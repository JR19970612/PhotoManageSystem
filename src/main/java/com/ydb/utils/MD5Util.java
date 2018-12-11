package com.ydb.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @program: com.ydb.utils
 * @description: md5加密
 * @author: Jun
 * @create: 2018-12-11 14:38
 **/

public class MD5Util {
    /**
     * 生成MD5
     *
     * @param word
     * @return
     */
    public static String encode(String word) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(word.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
