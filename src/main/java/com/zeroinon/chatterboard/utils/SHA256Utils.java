package com.zeroinon.chatterboard.utils;

import java.security.MessageDigest;

public class SHA256Utils {


    public static final String ENCRYPTION_ALGORITHM = "SHA-256";

    public static String encrypt(String plainText) {
        String hash = "";

        MessageDigest messageDigest;
        try{
            messageDigest = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
            messageDigest.update(plainText.getBytes());
            byte[] bytes = messageDigest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : bytes) {
                String hex = Integer.toHexString(0xff & b);
                hexString.append(hex.length() == 1 ? "0" + hex : hex);
            }
            hash = hexString.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return hash;
    }


}
