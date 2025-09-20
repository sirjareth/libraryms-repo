/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joysistvi.util.dbconnection;

import java.util.Scanner;

/**
 *
 * @author Yuno
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DbConnection dbConnect = new DbConnection();
//        System.out.println("Search Book Title: ");
//        String bookTitle = scanner.nextLine();
//        
//
       Book book = new Book(scanner, dbConnect);
       book.readBooksWithFullDetails();

    }
}
