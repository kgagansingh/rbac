package com.abtesting.rbac.model.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class AuthResponse {
    private Boolean access;
    private String status;
}