package com.abtesting.rbac.model.userinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class UserInfoResponse {
    private String userId;
    private String companyId;
    private String email;
    private Integer userStatus;
    private String roleId;
    private List<String> accessValues;
}