package com.abtesting.rbac.repository;

import com.abtesting.rbac.constants.CommonConstants;
import com.abtesting.rbac.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select user from User user where user.email = ?1 and user.userStatus in ?2")
    User findUserByEmail(String email, List<Integer> userStatusList);

    @Query("select user from User user where user.id = ?1 and user.userStatus in ?2")
    Optional<User> findUserById(Long id, List<Integer> userStatusList);

    default User findUserByEmail(String email) {
        return findUserByEmail(email.toLowerCase(), CommonConstants.UserStatusEnum.ACTIVE_STATUS_LIST);
    }

    @Override
    default Optional<User> findById(Long id) {
        return findUserById(id, CommonConstants.UserStatusEnum.ACTIVE_STATUS_LIST);
    }
}