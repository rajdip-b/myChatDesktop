package com.app.mychat.controller;

import java.util.HashMap;

import com.app.mychat.utils.classes.Animations;
import com.app.mychat.utils.classes.CredentialNetwork;
import com.app.mychat.utils.classes.KeyValues;
import com.app.mychat.utils.interfaces.CredentialNetworkListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static com.app.mychat.utils.classes.Hash.hashUp;
import static com.app.mychat.utils.classes.Regex.*;

public class SignupGUIController implements CredentialNetworkListener {

    private final CredentialNetwork credentialNetwork;

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
        if (isNameAMatch(firstName)){
            lblErrFirstName.setText("Invalid first name!");
            flag = false;
        }
        if (isNameAMatch(lastName)) {
            lblErrLastName.setText("Invalid last name!");
            flag = false;
        }
        if (!isEmailAMatch(email)) {
            lblErrEmail.setText("Invalid email!");
            flag = false;
        }
        if (isPasswordAMatch(password)) {
            lblErrPassword.setText("Password must have 8 to 20 characters including a-z, A-Z, 0-9 and !@#$");
            flag = false;
        }
        if (isAliasAMatch(alias)) {
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
            HashMap<String, Object> message = new HashMap<>();
            message.put(KeyValues.KEY_QUERY, KeyValues.QUERY_SIGNUP_REQUEST);
            message.put(KeyValues.KEY_FIRST_NAME, firstName);
            message.put(KeyValues.KEY_LAST_NAME, lastName);
            message.put(KeyValues.KEY_PASSWORD, password);
            message.put(KeyValues.KEY_EMAIL, email);
            message.put(KeyValues.KEY_USERNAME, alias);
            credentialNetwork.attemptSignup(message);
        }
    }

    @FXML
    public void initialize(){
        animations.setButtonAnimation(btnRegister);
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
    public void onOperationSuccessful(String message) {
        Platform.runLater(() ->{
            btnRegister.setDisable(false);
            new Alert(Alert.AlertType.INFORMATION, message).show();
        });
    }

}
