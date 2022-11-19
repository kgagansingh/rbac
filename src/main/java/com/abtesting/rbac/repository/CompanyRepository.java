package com.abtesting.rbac.repository;

import com.abtesting.rbac.constants.CommonConstants;
import com.abtesting.rbac.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select company from Company company where company.id = ?1 and company.status = ?2")
    Optional<Company> findByCompanyId(Long companyId, Integer status);

    default Optional<Company> findByCompanyId(Long companyId) {
        return findByCompanyId(companyId, CommonConstants.CompanyConstantsEnum.ACTIVE.getId());
    }
}