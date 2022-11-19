package com.abtesting.rbac.controller.v1;

import com.abtesting.rbac.constants.CommonConstants;
import com.abtesting.rbac.entity.Company;
import com.abtesting.rbac.entity.Permission;
import com.abtesting.rbac.entity.Role;
import com.abtesting.rbac.entity.RolePermissionMap;
import com.abtesting.rbac.repository.CompanyRepository;
import com.abtesting.rbac.repository.PermissionRepository;
import com.abtesting.rbac.repository.RolePermMapRepository;
import com.abtesting.rbac.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SetupController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    RolePermMapRepository rolePermMapRepository;

    @GetMapping("/v1/list/setup")
    public void setup(){
//        roleRepository.save(Role.builder()
//                .name("admin")
//                .status(CommonConstants.GlobalStringConstants.ACTIVE.getId())
//                .build());
//        companyRepository.save(Company.builder()
//                .name("Jio")
//                .status(CommonConstants.GlobalStringConstants.ACTIVE.getId())
//                .build());

//        permissionRepository.save(Permission.builder()
//                .name("default")
//                .status(CommonConstants.GlobalStringConstants.ACTIVE.getId())
//                .build());
//        permissionRepository.save(Permission.builder()
//                .name("login")
//                .status(CommonConstants.GlobalStringConstants.ACTIVE.getId())
//                .build());
//        permissionRepository.save(Permission.builder()
//                .name("create_experiment")
//                .status(CommonConstants.GlobalStringConstants.ACTIVE.getId())
//                .build());
//
//        permissionRepository.findAll().stream().map(permission -> RolePermissionMap.builder()
//                .permissionId(permission.getId())
//                .roleId(roleRepository.findRoleByName("admin").getId())
//                .build()).forEach(rolePermissionMap -> rolePermMapRepository.save(rolePermissionMap));

//        permissionRepository.findById(1L).stream().map(permission -> RolePermissionMap.builder()
//                .permissionId(permission.getId())
//                .roleId(roleRepository.findRoleByName("user").getId())
//                .build()).forEach(rolePermissionMap -> rolePermMapRepository.save(rolePermissionMap));
//
//        permissionRepository.findById(2L).stream().map(permission -> RolePermissionMap.builder()
//                .permissionId(permission.getId())
//                .roleId(roleRepository.findRoleByName("user").getId())
//                .build()).forEach(rolePermissionMap -> rolePermMapRepository.save(rolePermissionMap));



    }
}
