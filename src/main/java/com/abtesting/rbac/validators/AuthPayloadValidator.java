package com.abtesting.rbac.validators;

import com.abtesting.rbac.model.Auth.AuthRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthPayloadValidator {

    public void validateRequest(AuthRequest request) {
        CommonValidators.checkEmptyRequest(request);
        CommonValidators.checkActionValues(request.getActionValue());
    }
}