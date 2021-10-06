package com.app.mychat.utils.classes.backend;

import java.util.ArrayList;
import java.util.HashMap;
import static com.app.mychat.utils.classes.backend.KeyValues.*;

public class MessageGenerator {

    public static HashMap<String, Object> generateLoginRequestMessage(String username, String password){
        HashMap<String, Object> msg = new HashMap<>();
        msg.put(KEY_QUERY, QUERY_LOGIN_REQUEST);
        msg.put(KEY_USERNAME, username);
        msg.put(KEY_PASSWORD, password);
        return msg;
    }

    public static HashMap<String, Object> generateSignupRequestMessage(String firstName, String lastName, String email, String username, String password){
        HashMap<String, Object> msg = new HashMap<>();
        msg.put(KEY_QUERY, QUERY_SIGNUP_REQUEST);
        msg.put(KEY_FIRST_NAME, firstName);
        msg.put(KEY_LAST_NAME, lastName);
        msg.put(KEY_EMAIL, email);
        msg.put(KEY_USERNAME, username);
        msg.put(KEY_PASSWORD, password);
        return msg;
    }

    public static HashMap<String, Object> generateHandshake(String username){
        HashMap<String, Object> msg = new HashMap<>();
        msg.put(KEY_QUERY, QUERY_HANDSHAKE);
        msg.put(KEY_USERNAME, username);
        return msg;
    }

    public static HashMap<String, Object> generateClientListRequestMessage(String username){
        HashMap<String, Object> msg = new HashMap<>();
        msg.put(KEY_QUERY, QUERY_CLIENT_LIST);
        msg.put(KEY_USERNAME, username);
        return msg;
    }

    public static HashMap<String, Object> generateTextMessage(String username, String message){
        HashMap<String, Object> msg = new HashMap<>();
        msg.put(KEY_QUERY, QUERY_SEND_MESSAGE);
        ArrayList<String> messageBody= new ArrayList<>();
        messageBody.add(username);
        messageBody.add(message);
        msg.put(KEY_MESSAGE, messageBody);
        msg.put(KEY_USERNAME, username);
        return msg;
    }

    public static HashMap<String, Object> generateEditAccountWithPasswordAndWithUsernameRequestMessage(String username, String firstName, String lastName, String password, String email){
        HashMap<String, Object> msg = new HashMap<>();
        msg.put(KEY_QUERY, QUERY_EDIT_ACCOUNT_WITH_PASSWORD_AND_WITH_USERNAME);
        msg.put(KEY_USERNAME, username);
        msg.put(KEY_FIRST_NAME, firstName);
        msg.put(KEY_LAST_NAME, lastName);
        msg.put(KEY_PASSWORD, password);
        msg.put(KEY_EMAIL, email);
        return msg;
    }

    public static HashMap<String, Object> generateEditAccountWithoutPasswordAndWithUsernameRequestMessage(String username, String firstName, String lastName, String email){
        HashMap<String, Object> msg = new HashMap<>();
        msg.put(KEY_QUERY, QUERY_EDIT_ACCOUNT_WITHOUT_PASSWORD_AND_WITH_USERNAME);
        msg.put(KEY_USERNAME, username);
        msg.put(KEY_FIRST_NAME, firstName);
        msg.put(KEY_LAST_NAME, lastName);
        msg.put(KEY_EMAIL, email);
        return msg;
    }

    public static HashMap<String, Object> generateEditAccountWithPasswordAndWithoutUsernameRequestMessage(String username, String password, String firstName, String lastName, String email){
        HashMap<String, Object> msg = new HashMap<>();
        msg.put(KEY_QUERY, QUERY_EDIT_ACCOUNT_WITH_PASSWORD_AND_WITHOUT_USERNAME);
        msg.put(KEY_USERNAME, username);
        msg.put(KEY_PASSWORD, password);
        msg.put(KEY_FIRST_NAME, firstName);
        msg.put(KEY_LAST_NAME, lastName);
        msg.put(KEY_EMAIL, email);
        return msg;
    }

    public static HashMap<String, Object> generateEditAccountWithoutPasswordAndWithoutUsernameRequestMessage(String username, String firstName, String lastName, String email){
        HashMap<String, Object> msg = new HashMap<>();
        msg.put(KEY_QUERY, QUERY_EDIT_ACCOUNT_WITHOUT_PASSWORD_AND_WITHOUT_USERNAME);
        msg.put(KEY_USERNAME, username);
        msg.put(KEY_FIRST_NAME, firstName);
        msg.put(KEY_LAST_NAME, lastName);
        msg.put(KEY_EMAIL, email);
        return msg;
    }

    public static HashMap<String, Object> generateDeleteAccountRequestMessage(String username){
        HashMap<String, Object> msg = new HashMap<>();
        msg.put(KEY_QUERY, QUERY_DELETE_ACCOUNT);
        msg.put(KEY_USERNAME, username);
        return msg;
    }

    public static HashMap<String, Object> generateTypingStatusUpdateMessage(String username, int status){
        HashMap<String, Object> msg = new HashMap<>();
        msg.put(KEY_QUERY, QUERY_TYPING_STATUS_UPDATE);
        msg.put(KEY_USERNAME, username);
        msg.put(KEY_TYPING_STATUS, status);
        return msg;
    }

}
