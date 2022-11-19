package com.abtesting.rbac.controller.v1;

import com.abtesting.rbac.constants.CommonConstants;
import com.abtesting.rbac.entity.Company;
import com.abtesting.rbac.entity.Role;
import com.abtesting.rbac.model.company.CompanyResponse;
import com.abtesting.rbac.repository.CompanyRepository;
import com.abtesting.rbac.repository.RoleRepository;
import com.abtesting.rbac.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/v1/list/companies")
    public List<CompanyResponse> getCompanyList() {
        return this.companyService.getCompanyList();
    }
}
