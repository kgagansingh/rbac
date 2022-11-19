package com.abtesting.rbac.repository;

import com.abtesting.rbac.entity.RolePermissionMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolePermMapRepository extends JpaRepository<RolePermissionMap, Long> {
    @Query("select rolePerm from RolePermissionMap rolePerm where rolePerm.roleId = ?1")
    List<RolePermissionMap> getPermissionList(Long roleId);
}