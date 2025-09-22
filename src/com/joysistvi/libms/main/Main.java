/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joysistvi.libms.main;

import com.joysistvi.libms.function.Book;
import com.joysistvi.libms.function.User;
import com.joysistvi.libms.dbconnection.DbConnection;
import java.util.Scanner;

/**
 *
 * @author Yuno
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DbConnection dbConnect = new DbConnection();

        User user = new User(dbConnect, scanner);
        user.logInUser();
    }
}
