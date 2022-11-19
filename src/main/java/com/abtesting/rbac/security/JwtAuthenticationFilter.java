package com.abtesting.rbac.security;

import com.abtesting.rbac.exceptions.DynamicErrors;
import com.abtesting.rbac.exceptions.NonRetryableException;
import com.abtesting.rbac.exceptions.StaticErrorsEnum;
import com.abtesting.rbac.model.security.UserBoSecurity;
import com.abtesting.rbac.service.JwtService;
import com.abtesting.rbac.utils.StringUtils;
import com.abtesting.rbac.validators.CommonValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.abtesting.rbac.constants.CommonConstants.GlobalStringConstants.AUTHORIZATION;
import static com.abtesting.rbac.constants.CommonConstants.GlobalStringConstants.USERID;

@Component
public class JwtAuthenticationFilter implements Filter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    public JwtAuthenticationFilter(JwtService jwtService, HandlerExceptionResolver handlerExceptionResolver) {
        this.jwtService = jwtService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = ((HttpServletRequest) request);
        HttpServletResponse httpResponse = ((HttpServletResponse) response);
        String authorization = httpRequest.getHeader(AUTHORIZATION.getValue());
        String userId = httpRequest.getHeader(USERID.getValue());
        try {
            validateRequest(authorization, userId);
        } catch (Exception err) {
            handlerExceptionResolver.resolveException(httpRequest, httpResponse, null, err);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                new UserBoSecurity(userId, getTokenFromAuthorization(authorization)),
                null,
                null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private void validateRequest(String authBearer, String userId) {
        if (StringUtils.isEmpty(authBearer)) {
            throw new NonRetryableException(DynamicErrors.REQUEST_BODY_MISSING(AUTHORIZATION.getValue()));
        }
        if (StringUtils.isEmpty(userId)) {
            throw new NonRetryableException(DynamicErrors.REQUEST_BODY_MISSING(USERID.getValue()));
        }
        String token = getTokenFromAuthorization(authBearer);
        String tokenUserId;
        tokenUserId = jwtService.extractUserId(token);
        if (!StringUtils.equals(tokenUserId, userId)) {
            throw new NonRetryableException(StaticErrorsEnum.FORBIDDEN);
        }
        if (!jwtService.validateToken(token, userId)) {
            throw new NonRetryableException(StaticErrorsEnum.UN_AUTHORIZED);
        }
    }

    private String getTokenFromAuthorization(String authBearer) {
        return authBearer.split(" ")[1].trim();
    }
}