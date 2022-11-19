package com.abtesting.rbac.service;

import com.abtesting.rbac.constants.CommonConstants;
import com.abtesting.rbac.model.constant.ConstantsResponse;
import com.abtesting.rbac.repository.PermissionRepository;
import com.abtesting.rbac.repository.RoleRepository;
import com.abtesting.rbac.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HelperService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private CompanyService companyService;

    /**
     * generates and returns all the available constants used in TestStudio
     *
     * @return ConstantsResponse
     */
    public ConstantsResponse getConstantsList() {
        return ConstantsResponse.builder()
                .roleIdNameMap(generateRoleIdNameMap())
                .roleNameIdMap(generateRoleNameIdMap())
                .permissionIdNameMap(generatePermissionIdNameMap())
                .permissionNameIdMap(generatePermissionNameIdMap())
                .userStatusIdNameMap(generateUserStatusIdNameMap())
                .userStatusNameIdMap(generateUserStatusNameIdMap())
                .globalIdNameMap(generateGlobalIdNameMap())
                .globalNameIdMap(generateGlobalNameIdMap())
                .companyIdNameMap(generateCompanyIdNameMap())
                .companyNameIdMap(generateCompanyNameIdMap())
                .build();
    }

    /**
     * returns map of roleId & roleName
     *
     * @return Map<String, String> (roleId, roleName)
     */
    private Map generateRoleIdNameMap() {
        return roleRepository.findAll()
                .stream()
                .filter(role -> !NumberUtils.equals(role.getStatus(), CommonConstants.GlobalStringConstants.IN_ACTIVE.getId()))
                .collect(Collectors.toMap(role -> role.getId().toString(), role -> role.getName()));
    }

    /**
     * returns map of roleName & roleId
     *
     * @return Map<String, String> (roleName, roleId)
     */
    private Map generateRoleNameIdMap() {
        return roleRepository.findAll()
                .stream()
                .filter(role -> !NumberUtils.equals(role.getStatus(), CommonConstants.GlobalStringConstants.IN_ACTIVE.getId()))
                .collect(Collectors.toMap(role -> role.getName(), role -> role.getId().toString()));
    }

    /**
     * returns map of permissionId & permissionName
     *
     * @return Map<String, String> (permissionId, permissionName)
     */
    private Map generatePermissionIdNameMap() {
        return permissionRepository.findAll()
                .stream()
                .filter(permission -> !NumberUtils.equals(permission.getStatus(), CommonConstants.GlobalStringConstants.IN_ACTIVE.getId()))
                .collect(Collectors.toMap(permission -> permission.getId().toString(), permission -> permission.getName()));
    }

    /**
     * returns map of permissionName & permissionId
     *
     * @return Map<String, String> (permissionName, permissionId)
     */
    private Map generatePermissionNameIdMap() {
        return permissionRepository.findAll()
                .stream()
                .filter(permission -> !NumberUtils.equals(permission.getStatus(), CommonConstants.GlobalStringConstants.IN_ACTIVE.getId()))
                .collect(Collectors.toMap(permission -> permission.getName(), permission -> permission.getId().toString()));
    }

    /**
     * returns Map of  userStatusID & userStatusName
     *
     * @return Map<Integer, String> (userStatusId, userStatusName)
     */
    private Map generateUserStatusIdNameMap() {
        return Arrays.stream(CommonConstants.UserStatusEnum.class.getEnumConstants())
                .collect(Collectors.toMap(member -> member.getId(), member -> member.getValue()));
    }

    /**
     * returns Map of  userStatusName & userStatusId
     *
     * @return Map<String, Integer> (userStatusName, userStatusId)
     */
    private Map generateUserStatusNameIdMap() {
        return Arrays.stream(CommonConstants.UserStatusEnum.class.getEnumConstants())
                .collect(Collectors.toMap(member -> member.getValue(), member -> member.getId()));
    }

    /**
     * returns Map of  global constants's id & name
     *
     * @return Map<Integer, String> (id, name)
     */
    private Map generateGlobalIdNameMap() {
        return Arrays.stream(CommonConstants.GlobalStringConstants.class.getEnumConstants())
                .collect(Collectors.toMap(member -> member.getId(), member -> member.getValue()));
    }

    /**
     * returns Map of  global constants's name & id
     *
     * @return Map<String, Name> (name, id)
     */
    private Map generateGlobalNameIdMap() {
        return Arrays.stream(CommonConstants.GlobalStringConstants.class.getEnumConstants())
                .collect(Collectors.toMap(member -> member.getValue(), member -> member.getId()));
    }

    /**
     * returns Map of  company name & id
     *
     * @return Map<String, String> (name, id)
     */
    private Map generateCompanyIdNameMap() {
        return companyService.getCompanyList()
                .stream()
                .collect(Collectors.toMap(company -> company.getCompanyId(), company -> company.getCompanyName()));
    }

    /**
     * returns Map of  company id & name
     *
     * @return Map<String, String> (id, name)
     */
    private Map generateCompanyNameIdMap() {
        return companyService.getCompanyList()
                .stream()
                .collect(Collectors.toMap(company -> company.getCompanyName(), company -> company.getCompanyId()));
    }
}