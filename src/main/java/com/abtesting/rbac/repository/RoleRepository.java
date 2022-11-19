package com.abtesting.rbac.repository;

import com.abtesting.rbac.constants.CommonConstants;
import com.abtesting.rbac.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select role from Role role where role.name=?1 and role.status = ?2")
    Role findRoleByName(String name, Integer status);

    default Role findRoleByName(String name) {
        return findRoleByName(name, CommonConstants.CompanyConstantsEnum.ACTIVE.getId());
    }
}