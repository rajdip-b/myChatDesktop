package com.app.mychat;

import com.app.mychat.controller.LoginGUIController;
import com.app.mychat.controller.SignupGUIController;
import com.app.mychat.utils.classes.CredentialNetwork;
import com.app.mychat.utils.interfaces.WindowEventListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application implements WindowEventListener {

    private static Stage currentStage = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        loadLoginScreen();
    }

    private Stage getLoginStage(){
        Stage stage = new Stage();
        Parent root = null;
        try{
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/LoginGUI.fxml")));
        }catch (IOException e){
            System.exit(1);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("myCHat - Login");
        return stage;
    }

    private Stage getSignupStage(){
        Stage stage = new Stage();
        Parent root = null;
        try{
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/SignupGUI.fxml")));
        }catch (IOException e){
            System.exit(1);
        }
        stage.setTitle("myChat - Sign up");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        return stage;
    }

    private Stage getChatScreenStage(){
        Stage stage = new Stage();
        Parent root = null;
        try{
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ChatScreenGUI.fxml")));
        }catch (IOException e){
            System.exit(1);
        }
        stage.setTitle("myChat - Chat screen");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        return stage;
    }

    private void loadLoginScreen(){
        currentStage = getLoginStage();
        currentStage.show();
        LoginGUIController.addWindowEventListener(this);
    }

    private void loadSignupScreen(){
        currentStage = getSignupStage();
        currentStage.show();
        SignupGUIController.addWindowEventListener(this);
    }

    private void loadChatScreen(){
        currentStage = getChatScreenStage();
        currentStage.show();
    }

    @Override
    public void onLoginScreenRequested() {
        currentStage.close();
        CredentialNetwork.disconnect();
        loadLoginScreen();
    }

    @Override
    public void onSignupScreenRequested() {
        currentStage.close();
        CredentialNetwork.disconnect();
        loadSignupScreen();
    }

    @Override
    public void onChatScreenRequested() {
        currentStage.close();
        CredentialNetwork.disconnect();
        loadChatScreen();
    }
}
