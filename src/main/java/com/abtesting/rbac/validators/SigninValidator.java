package com.abtesting.rbac.validators;

import com.abtesting.rbac.entity.User;
import com.abtesting.rbac.model.signin.SignInRequest;
import com.abtesting.rbac.utils.Encryption;
import org.springframework.stereotype.Component;

@Component
public class SigninValidator {

    public void validateRequest(SignInRequest request) {
        CommonValidators.checkEmptyRequest(request);
        CommonValidators.validateEmail(request.getEmail());
        CommonValidators.validatePassword(request.getPassword());
    }

    public User validateRequestWithDb(SignInRequest request) {
        String password = Encryption.encrypt(request.getPassword());
        return CommonValidators.checkUserExistenceUsingEmailPassword(request.getEmail(), password);
    }
}
