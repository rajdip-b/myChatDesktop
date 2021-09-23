package com.app.mychat.controller;

import java.util.HashMap;
import java.util.regex.Pattern;

import com.app.mychat.utils.classes.CredentialNetwork;
import com.app.mychat.utils.classes.KeyValues;
import com.app.mychat.utils.interfaces.CredentialNetworkListener;
import static com.app.mychat.utils.classes.Hash.hashUp;

public class SignupGUIController implements CredentialNetworkListener {

    private CredentialNetwork credentialNetwork;
    private static final String patternName = "^[a-zA-Z]{2,20}$";
    private static final String patternPassword = "^[a-zA-Z0-9!@#$]{8,20}$";
    private static final String patternEmail = "^[a-zA-Z0-9.]{1,}[@]{1}[a-zA-Z0-9.]{5,}$";
    private static final String patternAlias = "^[a-zA-Z0-9]{5,20}$";

    public SignupGUIController() {
        credentialNetwork = new CredentialNetwork(this);
    }

    // Regex match for first name / last name
    private boolean isNameAMatch(String name) {
        if (Pattern.matches(patternName, name))
            return true;
        return false;
    }

    //Regex match for password
    private boolean isPasswordAMatch(String password) {
        if (Pattern.matches(patternPassword, password))
            return true;
        return false;
    }

    //Regex match for alias
    private boolean isAliasAMatch(String alias) {
        if (Pattern.matches(patternAlias, alias))
            return true;
        return false;
    }

    //Regex match for email
    private boolean isEmailAMatch(String email) {
        if (Pattern.matches(patternEmail, email))
            return true;
        return false;
    }

    public void onSignupClicked(String firstName, String lastName, String email, String password, String alias) {
        boolean flag = true;
        if (!isNameAMatch(firstName)){
            System.out.println("Invalid first name!");
            flag = false;
        }
        if (!isNameAMatch(lastName)) {
            System.out.println("Invalid last name!");
            flag = false;
        }
        if (!isEmailAMatch(email)) {
            System.out.println("Invalid email!");
            flag = false;
        }
        if (!isPasswordAMatch(password)) {
            System.out.println("Invalid password!");
            flag = false;
        }
        if (!isAliasAMatch(alias)) {
            System.out.println("Invalid alias");
            flag = false;
        }
        if (flag) {
            password = hashUp(password);
            HashMap<String, Object> message = new HashMap<>();
            message.put(KeyValues.KEY_QUERY, KeyValues.QUERY_SIGNUP_REQUEST);
            message.put(KeyValues.KEY_FIRST_NAME, firstName);
            message.put(KeyValues.KEY_LAST_NAME, lastName);
            message.put(KeyValues.KEY_PASSWORD, password);
            message.put(KeyValues.KEY_EMAIL, email);
            message.put(KeyValues.KEY_USERNAME, alias);
            credentialNetwork.attemptSignup(message);
        }
    }

    @Override
    public void onConnectionUnSuccessful(String message) {
        System.out.println(message);
    }

    @Override
    public void onErrorWhileOperation(String message) {
        System.out.println(message);
    }

    @Override
    public void onOperationSuccessful(String message) {
        System.out.println(message);
    }

}
