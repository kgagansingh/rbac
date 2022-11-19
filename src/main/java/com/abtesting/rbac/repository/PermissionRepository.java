package com.abtesting.rbac.repository;

import com.abtesting.rbac.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
