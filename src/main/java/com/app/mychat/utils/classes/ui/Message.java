package com.app.mychat.utils.classes.ui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    private AnchorPane anchorPane;
    private Text txtMessage;
    private Label txtPerson;
    private Label txtTimeStamp;

    public Message(String sender, String message){
        this();
        txtPerson.setText(sender);
        txtMessage.setText(message);
        txtTimeStamp.setText(new SimpleDateFormat("HH:mm dd/mm/yyyy").format(new Date()).toString());
        anchorPane.getChildren().add(txtPerson);
        anchorPane.getChildren().add(txtMessage);
        anchorPane.getChildren().add(txtTimeStamp);
    }

    public Message(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fragments/MessageContainer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        txtMessage = (Text) root.getChildrenUnmodifiable().get(1);
        txtPerson = (Label) root.getChildrenUnmodifiable().get(0);
        txtTimeStamp = (Label) root.getChildrenUnmodifiable().get(2);
        anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(70);
        anchorPane.setPrefWidth(753);
    }

    public AnchorPane getMessageUI(){
        return anchorPane;
    }

}
