package com.app.mychat.utils.classes.backend;

import com.app.mychat.utils.interfaces.ChatNetworkListener;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatNetwork {

    private SSLSocket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private final ChatNetworkListener chatNetworkListener;
    private static final String serverIP = "127.0.0.1";
//    private static final String serverIP = "3.109.181.96";
    private static final int chatServerPort = 6000;

    public ChatNetwork(ChatNetworkListener chatNetworkListener){
        this.chatNetworkListener = chatNetworkListener;
        new Thread(() -> {
            try{
                if(getConnected()){
                    getMessages();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }).start();
    }

    private boolean getConnected(){
        try{
            socket = (SSLSocket)(SSLSocketFactory.getDefault()).createSocket(serverIP, chatServerPort);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            return true;
        }catch (IOException e){
            chatNetworkListener.serverUnreachable("The server is currently unreachable!");
            return false;
        }
    }

    private void getMessages(){
        try{
            while(true){
                if (objectInputStream.available() >= 0){
                    HashMap<String, Object> message = (HashMap<String, Object>) objectInputStream.readUnshared();
                    System.out.println("Reply: "+message);
                    resolveMessage(message);
                }
            }
        }catch(IOException e){
//            e.printStackTrace();
            chatNetworkListener.serverUnreachable("Disconnected");
        }catch(ClassNotFoundException e){
            System.exit(1);
        }
    }

    private void resolveMessage(HashMap<String, Object> message){
        int queryCode = Misc.getQueryCode(message);
        switch (queryCode){
            case KeyValues.QUERY_CLIENT_LIST -> resolveResponseClientList(message);
            case KeyValues.QUERY_SEND_MESSAGE -> resolveResponseTextMessage(message);
            case KeyValues.QUERY_HANDSHAKE -> resolveResponseHandshake(message);
            case KeyValues.QUERY_EDIT_ACCOUNT -> resolveResponseEditAccount(message);
        }
    }

    private void resolveResponseEditAccount(HashMap<String, Object> message){
        int responseCode = (Integer) message.get(KeyValues.KEY_RESPONSE_CODE);
        String responseMessage = (String) message.get(KeyValues.KEY_RESPONSE_MESSAGE);
        chatNetworkListener.editAccountResponseReceived(responseCode, responseMessage);
    }

    private void resolveResponseClientList(HashMap<String, Object> message){
        ArrayList<HashMap<String, Object>> active = (ArrayList<HashMap<String, Object>>) message.get(KeyValues.KEY_ACTIVE_USERS_LIST);
        ArrayList<HashMap<String, Object>> inactive = (ArrayList<HashMap<String, Object>>) message.get(KeyValues.KEY_INACTIVE_USERS_LIST);
        chatNetworkListener.clientListRecieved(active, inactive);
    }

    private void resolveResponseTextMessage(HashMap<String, Object> message){
        ArrayList<String> messageBody = (ArrayList<String>) message.get(KeyValues.KEY_MESSAGE);
        String sender = messageBody.get(0);
        String msg = messageBody.get(1);
        chatNetworkListener.messageReceived(sender, msg);
    }

    private void resolveResponseHandshake(HashMap<String, Object> message){
        ArrayList<ArrayList <String>> existingMessages = (ArrayList<ArrayList<String>>) message.get(KeyValues.KEY_EXISTING_MESSAGES);
        HashMap<String, Object> userDetails = (HashMap<String, Object>) message.get(KeyValues.KEY_USER_DETAILS);
        for (ArrayList<String> existingMessage : existingMessages){
            chatNetworkListener.messageReceived(existingMessage.get(0), existingMessage.get(1));
        }
        chatNetworkListener.userDetailsReceived(userDetails);
    }

    public synchronized void sendMessage(HashMap<String, Object> message){
        new Thread(() -> {
            try{
                System.out.println("Request: "+message);
                objectOutputStream.writeObject(message);
                objectOutputStream.reset();
            }catch (IOException e){
                e.printStackTrace();
                chatNetworkListener.serverUnreachable("Disconnected");
            }
        }).start();
    }

}
