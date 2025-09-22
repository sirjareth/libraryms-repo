/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joysistvi.libms.main;

//import com.joysis.libms.function.AuthorFunction;
import com.joysistvi.libms.function.Book;
import com.joysistvi.libms.dbconnection.DbConnection;
import java.util.Scanner;

public class MainMenu {

    Scanner scanner;
    DbConnection dbConnection;

    public MainMenu(Scanner scanner, DbConnection dbConnection) {
        this.scanner = scanner;
        this.dbConnection = dbConnection;
    }
    
    
    
    // ANSI escape codes for coloring text
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String INDENT = "\t\t\t\t\t\t";

    public void printWelcomeMessage() {
        Scanner sc = new Scanner(System.in);
        System.out.println(BLUE + "**************************************************************************************************************************");
        System.out.println(BLUE + "*                                                                                                                        *");
        System.out.println(BLUE + "* " + GREEN + "\t\t\t\t\t\t  _      __  __  _____ " + BLUE + "\t\t\t\t\t\t\t *");
        System.out.println(BLUE + "* " + GREEN + "\t\t\t\t\t\t | |     |  \\/  |/ ____|" + BLUE + "\t\t\t\t\t\t *");
        System.out.println(BLUE + "* " + GREEN + "\t\t\t\t\t\t | |     | \\  / | (___  " + BLUE + "\t\t\t\t\t\t *");
        System.out.println(BLUE + "* " + GREEN + "\t\t\t\t\t\t | |     | |\\/| |\\___ \\ " + BLUE + "\t\t\t\t\t\t *");
        System.out.println(BLUE + "* " + GREEN + "\t\t\t\t\t\t | |____ | |  | |____) |" + BLUE + "\t\t\t\t\t\t *");
        System.out.println(BLUE + "* " + GREEN + "\t\t\t\t\t\t |______||_|  |_|_____/ " + BLUE + "\t\t\t\t\t\t *");
        System.out.println(BLUE + "*                                                                                                                        *");
        System.out.println(BLUE + "* " + PURPLE + "\t\t\t\t\t WELCOME TO THE LIBRARY MANAGEMENT SYSTEM!   " + BLUE + "\t\t\t\t\t *");
        System.out.println(BLUE + "*                                                                                                                        *");
        System.out.println(BLUE + "**************************************************************************************************************************" + RESET);
        System.out.println();
        //mainDashBoard();
    }

    public void mainDashBoard(int role) {
        Scanner sc = new Scanner(System.in);
        printWelcomeMessage();

        // Show user role
        String roleName = (role == 1) ? (GREEN + "ADMIN" + RESET) : (CYAN + "STANDARD USER" + RESET);
        System.out.println(BLUE + "* " + YELLOW + "\t\t\t\t\t    You are logged in as: " + roleName + "   " + BLUE + "\t\t\t\t\t *");

        System.out.println(BLUE + "*                                                                                                                        *");
        System.out.println(BLUE + "**************************************************************************************************************************" + RESET);
        System.out.println();

        int choice = 0;
        //AuthorFunction authFunction = new AuthorFunction();
        do {

            if (role == 1) {
                System.out.println(BLUE + "\t\t\t\tPlease select an option from the menu below:" + RESET);
                System.out.println(PURPLE + INDENT + "1. Manage Books");
                System.out.println(PURPLE + INDENT + "2. Manage Publishers");
                System.out.println(PURPLE + INDENT + "3. Manage Authors");
                System.out.println(PURPLE + INDENT + "4. Manage Editions");
                System.out.println(PURPLE + INDENT + "0. Exit");
                System.out.println();

                System.out.print(INDENT + "Enter a choice: ");
                try {
                    choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            Book book = new Book(scanner, dbConnection);
                            book.bookDashBoard();
                            break;
                        case 2:
                            
                            break;
                        case 3:
                            
                            break;
                        case 4:
                            
                            break;
                        case 5:
                            // Exit the program
                            break;
                        default:
                        // Invalid input 
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a valid integer for the choice.");
                    sc.nextLine(); // antibug
                    //mainDashBoard(); // recursion
                }
            } else {
                System.out.println(BLUE + "\t\t\t\tPlease select an option from the menu below:" + RESET);
                System.out.println(PURPLE + INDENT + "1. View Books");
                System.out.println(PURPLE + INDENT + "2. Search Books");
                System.out.println(PURPLE + INDENT + "3. Borrow Books");
                System.out.println(PURPLE + INDENT + "4. Return Books");
                System.out.println(PURPLE + INDENT + "0. Exit");
                System.out.println();

                System.out.print(INDENT + "Enter a choice: ");
                try {
                    choice = sc.nextInt();
                    
                    Book book = new Book(scanner, dbConnection);
                    switch (choice) {
                        case 1:
                            book.readBooksWithFullDetails();
                            break;
                        case 2:
                            //book.searchBooks();
                            break;
                        case 3:
                            
                            break;
                        case 4:
                            
                            break;
                        case 5:
                            
                            break;
                        default:
                        // Invalid input 
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a valid integer for the choice.");
                    sc.nextLine(); // antibug
                    //mainDashBoard(); // recursion
                }
            }

        } while (choice != 0);

    }
}
