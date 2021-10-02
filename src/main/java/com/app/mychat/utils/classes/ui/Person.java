package com.app.mychat.utils.classes.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class Person {

    public static final String STATUS_ACTIVE = "Active";
    public static final String STATUS_INACTIVE = "Offline";
    public static final String STATUS_TYPING = "Typing...";

    private AnchorPane anchorPane;
    private Label txtPerson;
    private Text txtStatus;

    public Person(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fragments/MessageContainer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        txtPerson = (Label) root.getChildrenUnmodifiable().get(0);
        txtStatus = (Text) root.getChildrenUnmodifiable().get(1);
        anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(238);
        anchorPane.setPrefHeight(60);
    }

    public Person(String person, String status){
        this();
        txtPerson.setText(person);
        txtStatus.setText(status);
        anchorPane.getChildren().add(txtPerson);
        anchorPane.getChildren().add(txtStatus);
    }

    public AnchorPane getPersonUI(){
        return anchorPane;
    }

}
