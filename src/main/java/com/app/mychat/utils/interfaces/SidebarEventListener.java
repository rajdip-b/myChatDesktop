package com.app.mychat.utils.interfaces;

import javafx.scene.layout.AnchorPane;

public interface SidebarEventListener {

    void onAccountDeleteRequested();
    void onAccountEditWithPasswordAndWithUsernameRequested(String firstName, String lastName, String password, String username);
    void onAccountEditWithoutPasswordAndWithUsernameRequested(String firstName, String lastName, String username);
    void onAccountEditWithPasswordAndWithoutUsernameRequested(String firstName, String lastName, String password);
    void onAccountEditWithoutPasswordAndWithoutUsernameRequested(String firstName, String lastName);
    void onCheckUpdateRequested();
    void onPersonContainerClicked(AnchorPane personDetailContainer);

}
