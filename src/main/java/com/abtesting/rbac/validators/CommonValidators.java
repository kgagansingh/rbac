package com.abtesting.rbac.validators;

import com.abtesting.rbac.constants.CommonConstants;
import com.abtesting.rbac.entity.User;
import com.abtesting.rbac.exceptions.NonRetryableException;
import com.abtesting.rbac.exceptions.StaticErrorsEnum;
import com.abtesting.rbac.repository.CompanyRepository;
import com.abtesting.rbac.repository.UserRepository;
import com.abtesting.rbac.utils.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class CommonValidators {

    private static CompanyRepository companyRepository;

    private static UserRepository userRepository;

    /**
     * checks if email is valid
     *
     * @param email
     * @throws NonRetryableException
     */
    public static void validateEmail(String email) {
        if (StringUtils.isEmpty(email) || email.length() > CommonConstants.GlobalNumericConstants.EMAIl_LENGTH.getValue() || !EmailValidator.getInstance().isValid(email)) {
            throw new NonRetryableException(StaticErrorsEnum.INVALID_EMAIL);
        }
    }

    /**
     * password should have at least 8 characters, 1 upper case letter, 1 lower case letter,, 1 special character, 1 number
     *
     * @param password
     * @throws NonRetryableException
     */
    public static void validatePassword(String password) {
        if (StringUtils.isEmpty(password) || !RegexValidators.validatePassword(password)) {
            throw new NonRetryableException(StaticErrorsEnum.INVALID_PASSWORD);
        }
    }

    /**
     * checks if UUID is valid or not
     *
     * @param companyId
     * @throws NonRetryableException
     */
    public static void validateCompanyId(String companyId) {
        if (StringUtils.isEmpty(companyId) || !RegexValidators.isValidNumberId(companyId)) {
            throw new NonRetryableException(StaticErrorsEnum.INVALID_COMPANY_ID);
        }
    }

    /**
     * validates CompanyID with Db
     *
     * @param companyId
     * @throws NonRetryableException
     */
    public static void validateCompanyIdWithDb(String companyId) {
        if (!companyRepository.findByCompanyId(Long.parseLong(companyId)).isPresent()) {
            throw new NonRetryableException(StaticErrorsEnum.INVALID_COMPANY_ID);
        }
    }

    /**
     * checks if user already exists in db
     *
     * @param email
     * @throws NonRetryableException
     */
    public static void checkUserExistenceUsingEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user != null) {
            throw new NonRetryableException(StaticErrorsEnum.USER_ALREADY_EXISTS);
        }
    }

    /**
     * Validates if any user exists with given email and password.
     *
     * @param email    email address sent for login
     * @param password password sent for login
     * @return user details if exists with given email and password
     * @throws NonRetryableException
     */
    public static User checkUserExistenceUsingEmailPassword(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new NonRetryableException(StaticErrorsEnum.USER_NOT_FOUND);
        } else if (!password.equals(user.getPassword())) {
            throw new NonRetryableException(StaticErrorsEnum.INVALID_PASSWORD);
        }
        return user;
    }

    /**
     * checks if request body is null
     *
     * @param request
     * @throws NonRetryableException
     */
    public static void checkEmptyRequest(Object request) {
        if (request == null) {
            throw new NonRetryableException(StaticErrorsEnum.BAD_REQUEST);
        }
    }

    public static void checkActionValues(List<String> actionValues) {
        if (Objects.isNull(actionValues) || actionValues.isEmpty()) {
            throw new NonRetryableException(StaticErrorsEnum.NO_ACTION_TO_AUTHORIZE);
        }
    }

    /**
     * validates if userId is empty or not a UUID
     *
     * @param userId (String)
     * @throws NonRetryableException
     */
    public static void validateUserId(String userId) {
        if (StringUtils.isEmpty(userId) || !RegexValidators.isValidNumberId(userId)) {
            throw new NonRetryableException(StaticErrorsEnum.INVALID_USER_ID);
        }
    }

    /**
     * validates and fetches user from db
     *
     * @param userId (String)
     * @return User
     * @throws NonRetryableException
     */
    public static User validateUserIdAndGetUserFromDb(String userId) {
        Optional<User> userOptional = userRepository.findById(Long.parseLong(userId));
        if (!userOptional.isPresent()) {
            throw new NonRetryableException(StaticErrorsEnum.INVALID_USER_ID);
        }
        return userOptional.get();
    }

    @Autowired
    private void setCompanyRepository(CompanyRepository injectedRepo) {
        companyRepository = injectedRepo;
    }

    @Autowired
    private void setUserRepository(UserRepository injectedRepo) {
        userRepository = injectedRepo;
    }
}
