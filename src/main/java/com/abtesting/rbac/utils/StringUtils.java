package com.abtesting.rbac.utils;

public class StringUtils {
    /**
     * check if string is empty or not
     *
     * @param data
     * @return
     */
    public static boolean isEmpty(String data) {
        return data == null || data.isEmpty() || data.isBlank();
    }

    /**
     * checks if two string are equal or not
     *
     * @param str1
     * @param str2
     * @return boolean
     */
    public static boolean equals(String str1, String str2) {
        return (str1 == str2) || (str1 != null && str2 != null && str1.equals(str2));
    }
}