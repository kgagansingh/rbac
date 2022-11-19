package com.abtesting.rbac.utils;

public class NumberUtils {
    /**
     * null safe equals for checking of two numbers are equal
     *
     * @param num1
     * @param num2
     * @return
     */
    public static boolean equals(Integer num1, Integer num2) {
        return (num1 == null && num2 == null) || num1 != null && num2 != null && num1.equals(num2);
    }
}