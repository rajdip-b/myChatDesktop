package com.app.mychat.controller;

import java.util.HashMap;

import com.app.mychat.utils.classes.Animations;
import com.app.mychat.utils.classes.CredentialNetwork;
import com.app.mychat.utils.classes.KeyValues;
import com.app.mychat.utils.interfaces.CredentialNetworkListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static com.app.mychat.utils.classes.Hash.hashUp;
import static com.app.mychat.utils.classes.Regex.*;

public class LoginGUIController implements CredentialNetworkListener {

    private final CredentialNetwork credentialNetwork;

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
            HashMap<String, Object> message = new HashMap<>();
            password = hashUp(password);
            message.put(KeyValues.KEY_QUERY, KeyValues.QUERY_LOGIN_REQUEST);
            message.put(KeyValues.KEY_USERNAME, username);
            message.put(KeyValues.KEY_PASSWORD, password);
            credentialNetwork.attemptLogin(message);
        }
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
    public void onOperationSuccessful(String message) {
        Platform.runLater(() ->{
            btnLogin.setDisable(false);
            new Alert(Alert.AlertType.INFORMATION, message).show();
        });
    }

}