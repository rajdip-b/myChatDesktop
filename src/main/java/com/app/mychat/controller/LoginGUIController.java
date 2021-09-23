package com.app.mychat.controller;

import java.util.HashMap;

import com.app.mychat.utils.classes.CredentialNetwork;
import com.app.mychat.utils.classes.KeyValues;
import com.app.mychat.utils.interfaces.CredentialNetworkListener;
import static com.app.mychat.utils.classes.Hash.hashUp;
public class LoginGUIController implements CredentialNetworkListener {

    private CredentialNetwork credentialNetwork;

    public LoginGUIController() {
        credentialNetwork = new CredentialNetwork(this);
    }

    public void onLoginClicked(String username, String password) {
        HashMap<String, Object> message = new HashMap<>();
        password = hashUp(password);
        message.put(KeyValues.KEY_QUERY, KeyValues.QUERY_LOGIN_REQUEST);
        message.put(KeyValues.KEY_USERNAME, username);
        message.put(KeyValues.KEY_PASSWORD, password);
        credentialNetwork.attemptLogin(message);
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