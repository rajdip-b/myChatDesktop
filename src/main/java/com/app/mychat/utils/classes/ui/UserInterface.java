package com.app.mychat.utils.classes.ui;

import com.app.mychat.utils.interfaces.SidebarEventListener;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserInterface {

    public static Stage getLoginStage(Parent layout){
        Stage stage = new Stage();
        stage.setScene(new Scene(layout));
        stage.setResizable(false);
        stage.setTitle("myChat - Login");
        return stage;
    }

    public static Stage getSignupStage(Parent layout){
        Stage stage = new Stage();
        stage.setScene(new Scene(layout));
        stage.setResizable(false);
        stage.setTitle("myChat - Login");
        return stage;
    }

    public static Stage getChatScreenStage(Parent layout){
        Stage stage = new Stage();
        stage.setTitle("myChat - Chat screen");
        stage.setScene(new Scene(layout));
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(1);
        });
        return stage;
    }

    public static Stage getEditAccountStage(Parent layout){
        Stage stage = new Stage();
        stage.setTitle("myChat - Edit Account");
        stage.setScene(new Scene(layout));
        stage.setResizable(false);
        return stage;
    }

    public static AnchorPane getPersonContainerPane(String username, String firstName, String lastName, String email, String status, Parent layout, SidebarEventListener sidebarEventListener){
        Label lblUsername = (Label) layout.getChildrenUnmodifiable().get(0);
        Label lblStatus = (Label) layout.getChildrenUnmodifiable().get(1);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(238);
        anchorPane.setPrefHeight(60);
        lblUsername.setText(username);
        lblStatus.setText(status);
        anchorPane.getChildren().add(lblUsername);
        anchorPane.getChildren().add(lblStatus);
        anchorPane.setOnMouseEntered(event -> {
            anchorPane.setStyle("-fx-background-color: rgb(214, 214, 214)");
        });
        anchorPane.setOnMouseExited(event -> {
            anchorPane.setStyle("-fx-background-color: rgb(243, 243, 243)");
        });
        anchorPane.setOnMouseClicked(event -> {
            sidebarEventListener.onPersonContainerClicked(UserInterface.getPersonDetailContainerPane(username, firstName, lastName, email, status, new Layout().getPersonDetailContainerLayout()));
        });
        return anchorPane;
    }

    public static AnchorPane getPersonDetailContainerPane(String username, String firstName, String lastName, String email, String status, Parent layout){
        Label lblUserName = (Label) layout.getChildrenUnmodifiable().get(0);
        Label lblStatus = (Label) layout.getChildrenUnmodifiable().get(1);
        Label lblName = (Label) layout.getChildrenUnmodifiable().get(2);
        Label lblEmail = (Label) layout.getChildrenUnmodifiable().get(3);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(300);
        anchorPane.setPrefHeight(617);
        lblUserName.setText(username);
        lblStatus.setText(status);
        lblName.setText(firstName+" "+lastName);
        lblEmail.setText(email);
        anchorPane.getChildren().add(lblUserName);
        anchorPane.getChildren().add(lblStatus);
        anchorPane.getChildren().add(lblName);
        anchorPane.getChildren().add(lblEmail);
        return anchorPane;
    }

    public static AnchorPane getMessageContainerPane(String sender, String message, Parent layout){
        Text txtMessage = (Text) layout.getChildrenUnmodifiable().get(1);
        Label txtPerson = (Label) layout.getChildrenUnmodifiable().get(0);
        Label txtTimeStamp = (Label) layout.getChildrenUnmodifiable().get(2);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(70);
        anchorPane.setPrefWidth(753);
        txtPerson.setText(sender);
        txtMessage.setText(message);
        txtTimeStamp.setText(new SimpleDateFormat("HH:mm dd/mm/yyyy").format(new Date()).toString());
        anchorPane.getChildren().add(txtPerson);
        anchorPane.getChildren().add(txtMessage);
        anchorPane.getChildren().add(txtTimeStamp);
        return anchorPane;
    }

    public static AnchorPane getSidebarPane(Parent layout){
        return new AnchorPane(layout);
    }

}
