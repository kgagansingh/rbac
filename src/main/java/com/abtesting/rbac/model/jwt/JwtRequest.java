package com.abtesting.rbac.model.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Jwt Token payload Model
 */
@Builder
@AllArgsConstructor
@Getter
public class JwtRequest {
    private String userId;
    private String companyId;
    private String roleId;
    private List<String> accessValues;
    private Integer status;
}