package com.app.mychat.controller;

import com.app.mychat.Main;
import com.app.mychat.utils.classes.backend.ChatNetwork;
import com.app.mychat.utils.classes.backend.KeyValues;
import com.app.mychat.utils.classes.backend.MessageGenerator;
import com.app.mychat.utils.classes.backend.UserDetails;
import com.app.mychat.utils.classes.ui.Layout;
import com.app.mychat.utils.classes.ui.Message;
import com.app.mychat.utils.classes.ui.Person;
import com.app.mychat.utils.classes.ui.UserInterface;
import com.app.mychat.utils.interfaces.ChatNetworkListener;
import com.app.mychat.utils.interfaces.SidebarEventListener;
import com.app.mychat.utils.interfaces.WindowEventListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatScreenController implements ChatNetworkListener, SidebarEventListener {

    private ChatNetwork chatNetwork;

    @FXML private VBox vBoxMessages;
    @FXML private VBox vBoxActive;
    @FXML private VBox vBoxInactive;
    @FXML private TextArea txtMessage;
    @FXML private HBox hbox;

    private static WindowEventListener windowEventListener;
    private boolean isSidebarOpen;
    private AnchorPane sidebar;

    public static void addWindowEventListener(WindowEventListener windowEventListener){
        ChatScreenController.windowEventListener = windowEventListener;
    }

    @FXML
    public void initialize(){
        isSidebarOpen = false;
        sidebar = UserInterface.getSidebarPane(new Layout().getSidebarLayout());
        chatNetwork = new ChatNetwork(this);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        chatNetwork.sendMessage(MessageGenerator.generateHandshake(UserDetails.userName));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        chatNetwork.sendMessage(MessageGenerator.generateClientListRequestMessage(UserDetails.userName));
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

    @Override
    public void userDetailsReceived(HashMap<String, Object> userDetails) {
        UserDetails.firstName = (String) userDetails.get(KeyValues.KEY_FIRST_NAME);
        UserDetails.lastName = (String) userDetails.get(KeyValues.KEY_LAST_NAME);
        UserDetails.email = (String) userDetails.get(KeyValues.KEY_EMAIL);
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
            HashMap<String, Object> msg = MessageGenerator.generateTextMessage(UserDetails.userName, message);
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

    @FXML
    public void onSidebarClicked(){
        if(!isSidebarOpen){
            windowEventListener.onSidebarOpened();
            isSidebarOpen = true;
            hbox.getChildren().add(sidebar);
            SidebarController.addSidebarEventListener(this);
        }else {
            windowEventListener.onSidebarClosed();
            isSidebarOpen = false;
            hbox.getChildren().remove(sidebar);
        }
    }

    @Override
    public void onAccountDeleteRequested() {

    }

    @Override
    public void onAccountEditRequested(String firstName, String lastName, String password, String username) {

    }

    @Override
    public void onCheckUpdateRequested() {

    }
}
