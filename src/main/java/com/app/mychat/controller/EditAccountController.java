package com.app.mychat.controller;

import com.app.mychat.utils.classes.backend.Misc;
import com.app.mychat.utils.classes.backend.Regex;
import com.app.mychat.utils.classes.backend.UserDetails;
import com.app.mychat.utils.interfaces.SidebarEventListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EditAccountController {

    @FXML private TextField txtAlias;
    @FXML private TextField txtFirstname;
    @FXML private TextField txtLastname;
    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtConfirmPassword;

    private String alias;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;

    private static SidebarEventListener sidebarEventListener;


    public static void addSidebarEventListener(SidebarEventListener sidebarEventListener){
        EditAccountController.sidebarEventListener = sidebarEventListener;
    }

    @FXML
    public void onApplyClicked(){
        initTextVars();
        if (checkFieldData()){
            System.out.println(String.format("Alias: %s FirstName: %s LastName: %s Password: %s Email: %s", alias, firstName, lastName, password, UserDetails.email));
            if (password.equals("") && alias.equals(""))
                sidebarEventListener.onAccountEditWithoutPasswordAndWithoutUsernameRequested(firstName, lastName);
            else if(password.equals("") && !alias.equals(""))
                sidebarEventListener.onAccountEditWithoutPasswordAndWithUsernameRequested(firstName, lastName, alias);
            else if(!password.equals("") && alias.equals(""))
                sidebarEventListener.onAccountEditWithPasswordAndWithoutUsernameRequested(firstName, lastName, password);
            else if(!password.equals("") && !alias.equals(""))
                sidebarEventListener.onAccountEditWithPasswordAndWithUsernameRequested(firstName, lastName, password, alias);
        }
    }

    private void initTextVars(){
        alias = txtAlias.getText().trim();
        firstName = txtFirstname.getText().trim();
        lastName = txtLastname.getText().trim();
        password = txtPassword.getText().trim();
        confirmPassword =txtConfirmPassword.getText().trim();
    }

    private boolean checkFieldData(){
        if (password.equals("") && confirmPassword.equals("") && lastName.equals("") && firstName.equals("") && alias.equals("")){
            Platform.runLater(() -> {
                Misc.getErrorAlert("All fields cant be empty!").show();
            });
            return false;
        }
        if (!alias.equals("")) {
            if (!Regex.isAliasAMatch(alias)) {
                Platform.runLater(() -> {
                    Misc.getErrorAlert("Invalid alias!").show();
                });
                return false;
            }
        }
        if (!firstName.equals("")) {
            if (!Regex.isNameAMatch(firstName)) {
                Platform.runLater(() -> {
                    Misc.getErrorAlert("Invalid first name!").show();
                });
                return false;
            }
        }else{
            firstName = UserDetails.firstName;
        }
        if (!lastName.equals("")) {
            if (!Regex.isNameAMatch(lastName)) {
                Platform.runLater(() -> {
                    Misc.getErrorAlert("Invalid last name!").show();
                });
                return false;
            }
        }else{
            lastName = UserDetails.lastName;
        }
        if (!password.equals("") || !confirmPassword.equals("")) {
            if(password.equals(confirmPassword)){
                if (!Regex.isPasswordAMatch(password)){
                    Platform.runLater(() -> {
                        Misc.getErrorAlert("Weak password!").show();
                    });
                }else{
                    password = Misc.hashUp(password);
                }
            }else{
                Platform.runLater(() -> {
                    Misc.getErrorAlert("Password and confirm password do not match!").show();
                });
                return false;
            }
        }
        return true;
    }

}
