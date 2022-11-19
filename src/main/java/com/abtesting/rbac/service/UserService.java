package com.abtesting.rbac.service;

import com.abtesting.rbac.builder.UserDocumentBuilder;
import com.abtesting.rbac.constants.CommonConstants;
import com.abtesting.rbac.entity.Access;
import com.abtesting.rbac.entity.RolePermissionMap;
import com.abtesting.rbac.entity.User;
import com.abtesting.rbac.model.jwt.JwtRequest;
import com.abtesting.rbac.model.signin.SignInRequest;
import com.abtesting.rbac.model.signin.SignInResponse;
import com.abtesting.rbac.model.signup.SignUpRequest;
import com.abtesting.rbac.model.signup.SignUpResponse;
import com.abtesting.rbac.model.userinfo.UserInfoResponse;
import com.abtesting.rbac.repository.AccessRepository;
import com.abtesting.rbac.repository.RolePermMapRepository;
import com.abtesting.rbac.repository.UserRepository;
import com.abtesting.rbac.validators.SignUpPayLoadValidator;
import com.abtesting.rbac.validators.SigninValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private SignUpPayLoadValidator signUpPayLoadValidator;

    @Autowired
    private SigninValidator signinValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RolePermMapRepository rolePermMapRepository;

    @Autowired
    private AccessRepository accessRepository;

//    @Autowired
//    private RedisCacheImpl redisCacheImpl;
//
//    @Value("${redis.cache.ttl}")
//    private Long REDIS_CACHE_TTL;

    /**
     * verifies user credentials and generates jwt token for authorization.
     *
     * @param request json with email and password
     * @return on success, map with jwt token, user id and response success status
     * on failure, exception with valid codes and message
     */
    public SignInResponse signIn(SignInRequest request) {
        signinValidator.validateRequest(request);
        User user = signinValidator.validateRequestWithDb(request);
        Access access = addAccessValuesForUser(user);
        return new SignInResponse(access.getToken(), CommonConstants.RequestResponseStatusConstants.SUCCESS, user.getId().toString());
    }

    /**
     * Used to get list of permission id for given role id
     *
     * @param roleId role id for which we want list of permissions
     * @return list of permission id
     */
    public List<String> getPermission(Long roleId) {
        List<RolePermissionMap> data = rolePermMapRepository.getPermissionList(roleId);
        return data.stream().map(perm -> perm.getPermissionId().toString()).toList();
    }

    /**
     * add user in database driver
     *
     * @param request
     * @return
     */
    public SignUpResponse addUser(SignUpRequest request) {
        signUpPayLoadValidator.validateRequest(request);
        signUpPayLoadValidator.validateRequestWithDb(request);
        User user = UserDocumentBuilder.buildUserDocumentFromSignUpRequest(request);
        user = userRepository.save(user);
        Access access = addAccessValuesForUser(user);
        return buildSignUpResponse(user, access);

    }

    /**
     * updates user access values with token, version and status
     *
     * @param user
     */
    private Access addAccessValuesForUser(User user) {
        JwtRequest jwtPayload = JwtRequest.builder()
                .userId(user.getId().toString())
                .companyId(user.getCompanyId().toString())
                .roleId(user.getRoleId().toString())
                .accessValues(getPermission(user.getRoleId()))
                .status(user.getUserStatus())
                .build();
        String token = jwtService.generateToken(jwtPayload).getToken();
        return setAccessForUser(user.getId(), token);
    }

    private Access setAccessForUser(Long id, String token) {
        return accessRepository.save(Access
                .builder()
                .userId(id)
                .token(token)
                .version(CommonConstants.TokenStatusConstants.VERSION)
                .status(CommonConstants.TokenStatusConstants.ACTIVE)
                .build()
        );
    }

    /**
     * build success response
     *
     * @param user
     * @return
     */
    private SignUpResponse buildSignUpResponse(User user, Access access) {
        return SignUpResponse.builder()
                .userId(user.getId().toString())
                .userStatus(user.getUserStatus())
                .token(access.getToken())
                .build();
    }

    /**
     * fetches user info using userId
     *
     * @param userId (String)
     * @return UserInfoResponse
     */
    public UserInfoResponse getUserInfoFromUserId(String userId) {
        return checkAndUpdateCacheForUserInfo(userId);
    }

    /**
     * checks and updates cache for user info
     *
     * @param userId (String)
     * @return UserInfoResponse
     */
    private UserInfoResponse checkAndUpdateCacheForUserInfo(String userId) {
        //uncomment when adding caching
//        Optional<UserInfoResponse> userInfoOptional = redisCacheImpl.get(CommonConstants.CacheConstants.USER_INFO(userId), UserInfoResponse.class);
//        if (userInfoOptional.isPresent()) {
//            return userInfoOptional.get();
//        }
        return fetchUserInfoAndUpdateCache(userId);
    }

    /**
     * updates cache for user info
     *
     * @param userId (String)
     * @return UserInfoResponse
     */
    private UserInfoResponse fetchUserInfoAndUpdateCache(String userId) {
        User user = userRepository.findById(Long.parseLong(userId)).get();
        UserInfoResponse userInfoResponse = UserDocumentBuilder.buildUserInfoResponse(user);
        //uncomment when adding caching
//        redisCacheImpl.put(CommonConstants.CacheConstants.USER_INFO(userId), userInfoResponse, REDIS_CACHE_TTL, TimeUnit.MICROSECONDS);
        return userInfoResponse;
    }

    /**
     * Invalidates userInfo Cache using userId
     *
     * @param userId (String)
     */
//    public void invalidateUserInfoCache(String userId) {
//        redisCacheImpl.delete(CommonConstants.CacheConstants.USER_INFO(userId));
//    }
}