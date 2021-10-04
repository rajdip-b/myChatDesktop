package com.app.mychat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SidebarController {

    @FXML private Label lblUsername; // Label that displays the username

    // Darkens the label upon which the mouse enters
    @FXML
    public void onMouseEntered(MouseEvent mouseEvent){
        Label label = (Label) mouseEvent.getSource();
        label.setStyle("-fx-background-color: rgb(214, 214, 214)");
    }


    // Lightens the label that the mouse exits
    @FXML
    public void onMouseExited(MouseEvent mouseEvent){
        Label label = (Label) mouseEvent.getSource();
        label.setStyle("-fx-background-color: rgb(243, 243, 243)");
    }

    // To edit the user credentials
    @FXML
    public void onEditAccountClicked(MouseEvent mouseEvent){

    }

    // Account will be deleted
    @FXML
    public void onDeleteAccountClicked(MouseEvent mouseEvent){

    }

    //Logouts the present logged in user
    @FXML
    public void onLogoutClicked(MouseEvent mouseEvent){

    }

    // Updates the software
    @FXML
    public void onCheckForUpdatesClicked(MouseEvent mouseEvent){

    }

    // Opens the original repository in GitHub
    @FXML
    public void onGithubClicked(MouseEvent mouseEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/rajdip-b/myChatDesktop"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
