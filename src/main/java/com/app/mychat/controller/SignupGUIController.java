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

public class SignupGUIController implements CredentialNetworkListener {

    private final CredentialNetwork credentialNetwork;
    private static WindowEventListener windowEventListener;

    @FXML public TextField txtFirstName;
    @FXML public TextField txtLastName;
    @FXML public TextField txtEmail;
    @FXML public TextField txtAlias;
    @FXML public Button btnRegister;
    @FXML public Label lblErrFirstName;
    @FXML public Label lblErrLastName;
    @FXML public Label lblErrEmail;
    @FXML public Label lblErrAlias;
    @FXML public Label lblErrPassword;
    @FXML public PasswordField txtPassword;
    @FXML public PasswordField txtConfirmPassword;
    private final Animations animations;

    public SignupGUIController() {
        credentialNetwork = new CredentialNetwork(this);
        animations = new Animations();
    }

    public static void addWindowEventListener(WindowEventListener windowEventListener){
        SignupGUIController.windowEventListener = windowEventListener;
    }

    @FXML
    public void initialize(){
        animations.setButtonAnimation(btnRegister);
//        animations.setTextFieldAnimation(txtFirstName);
//        animations.setTextFieldAnimation(txtLastName);
//        animations.setTextFieldAnimation(txtEmail);
//        animations.setTextFieldAnimation(txtAlias);
//        animations.setPasswordFieldAnimation(txtPassword);
//        animations.setPasswordFieldAnimation(txtConfirmPassword);
    }

    @FXML
    public void onSignupClicked() {
        lblErrPassword.setText("");
        lblErrAlias.setText("");
        lblErrEmail.setText("");
        lblErrLastName.setText("");
        lblErrFirstName.setText("");
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String email = txtEmail.getText().trim();
        String alias = txtAlias.getText().trim();
        String password = txtPassword.getText().trim();
        String confirmPassword = txtConfirmPassword.getText().trim();

        boolean flag = true;
        if (!isNameAMatch(firstName)){
            lblErrFirstName.setText("Invalid first name!");
            flag = false;
        }
        if (!isNameAMatch(lastName)) {
            lblErrLastName.setText("Invalid last name!");
            flag = false;
        }
        if (!isEmailAMatch(email)) {
            lblErrEmail.setText("Invalid email!");
            flag = false;
        }
        if (!isPasswordAMatch(password)) {
            lblErrPassword.setText("Password must have 8 to 20 characters including a-z, A-Z, 0-9 and !@#$");
            flag = false;
        }
        if (!isAliasAMatch(alias)) {
            lblErrAlias.setText("Alias must have 5 to 20 characters in including a-z, A-Z and 0-9");
            flag = false;
        }
        if(!password.equals(confirmPassword)){
            lblErrPassword.setText("Password and confirm password doesn't match!");
            System.out.println(password+" "+confirmPassword);
            flag = false;
        }
        if (flag) {
            btnRegister.setDisable(true);
            password = hashUp(password);
            credentialNetwork.attemptSignup(MessageGenerator.generateSignupRequestMessage(firstName, lastName, email, alias, password));
        }
    }

    public void onKeyPressed(KeyEvent event){
        KeyCode keyCode = event.getCode();
        if (keyCode == KeyCode.ENTER)
            onSignupClicked();
    }

    @Override
    public void onConnectionUnSuccessful(String message) {
        Platform.runLater(() ->{
            btnRegister.setDisable(false);
            new Alert(Alert.AlertType.ERROR, message).show();
        });
    }

    @Override
    public void onErrorWhileOperation(String message) {
        Platform.runLater(() ->{
            btnRegister.setDisable(false);
            new Alert(Alert.AlertType.ERROR, message).show();
        });
    }

    @Override
    public void onOperationSuccessful(String message, String username) {
        Platform.runLater(() ->{
            btnRegister.setDisable(false);
            new Alert(Alert.AlertType.INFORMATION, message).showAndWait();
            windowEventListener.onLoginScreenRequested();
        });
    }

}
