package com.app.mychat.utils.classes.ui;

import javafx.scene.layout.AnchorPane;

public class Person {

    public static final String STATUS_ACTIVE = "Active";
    public static final String STATUS_INACTIVE = "Offline";
    public static final String STATUS_TYPING = "Typing...";

    private final AnchorPane anchorPane;

    public Person(String person, String status){
        anchorPane = UserInterface.getPersonContainerPane(person, status, new Layout().getPersonContainerLayout());
    }

    public AnchorPane getPersonUI(){
        return anchorPane;
    }

}
