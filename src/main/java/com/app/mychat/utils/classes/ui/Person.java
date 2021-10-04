package com.app.mychat.utils.classes.ui;

import com.app.mychat.controller.LoginGUIController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Person {

    public static final String STATUS_ACTIVE = "Active";
    public static final String STATUS_INACTIVE = "Offline";
    public static final String STATUS_TYPING = "Typing...";

    private AnchorPane anchorPane;
    private Label txtPerson;
    private Label txtStatus;

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String status;

    public Person(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fragments/PersonContainer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        txtPerson = (Label) root.getChildrenUnmodifiable().get(0);
        txtStatus = (Label) root.getChildrenUnmodifiable().get(1);
        anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(238);
        anchorPane.setPrefHeight(70);
        anchorPane.setOnMouseClicked(event -> {
            getPersonDetailContainer().show();
        });
        anchorPane.setOnMouseEntered(event -> {
            anchorPane.setStyle("-fx-background-color: rgb(201, 201, 201)");
        });
        anchorPane.setOnMouseExited(event -> {
            anchorPane.setStyle("-fx-background-color: rgb(247, 247, 247)");
        });
    }

    public Person(String firstName, String lastName, String email, String username, String status){
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.status = status;
        txtPerson.setText(username);
        txtStatus.setText(status);
        anchorPane.getChildren().add(txtPerson);
        anchorPane.getChildren().add(txtStatus);
    }

    public AnchorPane getPersonUI(){
        return anchorPane;
    }

    private Stage getPersonDetailContainer(){
        Stage stage = new Stage();
        Parent root = null;
        try{
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fragments/PersonDetailContainer.fxml")));
        }catch (IOException e){
            System.exit(1);
        }

        Label lblName = (Label) root.getChildrenUnmodifiable().get(0);
        Label lblUserName = (Label) root.getChildrenUnmodifiable().get(1);
        Label lblEmail = (Label) root.getChildrenUnmodifiable().get(2);
        Label lblStatus = (Label) root.getChildrenUnmodifiable().get(3);

        lblName.setText(firstName+" "+lastName);
        lblUserName.setText(username);
        lblStatus.setText(status);
        lblEmail.setText(email);

        AnchorPane personDetails = new AnchorPane();
        personDetails.setPrefWidth(630);
        personDetails.setPrefHeight(225);
        personDetails.getChildren().add(lblName);
        personDetails.getChildren().add(lblUserName);
        personDetails.getChildren().add(lblEmail);
        personDetails.getChildren().add(lblStatus);

        Scene scene = new Scene(personDetails);

        stage.setTitle(username);
        stage.setScene(scene);
        stage.setResizable(false);
        return stage;
    }

}
