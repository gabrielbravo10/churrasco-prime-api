package com.churrascoprime.api.utils;

public class StringUtils {
    public static boolean isValid(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
