package com.app.mychat.controller;

import com.app.mychat.utils.classes.backend.*;
import com.app.mychat.utils.classes.ui.Layout;
import com.app.mychat.utils.classes.ui.Message;
import com.app.mychat.utils.classes.ui.Person;
import com.app.mychat.utils.classes.ui.UserInterface;
import com.app.mychat.utils.interfaces.ChatNetworkListener;
import com.app.mychat.utils.interfaces.SidebarEventListener;
import static com.app.mychat.utils.classes.backend.KeyValues.*;

import com.app.mychat.utils.interfaces.WindowEventListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatScreenController implements ChatNetworkListener, SidebarEventListener {

    private ChatNetwork chatNetwork;

    @FXML private VBox vBoxMessages;
    @FXML private VBox vBoxActive;
    @FXML private VBox vBoxInactive;
    @FXML private TextArea txtMessage;
    @FXML private AnchorPane sidebarPane;
    @FXML private ScrollPane msgScrollPane;

    private boolean isSidebarOpen;
    private AnchorPane sidebar;
    private static WindowEventListener windowEventListener;

    public static void addWindowEventListener(WindowEventListener windowEventListener) {
        ChatScreenController.windowEventListener = windowEventListener;
    }

    @FXML
    public void initialize(){
        isSidebarOpen = false;
        sidebarPane.setPrefWidth(0);
        msgScrollPane.vvalueProperty().bind(vBoxMessages.heightProperty());
        sidebar = UserInterface.getSidebarPane(new Layout().getSidebarLayout());
        test();
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
            sidebarPane.getChildren().clear();
            sidebarPane.getChildren().add(sidebar);
            SidebarController.addSidebarEventListener(this);
        }else {
            windowEventListener.onSidebarClosed();
            isSidebarOpen = false;
            sidebarPane.getChildren().clear();
        }
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
            Misc.getErrorAlert(message).showAndWait();
            System.exit(1);
        });
    }

    @Override
    public void clientListRecieved(ArrayList<HashMap<String, Object>> activeUsers, ArrayList<HashMap<String, Object>> inactiveUsers) {
        Platform.runLater(() -> {
            vBoxInactive.getChildren().clear();
            vBoxActive.getChildren().clear();
            for (HashMap<String, Object> user : activeUsers){
                String firstName = (String) user.get(KEY_FIRST_NAME);
                String lastName =(String) user.get(KEY_LAST_NAME);
                String email = (String) user.get(KEY_EMAIL);
                String userName = (String) user.get(KEY_USERNAME);
                Person person = new Person(firstName, lastName, email, userName, Person.STATUS_ACTIVE, this);
                PersonHolder.addPerson(person);
                appendToActiveUsersListSection(person);
            }
            for (HashMap<String, Object> user : inactiveUsers){
                String firstName = (String) user.get(KEY_FIRST_NAME);
                String lastName =(String) user.get(KEY_LAST_NAME);
                String email = (String) user.get(KEY_EMAIL);
                String userName = (String) user.get(KEY_USERNAME);
                Person person = new Person(firstName, lastName, email, userName, Person.STATUS_INACTIVE, this);
                appendToInactiveUsersListSection(person);
            }
            UserDetails.self = PersonHolder.getPersonByUsername(UserDetails.userName);
        });
    }

    @Override
    public void userDetailsReceived(HashMap<String, Object> userDetails) {
        UserDetails.firstName = (String) userDetails.get(KeyValues.KEY_FIRST_NAME);
        UserDetails.lastName = (String) userDetails.get(KeyValues.KEY_LAST_NAME);
        UserDetails.email = (String) userDetails.get(KeyValues.KEY_EMAIL);
    }

    @Override
    public void onPersonContainerClicked(AnchorPane personContainer){
        if (!isSidebarOpen){
            windowEventListener.onSidebarOpened();
            isSidebarOpen = true;
            sidebarPane.getChildren().clear();
            sidebarPane.getChildren().add(personContainer);
        }else{
            sidebarPane.getChildren().clear();
            sidebarPane.getChildren().add(personContainer);
        }
    }

    @Override
    public void onAccountDeleteRequested() {
        chatNetwork.sendMessage(MessageGenerator.generateDeleteAccountRequestMessage(UserDetails.userName));
    }

    @Override
    public void onAccountEditWithPasswordAndWithUsernameRequested(String firstName, String lastName, String password, String username) {
        chatNetwork.sendMessage(MessageGenerator.generateEditAccountWithPasswordAndWithUsernameRequestMessage(username, firstName, lastName, password, UserDetails.email));
        System.out.println("With password and with username");
    }

    @Override
    public void onAccountEditWithoutPasswordAndWithUsernameRequested(String firstName, String lastName, String username) {
        chatNetwork.sendMessage(MessageGenerator.generateEditAccountWithoutPasswordAndWithUsernameRequestMessage(username, firstName, lastName, UserDetails.email));
        System.out.println("Without password and with username");
    }

    @Override
    public void onAccountEditWithPasswordAndWithoutUsernameRequested(String firstName, String lastName, String password) {
        chatNetwork.sendMessage(MessageGenerator.generateEditAccountWithPasswordAndWithoutUsernameRequestMessage(UserDetails.userName, password, firstName, lastName, UserDetails.email));
        System.out.println("With password and without username");
    }

    @Override
    public void onAccountEditWithoutPasswordAndWithoutUsernameRequested(String firstName, String lastName) {
        chatNetwork.sendMessage(MessageGenerator.generateEditAccountWithoutPasswordAndWithoutUsernameRequestMessage(UserDetails.userName, firstName, lastName, UserDetails.email));
        System.out.println("Without password and without username");
    }

    @Override
    public void editAccountResponseReceived(int responseCode, String responseMessage) {
        switch (responseCode){
            case RESPONSE_CODE_SUCCESS -> {
                Platform.runLater(() -> {
                    Misc.getInformationAlert(responseMessage).showAndWait();
                    Platform.exit();
                    System.exit(1);
                });
            }
            case RESPONSE_CODE_FAILURE -> {
                Platform.runLater(() ->{
                    Misc.getErrorAlert(responseMessage).show();
                });
            }
        }
    }

    @Override
    public void onTypingUpdateReceived(String username, String status) {
        Platform.runLater(() -> {
            PersonHolder.getPersonByUsername(username).setStatus(status);
        });
    }

    @Override
    public void onCheckUpdateRequested() {
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

    private void test(){
        txtMessage.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("")) {
                UserDetails.self.setStatus(Person.STATUS_ACTIVE);
                chatNetwork.sendMessage(MessageGenerator.generateTypingStatusUpdateMessage(UserDetails.userName, TYPING_STATUS_OFF));
            }
            else if (UserDetails.self.getStatus().equals(Person.STATUS_ACTIVE)) {
                UserDetails.self.setStatus(Person.STATUS_TYPING);
                chatNetwork.sendMessage(MessageGenerator.generateTypingStatusUpdateMessage(UserDetails.userName, TYPING_STATUS_ON));
            }
        });
    }

}
