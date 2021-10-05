package com.app.mychat.utils.classes.ui;

import javafx.scene.layout.AnchorPane;

public class Person {

    public static final String STATUS_ACTIVE = "Active";
    public static final String STATUS_INACTIVE = "Offline";
    public static final String STATUS_TYPING = "Typing...";

    private final AnchorPane anchorPane;

    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public Person(String firstName, String lastName, String email, String username, String status) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        anchorPane = UserInterface.getPersonContainerPane(username, status, new Layout().getPersonContainerLayout());
    }

    public AnchorPane getPersonUI(){
        return anchorPane;
    }

}
