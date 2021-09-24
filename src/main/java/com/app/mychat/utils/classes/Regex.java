package com.app.mychat.utils.classes;

import java.util.regex.Pattern;

public class Regex {

    private static final String patternName = "^[a-zA-Z]{2,20}$";
    private static final String patternPassword = "^[a-zA-Z0-9!@#$]{8,20}$";
    private static final String patternEmail = "^[a-zA-Z0-9.]+[@][a-zA-Z0-9.]{5,}$";
    private static final String patternAlias = "^[a-zA-Z0-9]{5,20}$";

    // Regex match for first name / last name
    public static boolean isNameAMatch(String name) {
        return !Pattern.matches(patternName, name);
    }

    //Regex match for password
    public static boolean isPasswordAMatch(String password) {
        return !Pattern.matches(patternPassword, password);
    }

    //Regex match for alias
    public static boolean isAliasAMatch(String alias) {
        return !Pattern.matches(patternAlias, alias);
    }

    //Regex match for email
    public static boolean isEmailAMatch(String email) {
        return Pattern.matches(patternEmail, email);
    }

}