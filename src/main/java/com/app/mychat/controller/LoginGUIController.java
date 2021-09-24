package com.app.mychat.controller;

import java.util.HashMap;
import java.util.regex.Pattern;

import com.app.mychat.utils.classes.CredentialNetwork;
import com.app.mychat.utils.classes.KeyValues;
import com.app.mychat.utils.interfaces.CredentialNetworkListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static com.app.mychat.utils.classes.Hash.hashUp;

public class LoginGUIController implements CredentialNetworkListener {

    private CredentialNetwork credentialNetwork;
    private static final String patternAlias = "^[a-zA-Z0-9]{5,20}$";
    private static final String patternPassword = "^[a-zA-Z0-9!@#$]{8,20}$";

    @FXML private PasswordField txtPassword;
    @FXML private TextField txtAlias;
    @FXML private Button btnLogin;
    @FXML private Label lblErrAlias;
    @FXML private Label lblErrPassword;

    public LoginGUIController() {
        credentialNetwork = new CredentialNetwork(this);
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

    //-----------BUTTON ANIMATIONS----------------

    @FXML
    public void onMouseEntered(){

    }

    @FXML
    public void onMouseExited(){

    }

    //--------------------------------------------

    private boolean isPasswordAMatch(String password) {
        if (Pattern.matches(patternPassword, password))
            return true;
        return false;
    }

    private boolean isAliasAMatch(String alias) {
        if (Pattern.matches(patternAlias, alias))
            return true;
        return false;
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