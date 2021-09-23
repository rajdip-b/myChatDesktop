package com.app.mychat.view;

import com.app.mychat.controller.SignupGUIController;

import java.util.Scanner;

public class SignupGUI {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter last name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.next();
        System.out.print("Enter alias: ");
        String alias = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();
        new SignupGUIController().onSignupClicked(firstName, lastName, email, password, alias);
    }

}
