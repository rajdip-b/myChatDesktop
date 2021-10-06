package com.app.mychat.utils.classes.ui;

import com.app.mychat.utils.interfaces.SidebarEventListener;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Person {

    public static final String STATUS_ACTIVE = "Active";
    public static final String STATUS_INACTIVE = "Offline";
    public static final String STATUS_TYPING = "Typing...";

    private final AnchorPane anchorPane;
    private final String username;
    private String status;
    private Label lblStatus;

    public Person(String firstName, String lastName, String email, String username, String status, SidebarEventListener sidebarEventListener) {
        this.username = username;
        this.status = status;
        anchorPane = UserInterface.getPersonContainerPane(username, firstName, lastName, email, status, new Layout().getPersonContainerLayout(), sidebarEventListener);
        lblStatus = (Label) anchorPane.getChildrenUnmodifiable().get(1);
    }

    public AnchorPane getPersonUI(){
        return anchorPane;
    }

    public String getUsername(){
        return username;
    }

    public void setStatus(String status){
        System.out.println("Setting status: "+status);
        this.status = status;
        lblStatus.setText(status);
    }

    public String getStatus(){
        return status;
    }

}
