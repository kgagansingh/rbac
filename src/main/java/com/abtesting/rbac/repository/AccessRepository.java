package com.abtesting.rbac.repository;

import com.abtesting.rbac.entity.Access;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessRepository extends JpaRepository<Access, Long> {
}
