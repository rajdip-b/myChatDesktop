package com.app.mychat.utils.interfaces;

public interface WindowEventListener {

    public void onLoginScreenRequested();
    public void onSignupScreenRequested();
    public void onChatScreenRequested(String username);
    public void onSidebarOpened();
    public void onSidebarClosed();

}
