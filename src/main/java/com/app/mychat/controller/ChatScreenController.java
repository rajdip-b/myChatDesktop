package com.app.mychat.controller;

import com.app.mychat.Main;
import com.app.mychat.utils.classes.backend.ChatNetwork;
import com.app.mychat.utils.classes.backend.MessageGenerator;
import com.app.mychat.utils.classes.ui.Message;
import com.app.mychat.utils.classes.ui.Person;
import com.app.mychat.utils.interfaces.ChatNetworkListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatScreenController implements ChatNetworkListener {

    private ChatNetwork chatNetwork;

    @FXML private VBox vBoxMessages;
    @FXML private VBox vBoxActive;
    @FXML private VBox vBoxInactive;
    @FXML private TextArea txtMessage;

    private String username;

    @FXML
    public void initialize(){
        username = Main.userName;
        chatNetwork = new ChatNetwork(this);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        chatNetwork.sendMessage(MessageGenerator.generateHandshake(username));
        HashMap<String, Object> clientList = MessageGenerator.generateClientListRequestMessage(username);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        chatNetwork.sendMessage(clientList);
    }

    @Override
    public void messageReceived(String sender, String message) {
        Platform.runLater(() -> {
            Message msg = new Message(sender, message);
            appendToMessagesSection(msg);
        });
    }

    @Override
    public void serverUnreachable(String message) {
        System.out.println(message);
        Platform.runLater(() -> {
            new Alert(Alert.AlertType.ERROR, message).showAndWait();
            System.exit(1);
        });
    }

    @Override
    public void clientListRecieved(ArrayList<String> activeUsers, ArrayList<String> inactiveUsers) {
        Platform.runLater(() -> {
            vBoxInactive.getChildren().clear();
            vBoxActive.getChildren().clear();
            for (String userName : activeUsers){
                Person person = new Person(userName, Person.STATUS_ACTIVE);
                appendToActiveUsersListSection(person);
            }
            for (String userName : inactiveUsers){
                Person person = new Person(userName, Person.STATUS_INACTIVE);
                appendToInactiveUsersListSection(person);
            }
        });
    }

    private void appendToActiveUsersListSection(Person person){
        AnchorPane anchorPane = person.getPersonUI();
        vBoxActive.getChildren().add(anchorPane);
    }

    private void appendToInactiveUsersListSection(Person person){
        AnchorPane anchorPane = person.getPersonUI();
        vBoxInactive.getChildren().add(anchorPane);
    }

    private void appendToMessagesSection(Message message){
        AnchorPane anchorPane = message.getMessageUI();
        vBoxMessages.getChildren().add(anchorPane);
    }

    @FXML
    public void onSendClicked(){
        String message = txtMessage.getText().trim();
        if (!message.equals("")) {
            HashMap<String, Object> msg = MessageGenerator.generateTextMessage(username, message);
            chatNetwork.sendMessage(msg);
            Platform.runLater(() -> {
                appendToMessagesSection(new Message("You", message));
                txtMessage.setText("");
            });
        }
    }

    @FXML
    public void onEnterPressed(KeyEvent keyEvent){
        if (keyEvent.getCode() == KeyCode.ENTER)
            onSendClicked();
    }

}
