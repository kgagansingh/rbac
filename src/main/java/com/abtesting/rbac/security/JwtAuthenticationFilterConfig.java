package com.abtesting.rbac.security;

import com.abtesting.rbac.constants.CommonConstants;
import com.abtesting.rbac.service.JwtService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
public class JwtAuthenticationFilterConfig {
    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> configureJwtAuthenticationFilter(JwtService jwtService, HandlerExceptionResolver handlerExceptionResolver) {
        FilterRegistrationBean<JwtAuthenticationFilter> configurationBean = new FilterRegistrationBean<>();
        configurationBean.setFilter(new JwtAuthenticationFilter(jwtService, handlerExceptionResolver));
        configurationBean.setUrlPatterns(CommonConstants.RESTRICTED_ENDPOINTS.LIST);
        return configurationBean;
    }
}