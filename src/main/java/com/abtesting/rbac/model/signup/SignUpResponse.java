package com.abtesting.rbac.model.signup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class SignUpResponse {
    private String userId;
    private Integer userStatus;
    private String token;
}