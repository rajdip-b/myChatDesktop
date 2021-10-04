package com.app.mychat.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class SidebarController {

    @FXML private Label lblUsername; // Label that displays the username

    @FXML
    public void onMouseEntered(MouseEvent mouseEvent){
        Label label = (Label) mouseEvent.getSource();
        label.setStyle("-fx-background-color: rgb(214, 214, 214)");
    }

    @FXML
    public void onMouseExited(MouseEvent mouseEvent){
        Label label = (Label) mouseEvent.getSource();
        label.setStyle("-fx-background-color: rgb(243, 243, 243)");
    }

    @FXML
    public void onEditAccountClicked(MouseEvent mouseEvent){ // To edit the user credentials

    }

    @FXML
    public void onDeleteAccountClicked(MouseEvent mouseEvent){  // Account will be deleted

    }

    @FXML
    public void onLogoutClicked(MouseEvent mouseEvent){ // Logouts the present logged in user

    }

    @FXML
    public void onCheckForUpdatesClicked(MouseEvent mouseEvent){ // Updates the chat window

    }

    @FXML
    public void onGithubClicked(MouseEvent mouseEvent){ // Opens the original repository in GIT-Hub

    }

}
