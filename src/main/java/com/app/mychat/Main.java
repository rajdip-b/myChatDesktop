package com.app.mychat;

import com.app.mychat.controller.ChatScreenController;
import com.app.mychat.controller.LoginGUIController;
import com.app.mychat.controller.SignupGUIController;
import com.app.mychat.utils.classes.backend.UserDetails;
import com.app.mychat.utils.classes.ui.Layout;
import com.app.mychat.utils.classes.ui.UserInterface;
import com.app.mychat.utils.interfaces.WindowEventListener;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application implements WindowEventListener {

    private static Stage currentStage = null;

    public static void main(String[] args) {
//        System.setProperty("javax.net.ssl.trustStore", "src/main/java/myChatTrustStore.jts");
        System.setProperty("javax.net.ssl.trustStore", "    myChatTrustStore.jts");
        System.setProperty("javax.net.ssl.trustStorePassword", "cPPMq4IXThdRK1gf");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        loadLoginScreen();
    }

    private void loadLoginScreen(){
        currentStage = UserInterface.getLoginStage(new Layout().getLoginLayout());
        currentStage.show();
        LoginGUIController.addWindowEventListener(this);
    }

    private void loadSignupScreen(){
        currentStage = UserInterface.getSignupStage(new Layout().getSignupLayout());
        currentStage.show();
        SignupGUIController.addWindowEventListener(this);
    }

    private void loadChatScreen(){
        currentStage = UserInterface.getChatScreenStage(new Layout().getChatScreenLayout());
        currentStage.show();
        ChatScreenController.addWindowEventListener(this);
    }

    @Override
    public void onLoginScreenRequested() {
        currentStage.close();
        loadLoginScreen();
    }

    @Override
    public void onSignupScreenRequested() {
        currentStage.close();
        loadSignupScreen();
    }

    @Override
    public void onChatScreenRequested() {
        currentStage.close();
        loadChatScreen();
    }

    @Override
    public void onSidebarOpened() {
        currentStage.setWidth(currentStage.getWidth()+300);
    }

    @Override
    public void onSidebarClosed() {
        currentStage.setWidth(currentStage.getWidth()-300);
    }

}
