package com.app.mychat.controller;

import com.app.mychat.utils.classes.backend.Misc;
import com.app.mychat.utils.classes.backend.UserDetails;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditAccount {

    @FXML private TextField txtAlias;
    @FXML private TextField txtFirstname;
    @FXML private TextField txtLastname;
    @FXML private TextField txtPassword;
    @FXML private TextField txtConfirmPassword;

    private String alias;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;

    @FXML
    public void initialize(){
        txtAlias.setText(UserDetails.userName);
        txtFirstname.setText(UserDetails.firstName);
        txtLastname.setText(UserDetails.lastName);
    }

    @FXML
    public void onApplyClicked(){

    }

    private boolean initTextVars(){
        alias = txtAlias.getText().trim();
        firstName = txtFirstname.getText().trim();
        lastName = txtLastname.getText().trim();
        password = txtPassword.getText().trim();
        confirmPassword =txtConfirmPassword.getText().trim();
    }

    private boolean checkEmptyFields(){
        if(alias.equals("")){
            Platform.runLater(() ->{
                Misc.getErrorAlert("Alias cant be empty!");
            });
            return false;
        }
        if(firstName.equals("")){
            Platform.runLater(() ->{
                Misc.getErrorAlert("First name cant be empty!");
            });
            return false;
        }
        if(lastName.equals("")){
            Platform.runLater(() ->{
                Misc.getErrorAlert("Last name cant be empty!");
            });
            return false;
        }
        if(!password.equals(confirmPassword)){
            Platform.runLater(() ->{
                Misc.getErrorAlert("Password and confirm password do not match!");
            });
            return false;
        }
        return true;
    }

}
