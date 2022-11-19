package com.abtesting.rbac.model.signin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignInRequest {
    private String email;
    private String password;
}