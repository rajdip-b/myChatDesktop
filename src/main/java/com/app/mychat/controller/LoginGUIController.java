package com.app.mychat.controller;

import com.app.mychat.utils.classes.ui.Animations;
import com.app.mychat.utils.classes.backend.CredentialNetwork;
import com.app.mychat.utils.classes.backend.MessageGenerator;
import com.app.mychat.utils.interfaces.CredentialNetworkListener;
import com.app.mychat.utils.interfaces.WindowEventListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static com.app.mychat.utils.classes.backend.Misc.hashUp;
import static com.app.mychat.utils.classes.backend.Regex.*;

public class LoginGUIController implements CredentialNetworkListener {

    private final CredentialNetwork credentialNetwork;
    private static WindowEventListener windowEventListener;

    @FXML private PasswordField txtPassword;
    @FXML private TextField txtAlias;
    @FXML private Button btnLogin;
    @FXML private Label lblErrAlias;
    @FXML private Label lblErrPassword;
    private final Animations animations;

    public LoginGUIController() {
        credentialNetwork = new CredentialNetwork(this);
        animations = new Animations();
    }

    public static void addWindowEventListener(WindowEventListener windowEventListener){
        LoginGUIController.windowEventListener = windowEventListener;
    }

    @FXML
    public void initialize(){
        animations.setButtonAnimation(btnLogin);
    }

    @FXML
    public void onLoginClicked() {
        String username = txtAlias.getText().trim();
        String password = txtPassword.getText().trim();
        boolean flag = true;
        if(!isAliasAMatch(username)){
            flag = false;
            lblErrAlias.setText("Invalid alias!");
        }
        if(!isPasswordAMatch(password)){
            flag = false;
            lblErrPassword.setText("Invalid password!");
        }
        if(flag){
            btnLogin.setDisable(true);
            lblErrPassword.setText("");
            lblErrAlias.setText("");
            password = hashUp(password);
            credentialNetwork.attemptLogin(MessageGenerator.generateLoginRequestMessage(username, password));
        }
    }

    @FXML
    public void onCreateAccountClicked(){
        windowEventListener.onSignupScreenRequested();
    }

    public void onKeyPressed(KeyEvent event){
        KeyCode keyCode = event.getCode();
        if (keyCode == KeyCode.ENTER)
            onLoginClicked();
    }

    @Override
    public void onConnectionUnSuccessful(String message) {
        Platform.runLater(() ->{
            btnLogin.setDisable(false);
            new Alert(Alert.AlertType.ERROR, message).show();
        });
    }

    @Override
    public void onErrorWhileOperation(String message) {
        Platform.runLater(() ->{
            btnLogin.setDisable(false);
            new Alert(Alert.AlertType.ERROR, message).show();
        });
    }

    @Override
    public void onOperationSuccessful(String message, String username) {
        Platform.runLater(() ->{
            btnLogin.setDisable(false);
            new Alert(Alert.AlertType.INFORMATION, message).showAndWait();
            windowEventListener.onChatScreenRequested(username);
        });
    }

}