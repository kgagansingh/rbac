package com.abtesting.rbac.builder;

import com.abtesting.rbac.constants.CommonConstants;
import com.abtesting.rbac.entity.User;
import com.abtesting.rbac.model.signup.SignUpRequest;
import com.abtesting.rbac.model.userinfo.UserInfoResponse;
import com.abtesting.rbac.repository.RoleRepository;
import com.abtesting.rbac.service.JwtService;
import com.abtesting.rbac.utils.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDocumentBuilder {

    private static RoleRepository roleRepository;
    private static JwtService jwtService;

    /**
     * build user documnet to save in  Db
     *
     * @param request
     * @return
     */
    public static User buildUserDocumentFromSignUpRequest(SignUpRequest request) {
        return User.builder()
                .companyId(Long.parseLong(request.getCompanyId()))
                .email(request.getEmail())
                .password(Encryption.encrypt(request.getPassword()))
                .roleId(roleRepository.findRoleByName(CommonConstants.RoleConstantsEnum.USER.getValue()).getId())
                .userStatus(CommonConstants.UserStatusEnum.PENDING.getId())
                .build();
    }

    /**
     * Build user info response
     *
     * @param user (User)
     * @return UserInfoResponse Object
     */
    public static UserInfoResponse buildUserInfoResponse(User user) {
        return UserInfoResponse.builder()
                .userId(user.getId().toString())
                .companyId(user.getCompanyId().toString())
                .email(user.getEmail())
                .userStatus(user.getUserStatus())
                .roleId(user.getRoleId().toString())
                .accessValues(jwtService.getAccessListFromToken(user.getTokenFromAccess()))
                .build();
    }

    @Autowired
    public void setRoleRepository(RoleRepository injectedRepo) {
        roleRepository = injectedRepo;
    }

    @Autowired
    public void setRoleRepository(JwtService injectedRepo) {
        jwtService = injectedRepo;
    }
}
