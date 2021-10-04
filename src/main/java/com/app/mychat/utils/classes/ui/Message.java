package com.app.mychat.utils.classes.ui;

import javafx.scene.layout.AnchorPane;

public class Message {

    private AnchorPane anchorPane;

    public Message(String sender, String message){
        anchorPane = UserInterface.getMessageContainerPane(sender, message, new Layout().getMessageContainerLayout());
    }

    public AnchorPane getMessageUI(){
        return anchorPane;
    }

}
