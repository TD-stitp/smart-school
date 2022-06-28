package com.td.smartschool.customer.common.utils;


import java.util.Random;
import java.util.UUID;

/**
 * @author XX
 * @version 2.0
 * @date 2021/11/8 11:41
 */
public class UUIDUtil {

    private static final String NUM_ARRAY = "0123456789";
    private static final String CHAR_ARRAY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static char getChar(String str) {
        return str.charAt(new Random(System.currentTimeMillis()).nextInt((str.length()) + 1) % str.length());
    }

    private static char getNum() {
        return getChar(NUM_ARRAY);
    }

    private static char getChar() {
        return getChar(CHAR_ARRAY);
    }

    private static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getImageUUID() {
        int hash = getUUID().hashCode();
        if (hash < 0) {
            hash = -hash;
        }
        String hashStr = String.valueOf(hash);
        if (hashStr.length() < 9) {
            return getNum() + hashStr;
        } else {
            return getNum() + String.valueOf(hash).substring(0, 9);
        }
    }

    public static String getCode() {
        return (getChar() + getUUID().substring(0, 5)).toUpperCase();
    }
}
