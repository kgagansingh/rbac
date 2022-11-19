package com.abtesting.rbac.controller.v1;

import com.abtesting.rbac.model.Auth.AuthRequest;
import com.abtesting.rbac.model.Auth.AuthResponse;
import com.abtesting.rbac.model.signin.SignInRequest;
import com.abtesting.rbac.model.signin.SignInResponse;
import com.abtesting.rbac.model.signup.SignUpRequest;
import com.abtesting.rbac.model.signup.SignUpResponse;
import com.abtesting.rbac.model.userinfo.UserInfoResponse;
import com.abtesting.rbac.service.AuthService;
import com.abtesting.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/v1/signin")
    public SignInResponse userSignIn(@RequestBody SignInRequest request) {
        return userService.signIn(request);
    }

    @PostMapping("/v1/authorize")
    public AuthResponse isAuthorized(@RequestBody AuthRequest request, @RequestHeader String authorization) {
        return authService.isActionAuthorized(request, authorization);
    }

    @PostMapping("/v1/signup")
    public ResponseEntity<SignUpResponse> userSignUp(@RequestBody SignUpRequest signUpRequestData) {
        return new ResponseEntity<>(userService.addUser(signUpRequestData), HttpStatus.CREATED);
    }

    @GetMapping("/v1/user-info")
    public UserInfoResponse userInfo(@RequestHeader String userId) {
        return userService.getUserInfoFromUserId(userId);
    }
}