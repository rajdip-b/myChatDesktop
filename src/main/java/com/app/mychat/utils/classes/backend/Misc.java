package com.app.mychat.utils.classes.backend;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Misc {

    public static String hashUp(String input) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA256");
        }catch (NoSuchAlgorithmException e) {
            System.exit(1);
        }
        byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hex = new StringBuilder(number.toString(16));
        while(hex.length() < 32)
            hex.insert(0, '0');
        return hex.toString();
    }

    public static int getQueryCode(HashMap<String, Object> message){
        return (int) message.get(KeyValues.KEY_QUERY);
    }

    public static int getResponseCode(HashMap<String, Object> message){
        return (int) message.get(KeyValues.KEY_RESPONSE_CODE);
    }

}