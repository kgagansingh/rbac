package com.abtesting.rbac.validators;

import java.util.regex.Pattern;

public class RegexValidators {
    private static final String LONG_INTEGER_REGEX = "^-?\\d{1,19}$";
    private static final String PASSWORD_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

    /**
     * validates UUID using regex
     *
     * @param id
     * @return
     */
    public static boolean isValidNumberId(String id) {
        Pattern UUIDRegex = Pattern.compile(LONG_INTEGER_REGEX);
        if (UUIDRegex.matcher(id).find()) {
            return true;
        }
        return false;
    }

    /**
     * validates password using regex
     *
     * @param password
     * @return
     */
    public static boolean validatePassword(String password) {
        Pattern passwordRegex = Pattern.compile(PASSWORD_REGEX);
        if (passwordRegex.matcher(password).find()) {
            return true;
        }
        return false;
    }
}