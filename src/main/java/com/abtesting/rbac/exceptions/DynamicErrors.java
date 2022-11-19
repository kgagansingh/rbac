package com.abtesting.rbac.exceptions;

import java.util.Arrays;
import java.util.List;

public class DynamicErrors {
    private static String USER_NOT_FOUND_DESCRIPTION = "User %s not found";
    private static String UNAUTHORIZED_ACTION_DESCRIPTION = "User is unauthorized to perform %s";
    private static String REQUEST_BODY_MISSING_DESCRIPTION = "Invalid request body, please provide missing %s field";
    private Integer statusCode;
    private String errorCode;
    private String description;

    public static DynamicErrors USER_NOT_FOUND(String userId) {
        return new DynamicErrors(400,
                "TKDE-101",
                String.format(USER_NOT_FOUND_DESCRIPTION, userId));
    }

    public static DynamicErrors UNAUTHORIZED_ACTION(List<String> actionList) {
        return new DynamicErrors(403,
                "TKDE-102",
                String.format(UNAUTHORIZED_ACTION_DESCRIPTION, Arrays.toString(actionList.toArray())));
    }

    public static DynamicErrors REQUEST_BODY_MISSING(String missingData) {
        return new DynamicErrors(400,
                "TKDE-103",
                String.format(REQUEST_BODY_MISSING_DESCRIPTION, missingData));
    }

    DynamicErrors(int statusCode, String errorCode, String description) {
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