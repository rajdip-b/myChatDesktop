package com.app.mychat.utils.classes.ui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(1);
        });
        return stage;
    }

    public static AnchorPane getPersonContainerPane(String person, String status, Parent layout){
        Label txtPerson = (Label) layout.getChildrenUnmodifiable().get(0);
        Text txtStatus = (Text) layout.getChildrenUnmodifiable().get(1);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(238);
        anchorPane.setPrefHeight(60);
        txtPerson.setText(person);
        txtStatus.setText(status);
        anchorPane.getChildren().add(txtPerson);
        anchorPane.getChildren().add(txtStatus);
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
