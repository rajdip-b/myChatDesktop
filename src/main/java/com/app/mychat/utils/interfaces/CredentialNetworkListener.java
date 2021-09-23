package com.app.mychat.utils.interfaces;

public interface CredentialNetworkListener {

    // Gets triggered if the connection was unsuccessful. Returns a message.
    void onConnectionUnSuccessful(String message);

    // Gets triggered when some error has been encountered while attempting to login/signup
    void onErrorWhileOperation(String message);

    //Gets triggered if login/signup was successful
    void onOperationSuccessful(String message);

}