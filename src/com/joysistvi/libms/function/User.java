/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joysistvi.libms.function;




import com.joysistvi.libms.main.MainMenu;
import com.joysistvi.libms.dbconnection.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Yuno
 */
public class User {

    private final DbConnection dbConnection; // composition
    private final Scanner scanner;
    
    // constructor injection
    public User(DbConnection dbConnection, Scanner scanner) {
        this.dbConnection = dbConnection;
        this.scanner = scanner;
    }

    // ANSI escape codes for coloring text
    final String RESET = "\u001B[0m";
    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";
    final String YELLOW = "\u001B[33m";
    final String BLUE = "\u001B[34m";
    final String CYAN = "\u001B[36m";
    final String INDENT = "\t\t\t\t\t\t";

    
    
    public void logInUser() {
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.println("\n" + INDENT + BLUE + "*** Log in ***" + RESET + "\n");
            System.out.print(INDENT + CYAN + "Enter user name: " + RESET);
            String userLoginName = scanner.nextLine();
            System.out.print(INDENT + CYAN + "Enter user password: " + RESET);
            String userLoginPass = scanner.nextLine();
            System.out.println("\n");

            String query = "SELECT username, password, role_id FROM tblusers "
                    + "WHERE username = ? AND password = ? AND isArchived = 0";

            try (Connection connection = dbConnection.connect();
                    PreparedStatement prep = connection.prepareStatement(query)) {

                prep.setString(1, userLoginName);
                prep.setString(2, userLoginPass);

                try (ResultSet result = prep.executeQuery()) {
                    if (result.next()) {
                        int role = 1;
                        System.out.println(INDENT + GREEN + "✔ Logged In Successfully!" + RESET);

                        MainMenu mn = new MainMenu(scanner, dbConnection);
                        if (role == 1) {
                            mn.mainDashBoard(1); // Admin
                        } else {
                            mn.mainDashBoard(2); // Standard user
                        }
                        loggedIn = true; // exit loop
                    } else {
                        System.out.println(INDENT + RED + "✘ Login Failed! Please try again." + RESET + "\n");
                    }
                }
            } catch (Exception e) {
                System.out.println(INDENT + YELLOW + "⚠ Error: " + e.getMessage() + RESET);
                System.out.println(INDENT + "Please try again.\n");
            }
        }
    }

//    public void logInUser() {
//    Scanner sc = new Scanner(System.in);
//    boolean loggedIn = false;
//
//    while (!loggedIn) {
//        System.out.println("\n*** Log in ***\n");
//        System.out.print("Enter user name: ");
//        String userLoginName = sc.nextLine();
//        System.out.print("Enter user password: ");
//        String userLoginPass = sc.nextLine();
//        System.out.println("\n\n\n");
//
//        String query = "SELECT user_username, user_password, role FROM tblusers "
//                     + "WHERE user_username = ? AND user_password = ? AND archived = 0";
//
//        try (Connection connection = dbConnection.connect();
//             PreparedStatement prep = connection.prepareStatement(query)) {
// 
//            prep.setString(1, userLoginName);
//            prep.setString(2, userLoginPass);
//
//            try (ResultSet result = prep.executeQuery()) {
//                if (result.next()) {
//                    int role = result.getInt("role");
//                    System.out.println("Logged In Successfully!");
//
//                    MainMenu mn = new MainMenu();
//                    if (role == 0) {
//                        mn.mainDashBoard(0); // Admin
//                    } else {
//                        mn.mainDashBoard(1); // Standard user
//                    }
//                    loggedIn = true; // exit loop
//                } else {
//                    System.out.println("Login Failed! Please try again.\n");
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//            System.out.println("Please try again.\n");
//        }
//    }
//}
//    public void logInUser() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("\n*** Log in ***\n");
//        System.out.print("Enter user name: ");
//        String userLoginName = sc.nextLine();
//        System.out.print("Enter user password: ");
//        String userLoginPass = sc.nextLine();
//        System.out.println("\n\n\n");
//
//        String query = "Select user_username, user_password, role from tblusers "
//                + "where user_username = ? AND user_password = ? AND archived = 0";
//
//        try (Connection connnection = dbConnection.connect();
//                PreparedStatement prep = connnection.prepareStatement(query)) {
//
//            // Set parameters for PreparedStatement
//            prep.setString(1, userLoginName);   
//            prep.setString(2, userLoginPass);
//
//            try (ResultSet result = prep.executeQuery()) {
//                int role = -1;
//                if (result.next()) {
//                    role = result.getInt("role");
//                    System.out.println("Logged In Successfully!");
//                    
//                    MainMenu mn = new MainMenu();
//                    if (role == 0) {
//                        mn.mainDashBoard(0); // Admin
//                    } else {
//                        mn.mainDashBoard(1); // Standard user
//                    }
//                } else {
//                    System.out.println("Login Failed! Please try again.");
//                    logInUser();
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//            logInUser();
//        }
//
//    }
}
