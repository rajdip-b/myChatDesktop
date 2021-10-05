package com.app.mychat.utils.classes.backend;

import com.app.mychat.utils.interfaces.CredentialNetworkListener;
import static com.app.mychat.utils.classes.backend.Misc.*;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class CredentialNetwork {

    private final CredentialNetworkListener networkListener;
//    	private static final String serverIP = "3.109.181.96";
    private static final String serverIP = "127.0.0.1";
    private static final int credentialServerPort = 5000;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private static SSLSocket socket;

    public CredentialNetwork(CredentialNetworkListener networkListener){
        this.networkListener = networkListener;
    }

    private boolean connectToCredentialServer(){
        try{
            socket = (SSLSocket)(SSLSocketFactory.getDefault()).createSocket(serverIP, credentialServerPort);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            return true;
        } catch (IOException e){
            e.printStackTrace();
            networkListener.onConnectionUnSuccessful("Host down or device is not connected!");
            return false;
        }
    }

    public void attemptLogin(HashMap<String, Object> message){
        new Thread(() -> {
            try{
                if(connectToCredentialServer()) {
                    objectOutputStream.writeObject(message);
                    HashMap<String, Object> m = (HashMap<String, Object>) objectInputStream.readObject();
                    int responseCode = getResponseCode(m);
                    if (responseCode == KeyValues.RESPONSE_CODE_FAILURE) {
                        networkListener.onErrorWhileOperation((String) m.get(KeyValues.KEY_RESPONSE_MESSAGE));
                    }
                    else if (responseCode == KeyValues.RESPONSE_CODE_SUCCESS) {
                        networkListener.onOperationSuccessful("Login successful!", message.get(KeyValues.KEY_USERNAME).toString());
                    }
                }
            }catch (IOException e){
                networkListener.onErrorWhileOperation("Server down!");
            }catch (ClassNotFoundException ignored){
            }
            disconnect();
        }).start();
    }

    public void attemptSignup(HashMap<String, Object> message) {
        new Thread(() -> {
            try {
                if(connectToCredentialServer()) {
                    objectOutputStream.writeObject(message);
                    HashMap<String, Object> m = (HashMap<String, Object>) objectInputStream.readObject();
                    int responseCode = getResponseCode(m);
                    if (responseCode == KeyValues.RESPONSE_CODE_FAILURE)
                        networkListener.onErrorWhileOperation((String) m.get(KeyValues.KEY_RESPONSE_MESSAGE));
                    else if (responseCode == KeyValues.RESPONSE_CODE_SUCCESS) {
                        networkListener.onOperationSuccessful("Signup successful!", message.get(KeyValues.KEY_USERNAME).toString());
                    }
                }
            }catch (IOException e) {
                networkListener.onErrorWhileOperation("Server down!");
            }catch (ClassNotFoundException e) {
                // TODO: handle exception
            }
            disconnect();
        }).start();
    }

    private void disconnect(){
        try{
            socket.close();
        }catch (IOException | NullPointerException ignored){
        }
    }

}
