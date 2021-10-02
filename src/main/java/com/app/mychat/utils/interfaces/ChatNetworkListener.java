package com.app.mychat.utils.interfaces;

import java.util.ArrayList;

public interface ChatNetworkListener {

    void messageReceived(String sender, String message);

    void serverUnreachable(String message);

    void clientListRecieved(ArrayList<String> activeUsers, ArrayList<String> inactiveUsers);

}
