package com.abtesting.rbac.constants;

import java.util.Arrays;
import java.util.List;

public class CommonConstants {

    public enum GlobalStringConstants {
        ACTIVE(0, "active"),
        IN_ACTIVE(1, "inActive"),
        AUTHORIZATION(2, "Authorization"),
        BEARER(3, "Bearer"),
        USERID(4, "userId");

        private Integer id;
        private String value;

        GlobalStringConstants(Integer id, String value) {
            this.id = id;
            this.value = value;
        }

        public Integer getId() {
            return id;
        }

        public String getValue() {
            return value;
        }
    }

    public enum GlobalNumericConstants {
        EMAIl_LENGTH(0, 64);

        private Integer id;
        private Integer value;

        GlobalNumericConstants(Integer id, Integer value) {
            this.id = id;
            this.value = value;
        }

        public Integer getId() {
            return id;
        }

        public Integer getValue() {
            return value;
        }
    }

    public static class EncryptionConstants {
        public static Integer BIG_INTEGER_SIGN = 1;  // can be -1, 0, 1
        public static Integer HEXADECIMAL_ENCODING = 16;
        public static Integer MINIMUM_HASH_LENGTH = 32;
        public static Character HASH_PREFIX = '0';
    }

    public enum CompanyConstantsEnum {
        ACTIVE(0, "active"),
        IN_ACTIVE(1, "inActive");

        private Integer id;
        private String value;

        CompanyConstantsEnum(Integer id, String value) {
            this.id = id;
            this.value = value;
        }

        public Integer getId() {
            return id;
        }

        public String getValue() {
            return value;
        }
    }

    public static class CacheConstants {
        public static String COMPANY_LIST = "COMPANY_LIST";

        public static String USER_INFO(String userId) {
            return "USER_INFO_" + userId;
        }
    }

    public enum UserStatusEnum {
        PENDING(0, "pending"),
        ACTIVE(1, "active"),
        IN_ACTIVE(2, "inActive"),
        REJECTED(3, "rejected"),
        DELETED(4, "deleted");

        String value;
        Integer id;

        public String getValue() {
            return value;
        }

        public Integer getId() {
            return id;
        }

        UserStatusEnum(Integer id, String value) {
            this.value = value;
            this.id = id;
        }

        public static List<Integer> ACTIVE_STATUS_LIST = Arrays.asList(PENDING.id, ACTIVE.id, IN_ACTIVE.id);
        public static List<Integer> IN_ACTIVE_STATUS_LIST = Arrays.asList(REJECTED.id, DELETED.id);

        public static List<Integer> VALID_TRANSITION_PENDING = Arrays.asList(ACTIVE.id, REJECTED.id);
        public static List<Integer> VALID_TRANSITION_ACTIVE = Arrays.asList(IN_ACTIVE.id, DELETED.id);
        public static List<Integer> VALID_TRANSITION_IN_ACTIVE = Arrays.asList(ACTIVE.id, DELETED.id);
        public static List<Integer> VALID_TRANSITION_REJECTED = Arrays.asList();
        public static List<Integer> VALID_TRANSITION_DELETED = Arrays.asList();
    }

    public enum RoleConstantsEnum {
        USER("user");
        private String value;

        RoleConstantsEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static class TokenStatusConstants {
        public static Integer ACTIVE = GlobalStringConstants.ACTIVE.getId();
        public static Integer INACTIVE = GlobalStringConstants.IN_ACTIVE.getId();
        public static String VERSION = "";
    }

    public static class RequestResponseStatusConstants {
        public static final String SUCCESS = "success";
    }

    public static class RESTRICTED_ENDPOINTS {
        public static List<String> LIST = Arrays.asList(
                "/user/v1/user-info",
                "/v1/constants",
                "/user/v1/authorize");
    }
}