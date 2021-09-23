package com.app.mychat.view;

import com.app.mychat.controller.LoginGUIController;

import java.util.Scanner;

public class LoginGUI{

    private static String username;
    private static String password;

    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter username: ");
		username = sc.nextLine();
		System.out.print("Enter password: ");
		password = sc.nextLine();
		new LoginGUIController().onLoginClicked(username, password);
		sc.close();
    }
}
