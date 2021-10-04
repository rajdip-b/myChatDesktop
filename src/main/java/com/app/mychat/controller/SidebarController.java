package com.app.mychat.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class SidebarController {

    @FXML private Label lblUsername;

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
    public void onEditAccountClicked(MouseEvent mouseEvent){

    }

    @FXML
    public void onDeleteAccountClicked(MouseEvent mouseEvent){

    }

    @FXML
    public void onLogoutClicked(MouseEvent mouseEvent){

    }

    @FXML
    public void onCheckForUpdatesClicked(MouseEvent mouseEvent){

    }

    @FXML
    public void onGithubClicked(MouseEvent mouseEvent){

    }

}
