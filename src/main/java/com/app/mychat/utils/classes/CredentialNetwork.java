package com.app.mychat.utils.classes;

import com.app.mychat.utils.interfaces.CredentialNetworkListener;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class CredentialNetwork {

    private CredentialNetworkListener networkListener;
    //	private static final String serverIP = "3.109.181.96";
    private static final String serverIP = "127.0.0.1";
    private static final int credentialServerPort = 5000;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public CredentialNetwork(CredentialNetworkListener networkListener){
        System.setProperty("javax.net.ssl.trustStore", "src/main/java/myChatTrustStore.jts");
        System.setProperty("javax.net.ssl.trustStorePassword", "cPPMq4IXThdRK1gf");
        this.networkListener = networkListener;
    }

    private boolean connectToCredentialServer(){
        SSLSocket socket;
        try{
            socket = (SSLSocket)(SSLSocketFactory.getDefault()).createSocket(serverIP, credentialServerPort);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            return true;
        } catch (IOException e){
            System.out.println(e);
            networkListener.onConnectionUnSuccessful("Host down or device is not connected!");
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public void attemptLogin(HashMap<String, Object> message){
        new Thread(() -> {
            try{
                if(connectToCredentialServer()) {
                    // Write the message to the server
                    objectOutputStream.writeObject(message);
                    // Wait for the server to respond
                    HashMap<String, Object> m = (HashMap<String, Object>) objectInputStream.readObject();
                    int responseCode = Integer.parseInt(m.get(KeyValues.KEY_RESPONSE_CODE).toString());
                    if (responseCode == KeyValues.RESPONSE_CODE_FAILURE)
                        networkListener.onErrorWhileOperation((String) m.get(KeyValues.KEY_RESPONSE_MESSAGE));
                    else if (responseCode == KeyValues.RESPONSE_CODE_SUCCESS) {
                        networkListener.onOperationSuccessful("Login successful!");
                    }
                }
            }catch (IOException e){
                System.out.println(e);
                networkListener.onErrorWhileOperation("Server down!");
            }catch (ClassNotFoundException e){
                System.out.println(e);
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    public void attemptSignup(HashMap<String, Object> message) {
        new Thread(() -> {
            try {
                if(connectToCredentialServer()) {
                    // Write the message to the server
                    objectOutputStream.writeObject(message);
                    // Wait for the server to respond
                    HashMap<String, Object> m = (HashMap<String, Object>) objectInputStream.readObject();
                    int responseCode = Integer.parseInt(m.get(KeyValues.KEY_RESPONSE_CODE).toString());
                    if (responseCode == KeyValues.RESPONSE_CODE_FAILURE)
                        networkListener.onErrorWhileOperation((String) m.get(KeyValues.KEY_RESPONSE_MESSAGE));
                    else if (responseCode == KeyValues.RESPONSE_CODE_SUCCESS) {
                        networkListener.onOperationSuccessful("Signup successful!");
                    }
                }
            }catch (IOException e) {
                System.out.println(e);
                networkListener.onErrorWhileOperation("Server down!");
            }catch (ClassNotFoundException e) {
                System.out.println(e);
                // TODO: handle exception
            }
        }).start();
    }

}
