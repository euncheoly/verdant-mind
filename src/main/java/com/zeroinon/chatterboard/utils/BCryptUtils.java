package com.zeroinon.chatterboard.utils;


import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtils {


    public static String bcryptHash(String password) {
        if(password != null && password.length() > 7) {
            String hash = BCrypt.hashpw(password, BCrypt.gensalt());
            return hash;
        }else{
            throw new IllegalArgumentException("Password must have at least 8 characters");
        }
    }




}
