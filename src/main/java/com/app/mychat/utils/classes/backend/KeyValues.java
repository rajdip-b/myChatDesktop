package com.app.mychat.utils.classes.backend;

public class KeyValues {

    public static final String KEY_QUERY = "query"; // Key that holds the queries
    public static final String KEY_RESPONSE_MESSAGE = "response_message"; // Key that holds the response message from the server
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_FIRST_NAME = "firstname";
    public static final String KEY_LAST_NAME = "lastname";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_RESPONSE_CODE = "response_code"; // Key that holds the response code from the server => SUCCESS/FAILURE
    public static final String KEY_MESSAGE = "message"; //Holds the message
    public static final String KEY_ACTIVE_USERS_LIST = "active_users";
    public static final String KEY_INACTIVE_USERS_LIST = "inactive_users";
    public static final String KEY_EXISTING_MESSAGES = "existing_messages";
    public static final String KEY_USER_DETAILS = "user_details";
    public static final String KEY_TYPING_STATUS = "typing_status";

    public static final int RESPONSE_CODE_SUCCESS = 1; // Used when some operation is successful
    public static final int RESPONSE_CODE_FAILURE = 2; // Used when some operation is successful
    public static final int TYPING_STATUS_ON = 14;
    public static final int TYPING_STATUS_OFF = 15;

    public static final int QUERY_HANDSHAKE = 7;
    public static final int QUERY_LOGIN_REQUEST = 3;   // Sent from the client to request a login
    public static final int QUERY_SIGNUP_REQUEST = 4;  // Sent from the client to request a signup
    public static final int QUERY_SEND_MESSAGE = 5;
    public static final int QUERY_CLIENT_LIST = 6;
    public static final int QUERY_DELETE_ACCOUNT = 8;
    public static final int QUERY_EDIT_ACCOUNT = 13; // sent by the server as a response to queries 9, 10, 11, 12
    public static final int QUERY_EDIT_ACCOUNT_WITH_PASSWORD_AND_WITH_USERNAME = 9;
    public static final int QUERY_EDIT_ACCOUNT_WITHOUT_PASSWORD_AND_WITHOUT_USERNAME = 10;
    public static final int QUERY_EDIT_ACCOUNT_WITHOUT_PASSWORD_AND_WITH_USERNAME = 11;
    public static final int QUERY_EDIT_ACCOUNT_WITH_PASSWORD_AND_WITHOUT_USERNAME = 12;
    public static final int QUERY_TYPING_STATUS_UPDATE = 16;

}