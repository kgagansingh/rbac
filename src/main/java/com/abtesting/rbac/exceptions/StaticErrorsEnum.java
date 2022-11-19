package com.abtesting.rbac.exceptions;

import com.abtesting.rbac.constants.CommonConstants;

public enum StaticErrorsEnum {
    SOMETHING_WENT_WRONG(500,
            "TKSE-101",
            "Something went wrong, please try again later"),
    INVALID_EMAIL(400,
            "TKSE-102",
            "User email missing/invalid/exceeded " + CommonConstants.GlobalNumericConstants.EMAIl_LENGTH.getValue() + " char limit, please provide a valid email address."),
    INVALID_PASSWORD(403,
            "TKSE-103",
            "User Password missing/invalid, please provide a valid password."),
    INVALID_COMPANY_ID(400,
            "TKSE-104",
            "Company ID missing/invalid, please provide a valid Company ID."),
    USER_ALREADY_EXISTS(400,
            "TKSE-105",
            "User already Exists"),
    USER_NOT_FOUND(400,
            "TKSE-106",
            "User not found"),
    BAD_REQUEST(400,
            "TKSE-107",
            "Request Body cannot be Empty"),
    INVALID_USER_ID(400,
            "TKSE-108",
            "UserID missing/invalid, please provide a valid UserId."),
    NO_ACTION_TO_AUTHORIZE(400,
            "TKSE-109",
            "No actions to authorize"),
    UN_AUTHORIZED(401,
            "TKSE-110",
            "Un-Authorized user"),
    FORBIDDEN(403,
            "TKSE-111",
            "User Does not have permission to perform this Action"),
    INVALID_TOKEN(401,
            "TKSE-112",
            "Invalid Token , Please provide a valid token"),
    JWT_EXPIRED(401,
            "TKSE-113",
            "JWT Token Expired, Please provide a valid token");


    private Integer statusCode;
    private String errorCode;
    private String description;

    StaticErrorsEnum(int statusCode, String errorCode, String description) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.description = description;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getDescription() {
        return this.description;
    }
}
