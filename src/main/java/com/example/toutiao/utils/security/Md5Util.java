package com.example.toutiao.utils.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/23 21:57
 * @description:
 */
public class Md5Util {

    /**
     * 获取md5加密字符
     * @param plainText 明文
     * @param length 获取的md5密文长度
     * @return
     */
    public static String getMd5(String plainText, int length) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");  // 获取MD5实例
        md5.update(plainText.getBytes());       // 传入要加密的byte类型值
        byte[] digest = md5.digest();           // 得到md5加密后的byte类型值

        StringBuilder sb = new StringBuilder();

        for (byte b: digest) {
            // 与运算
            int number = b & 0xff;  // 加盐
            String str = Integer.toHexString(number);
            if (str.length() == 1) {
                sb.append("0");
            }
            sb.append(str);
        }

        // 从下标0开始，length的长度值
        return sb.toString().substring(0, length);
    }

    /**
     * 获取32位的md5加密值
     * @param plainText
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String get32BitMd5(String plainText) throws NoSuchAlgorithmException {
        return getMd5(plainText, 32);
    }
}
