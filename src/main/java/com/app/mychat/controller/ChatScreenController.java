package com.app.mychat.controller;

import com.app.mychat.utils.classes.backend.*;
import com.app.mychat.utils.classes.ui.*;
import com.app.mychat.utils.interfaces.ChatNetworkListener;
import com.app.mychat.utils.interfaces.SidebarEventListener;
import com.app.mychat.utils.interfaces.WindowEventListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;

import static com.app.mychat.utils.classes.backend.KeyValues.*;

public class ChatScreenController implements ChatNetworkListener, SidebarEventListener {

    private ChatNetwork chatNetwork;

    @FXML private VBox vBoxMessages;
    @FXML private VBox vBoxActive;
    @FXML private VBox vBoxInactive;
    @FXML private TextArea txtMessage;
    @FXML private AnchorPane sidebarPane;
    @FXML private AnchorPane base;
    @FXML private ScrollPane msgScrollPane;
    @FXML private ScrollPane actv;
    @FXML private ScrollPane inactv;
    @FXML private Button send;

    private final Animations animations;

    private boolean isSidebarOpen;
    private AnchorPane sidebar;
    private static WindowEventListener windowEventListener;

    public ChatScreenController(){
        animations = new Animations();
    }
    public static void addWindowEventListener(WindowEventListener windowEventListener) {
        ChatScreenController.windowEventListener = windowEventListener;
    }

    @FXML
    public void initialize(){
        isSidebarOpen = false;
        sidebarPane.setPrefWidth(0);
        msgScrollPane.vvalueProperty().bind(vBoxMessages.heightProperty());
        sidebar = UserInterface.getSidebarPane(new Layout().getSidebarLayout());
        //------------------------------------------------------------------//
//        chatNetwork = new ChatNetwork(this);
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        chatNetwork.sendMessage(MessageGenerator.generateHandshake(UserDetails.userName));
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        chatNetwork.sendMessage(MessageGenerator.generateClientListRequestMessage(UserDetails.userName));
//        animations.setAnchorPaneAnimation(base);
//        animations.setScrollPaneAnimation(actv);
//        animations.setScrollPaneAnimation(inactv);
//        animations.setSendButtonAnimation(send);
        //---------------------------------------------------------------------------------------------//
    }

    @FXML
    public void onSendClicked(){
//        String message = txtMessage.getText().trim();
//        if (!message.equals("")) {
//            HashMap<String, Object> msg = MessageGenerator.generateTextMessage(UserDetails.userName, message);
//            chatNetwork.sendMessage(msg);
//            Platform.runLater(() -> {
//                appendToMessagesSection(new Message("You", message));
//                txtMessage.setText("");
//            });
//        }
    }

    @FXML
    public void onEnterPressed(KeyEvent keyEvent){
//        if (keyEvent.getCode() == KeyCode.ENTER)
//            onSendClicked();
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
//        Platform.runLater(() -> {
//            Message msg = new Message(sender, message);
//            appendToMessagesSection(msg);
//        });
    }

    @Override
    public void serverUnreachable(String message) {
//        System.out.println(message);
//        Platform.runLater(() -> {
//            Misc.getErrorAlert(message).showAndWait();
//            System.exit(1);
//        });
    }

    @Override
    public void clientListRecieved(ArrayList<HashMap<String, Object>> activeUsers, ArrayList<HashMap<String, Object>> inactiveUsers) {
//        Platform.runLater(() -> {
//            vBoxInactive.getChildren().clear();
//            vBoxActive.getChildren().clear();
//            for (HashMap<String, Object> user : activeUsers){
//                String firstName = (String) user.get(KEY_FIRST_NAME);
//                String lastName =(String) user.get(KEY_LAST_NAME);
//                String email = (String) user.get(KEY_EMAIL);
//                String userName = (String) user.get(KEY_USERNAME);
//                Person person = new Person(firstName, lastName, email, userName, Person.STATUS_ACTIVE, this);
//                appendToActiveUsersListSection(person);
//            }
//            for (HashMap<String, Object> user : inactiveUsers){
//                String firstName = (String) user.get(KEY_FIRST_NAME);
//                String lastName =(String) user.get(KEY_LAST_NAME);
//                String email = (String) user.get(KEY_EMAIL);
//                String userName = (String) user.get(KEY_USERNAME);
//                Person person = new Person(firstName, lastName, email, userName, Person.STATUS_INACTIVE, this);
//                appendToInactiveUsersListSection(person);
//            }
//        });
    }

    @Override
    public void userDetailsReceived(HashMap<String, Object> userDetails) {
//        UserDetails.firstName = (String) userDetails.get(KeyValues.KEY_FIRST_NAME);
//        UserDetails.lastName = (String) userDetails.get(KeyValues.KEY_LAST_NAME);
//        UserDetails.email = (String) userDetails.get(KeyValues.KEY_EMAIL);
    }

    @Override
    public void onPersonContainerClicked(AnchorPane personContainer){
        if (!isSidebarOpen){
            windowEventListener.onSidebarOpened();
            isSidebarOpen = true;
        }
        sidebarPane.getChildren().clear();
        sidebarPane.getChildren().add(personContainer);
    }

    @Override
    public void onAccountDeleteRequested() {
//        chatNetwork.sendMessage(MessageGenerator.generateDeleteAccountRequestMessage(UserDetails.userName));
    }

    @Override
    public void onAccountEditWithPasswordAndWithUsernameRequested(String firstName, String lastName, String password, String username) {
//        chatNetwork.sendMessage(MessageGenerator.generateEditAccountWithPasswordAndWithUsernameRequestMessage(username, firstName, lastName, password, UserDetails.email));
//        System.out.println("With password and with username");
    }

    @Override
    public void onAccountEditWithoutPasswordAndWithUsernameRequested(String firstName, String lastName, String username) {
//        chatNetwork.sendMessage(MessageGenerator.generateEditAccountWithoutPasswordAndWithUsernameRequestMessage(username, firstName, lastName, UserDetails.email));
//        System.out.println("Without password and with username");
    }

    @Override
    public void onAccountEditWithPasswordAndWithoutUsernameRequested(String firstName, String lastName, String password) {
        chatNetwork.sendMessage(MessageGenerator.generateEditAccountWithPasswordAndWithoutUsernameRequestMessage(UserDetails.userName, password, firstName, lastName, UserDetails.email));
        System.out.println("With password and without username");
    }

    @Override
    public void onAccountEditWithoutPasswordAndWithoutUsernameRequested(String firstName, String lastName) {
//        chatNetwork.sendMessage(MessageGenerator.generateEditAccountWithoutPasswordAndWithoutUsernameRequestMessage(UserDetails.userName, firstName, lastName, UserDetails.email));
//        System.out.println("Without password and without username");
    }

    @Override
    public void editAccountResponseReceived(int responseCode, String responseMessage) {
//        switch (responseCode){
//            case RESPONSE_CODE_SUCCESS -> Platform.runLater(() -> {
//                Misc.getInformationAlert(responseMessage).showAndWait();
//                Platform.exit();
//                System.exit(1);
//            });
//            case RESPONSE_CODE_FAILURE -> Platform.runLater(() -> Misc.getErrorAlert(responseMessage).show());
//        }
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
//        msgScrollPane.setVvalue(2.0);
    }
}
