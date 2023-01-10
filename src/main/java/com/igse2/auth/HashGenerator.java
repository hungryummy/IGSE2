package com.igse2.auth;

import java.security.MessageDigest;

/**
 * @author ：ZhRunXin
 * @date ：Created in 2023/1/11 0:54
 * @email ：zhrunxin33@gmail.com
 * @description：
 */
public class HashGenerator {

    public static String getSHA256(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return bytesToHex(hash); // make it printable
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    private static String bytesToHex(byte[] hash) {
        // return DatatypeConverter.printHexBinary(hash);
        final StringBuilder builder=new StringBuilder();
        for(byte b:hash) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();

    }
}
