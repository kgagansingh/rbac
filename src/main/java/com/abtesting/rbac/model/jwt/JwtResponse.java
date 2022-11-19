package com.abtesting.rbac.model.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Jwt Service Response Model
 */
@AllArgsConstructor
@Getter
public class JwtResponse {
    private String token;
}