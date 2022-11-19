package com.abtesting.rbac.model.constant;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class ConstantsResponse {
    private Map<String, String> roleIdNameMap;
    private Map<String, String> roleNameIdMap;
    private Map<String, String> permissionIdNameMap;
    private Map<String, String> permissionNameIdMap;
    private Map<Integer, String> userStatusIdNameMap;
    private Map<String, Integer> userStatusNameIdMap;
    private Map<Integer, String> globalIdNameMap;
    private Map<String, Integer> globalNameIdMap;
    private Map<String, String> companyIdNameMap;
    private Map<String, String> companyNameIdMap;
}