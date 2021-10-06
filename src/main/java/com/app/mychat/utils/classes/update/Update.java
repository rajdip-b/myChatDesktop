package com.app.mychat.utils.classes.update;

public class Update {

    public static void main(String[] args) {
        new UpdateChecker(new GitHub("v2.0.0")).checkForVersion();
    }

}
