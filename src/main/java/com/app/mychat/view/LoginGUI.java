package com.app.mychat.view;

import com.app.mychat.controller.LoginGUIController;

import java.io.File;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginGUI extends Application{

    private static String username;
    private static String password;

    public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		System.out.print("Enter username: ");
//		username = sc.nextLine();
//		System.out.print("Enter password: ");
//		password = sc.nextLine();
//		new LoginGUIController().onLoginClicked(username, password);
//		sc.close();
		launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/LoginGUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
