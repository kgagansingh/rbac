package com.abtesting.rbac.model.signin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignInResponse {
    private String token;
    private String status;
    private String userId;
}