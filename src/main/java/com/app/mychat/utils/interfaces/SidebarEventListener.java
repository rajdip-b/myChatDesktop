package com.app.mychat.utils.interfaces;

public interface SidebarEventListener {

    public void onAccountDeleteRequested();
    public void onAccountEditRequested(String firstName, String lastName, String password, String username);
    public void onCheckUpdateRequested();

}
