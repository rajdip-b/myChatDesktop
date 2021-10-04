package com.app.mychat.utils.classes.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class Layout {

    public Parent getSidebarLayout(){
        Parent root = null;
        try{
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fragments/Sidebar.fxml")));
        }catch (IOException e){
            System.exit(1);
        }
        return root;
    }

    public Parent getChatScreenLayout() {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/layouts/ChatScreenGUI.fxml")));
        } catch (IOException e) {
            System.exit(1);
        }
        return root;
    }

    public Parent getLoginLayout() {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/layouts/Login.fxml")));
        } catch (IOException e) {
            System.exit(1);
        }
        return root;
    }

    public Parent getSignupLayout() {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/layouts/Signup.fxml")));
        } catch (IOException e) {
            System.exit(1);
        }
        return root;
    }

    public Parent getEditAccountLayout() {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/layouts/EditAccount.fxml")));
        } catch (IOException e) {
            System.exit(1);
        }
        return root;
    }

    public Parent getMessageContainerLayout() {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fragments/MessageContainer.fxml")));
        } catch (IOException e) {
            System.exit(1);
        }
        return root;
    }

    public Parent getPersonContainerLayout() {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fragments/PersonContainer.fxml")));
        } catch (IOException e) {
            System.exit(1);
        }
        return root;
    }

}
