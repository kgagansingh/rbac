package com.abtesting.rbac.service;

import com.abtesting.rbac.constants.CommonConstants;
import com.abtesting.rbac.model.company.CompanyResponse;
import com.abtesting.rbac.repository.CompanyRepository;
import com.abtesting.rbac.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

//    @Autowired
//    private RedisCacheImpl redisCacheImpl;
//
//    @Value("${redis.cache.ttl}")
//    private Long REDIS_CACHE_TTL;

    /**
     * driiver function
     *
     * @return List of Companies {List<CompanyResponse>}
     */
    public List<CompanyResponse> getCompanyList() {
        return checkAndUpdateCompanyCache();
    }

    /**
     * fetches list of companies from cache of present else fetches from db
     *
     * @return List<CompanyResponse>
     */
    private List<CompanyResponse> checkAndUpdateCompanyCache() {
        //uncomment when adding caching
//        Optional<List> companyListOptional = redisCacheImpl.get(CommonConstants.CacheConstants.COMPANY_LIST, List.class);
//        if (companyListOptional.isPresent()) {
//            return companyListOptional.get();
//        }
        return fetchCompanyListAndUpdateCache();
    }

    /**
     * fetches list of companies from Db and updates the cache
     *
     * @return List<CompanyResponse>
     */
    private List<CompanyResponse> fetchCompanyListAndUpdateCache() {
        List<CompanyResponse> companyResponseList = this.companyRepository.findAll()
                .stream()
                .filter(company -> NumberUtils.equals(company.getStatus(), CommonConstants.CompanyConstantsEnum.ACTIVE.getId()))
                .map(company -> new CompanyResponse(company.getId().toString(), company.getName()))
                .toList();
        //uncomment when adding caching
//        redisCacheImpl.put(CommonConstants.CacheConstants.COMPANY_LIST, companyResponseList, REDIS_CACHE_TTL, TimeUnit.MICROSECONDS);
        return companyResponseList;
    }

    /**
     * Invalidates Company List Cache
     */
//    public void invalidateCompanyListCache() {
//        redisCacheImpl.delete(CommonConstants.CacheConstants.COMPANY_LIST);
//    }
}