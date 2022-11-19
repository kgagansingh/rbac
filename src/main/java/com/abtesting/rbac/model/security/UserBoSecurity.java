package com.abtesting.rbac.model.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserBoSecurity {
    private String userId;
    private String token;
}