package com.app.mychat.utils.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

public interface ChatNetworkListener {

    void messageReceived(String sender, String message);
    void serverUnreachable(String message);
    void userDetailsReceived(HashMap<String, Object> userDetails);
    void clientListRecieved(ArrayList<HashMap<String, Object>> activeUsers, ArrayList<HashMap<String, Object>> inactiveUsers);
    void editAccountResponseReceived(int responseCode, String responseMessage);

}
