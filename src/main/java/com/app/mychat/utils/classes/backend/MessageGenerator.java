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

    public static HashMap<String, Object> generatePasswordRequestMessage(String username){
        HashMap<String, Object> msg = new HashMap<>();
        msg.put(KEY_QUERY, QUERY_REQUEST_PASSWORD);
        msg.put(KEY_USERNAME, username);
        return msg;
    }

    public static HashMap<String, Object> generateEditAccountRequestMessage(String username, String firstName, String lastName, String password){
        HashMap<String, Object> msg = new HashMap<>();
        msg.put(KEY_QUERY, QUERY_EDIT_ACCOUNT);
        msg.put(KEY_USERNAME, username);
        msg.put(KEY_FIRST_NAME, firstName);
        msg.put(KEY_LAST_NAME, lastName);
        msg.put(KEY_PASSWORD, password);
        return msg;
    }

    public static HashMap<String, Object> generateDeleteAccountRequestMessage(String username){
        HashMap<String, Object> msg = new HashMap<>();
        msg.put(KEY_QUERY, QUERY_DELETE_ACCOUNT);
        msg.put(KEY_USERNAME, username);
        return msg;
    }

}
