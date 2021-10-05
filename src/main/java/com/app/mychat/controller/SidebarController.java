package com.app.mychat.controller;

import com.app.mychat.utils.classes.backend.Misc;
import com.app.mychat.utils.classes.backend.UserDetails;
import com.app.mychat.utils.classes.ui.Layout;
import com.app.mychat.utils.classes.ui.UserInterface;
import com.app.mychat.utils.interfaces.SidebarEventListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Consumer;

public class SidebarController {

    @FXML private Label lblUsername; // Label that displays the username

    private static SidebarEventListener sidebarEventListener;

    public static void addSidebarEventListener(SidebarEventListener sidebarEventListener){
        SidebarController.sidebarEventListener = sidebarEventListener;
    }

    @FXML
    public void initialize(){
        lblUsername.setText(UserDetails.userName);
    }

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
        UserInterface.getEditAccountStage(new Layout().getEditAccountLayout()).show();
        EditAccountController.addSidebarEventListener(sidebarEventListener);
    }

    // Account will be deleted
    @FXML
    public void onDeleteAccountClicked(MouseEvent mouseEvent){
        Misc.getConfirmationAlert("Are you sure you want to delete your account?").showAndWait().ifPresent(new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType buttonType) {
                if (buttonType == ButtonType.YES)
                    sidebarEventListener.onAccountDeleteRequested();
            }
        });
    }

    //Logouts the present logged in user
    @FXML
    public void onLogoutClicked(MouseEvent mouseEvent){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Log out?", ButtonType.YES, ButtonType.NO);
        a.showAndWait().ifPresent(new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType buttonType) {
                if(buttonType == ButtonType.YES){
                    System.exit(1);
                }
            }
        });
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
