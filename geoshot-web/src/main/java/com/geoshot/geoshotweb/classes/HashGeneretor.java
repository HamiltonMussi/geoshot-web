package com.geoshot.geoshotweb.classes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGeneretor {

//    public static void main(String[] args) {
//        System.out.println(getHash("SHA256", "1234"));
//    }

    public static String getHash(String algorithm, String plainPassword) {
        StringBuilder byteToHexString = new StringBuilder();
        MessageDigest digest = null;
        byte[] bytePassword;

        try {
            digest = MessageDigest.getInstance(algorithm);
        } catch(NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
        }

        try {

            bytePassword = digest.digest(plainPassword.getBytes("UTF-8"));
            for(byte b: bytePassword) {
                String hex = String.format("%02x",b);
                byteToHexString.append(hex);
            }

        } catch(UnsupportedEncodingException ex) {
            System.out.println(ex.getMessage());
        } catch(NullPointerException ex) {
            System.out.println(ex.getMessage());
        }

        return byteToHexString.toString();
    }

}
