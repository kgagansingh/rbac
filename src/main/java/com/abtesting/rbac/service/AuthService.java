package com.abtesting.rbac.service;

import com.abtesting.rbac.constants.CommonConstants;
import com.abtesting.rbac.exceptions.NonRetryableException;
import com.abtesting.rbac.model.Auth.AuthRequest;
import com.abtesting.rbac.model.Auth.AuthResponse;
import com.abtesting.rbac.validators.AuthPayloadValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.abtesting.rbac.exceptions.DynamicErrors.UNAUTHORIZED_ACTION;

@Service
public class AuthService {

    @Autowired
    private AuthPayloadValidator authPayloadValidator;

    @Autowired
    private JwtService jwtService;

    /**
     * service layer to authorize given action list for given user.
     *
     * @param request contains the list of permission ids to authorise.
     * @return access=true on success, else forbidden exception.
     */
    public AuthResponse isActionAuthorized(AuthRequest request, String authBearer) {
        authPayloadValidator.validateRequest(request);
        List<String> permList = jwtService.getAccessListFromToken(getTokenFromAuthorizationHeader(authBearer));
        if (permList.containsAll(request.getActionValue())) {
            return buildSuccessResponse();
        }
        throw new NonRetryableException(UNAUTHORIZED_ACTION(request.getActionValue()));
    }

    /**
     * fetches token out of the authorization header
     *
     * @param authBearer
     * @return
     */
    private String getTokenFromAuthorizationHeader(String authBearer) {
        return authBearer.split(" ")[1].trim();
    }

    /**
     * builds success response
     *
     * @return AuthResponse
     */
    private AuthResponse buildSuccessResponse() {
        return AuthResponse.builder()
                .access(true)
                .status(CommonConstants.RequestResponseStatusConstants.SUCCESS)
                .build();
    }
}