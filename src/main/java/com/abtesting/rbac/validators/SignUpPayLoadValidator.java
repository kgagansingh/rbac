package com.abtesting.rbac.validators;

import com.abtesting.rbac.model.signup.SignUpRequest;
import org.springframework.stereotype.Component;

@Component
public class SignUpPayLoadValidator {

    /**
     * validates signUp request data
     *
     * @param request
     */
    public void validateRequest(SignUpRequest request) {
        CommonValidators.checkEmptyRequest(request);
        CommonValidators.validateEmail(request.getEmail());
        CommonValidators.validatePassword(request.getPassword());
        CommonValidators.validateCompanyId(request.getCompanyId());
        refactorRequest(request);
    }

    /**
     * removes trailing spaces from request data
     *
     * @param request
     */
    private void refactorRequest(SignUpRequest request) {
        request.setCompanyId(request.getCompanyId().trim());
        request.setEmail(request.getEmail().trim().toLowerCase());
        request.setPassword(request.getPassword().trim());
    }

    /**
     * validates signup request data with db
     *
     * @param request
     */
    public void validateRequestWithDb(SignUpRequest request) {
        CommonValidators.checkUserExistenceUsingEmail(request.getEmail());
        CommonValidators.validateCompanyIdWithDb(request.getCompanyId());
    }
}