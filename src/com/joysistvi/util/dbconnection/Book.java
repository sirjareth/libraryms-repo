/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joysistvi.util.dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Yuno
 */
public class Book {

    Scanner scanner;
    DbConnection dbConnection;

    public Book(Scanner scanner, DbConnection dbConnection) {
        this.scanner = scanner;
        this.dbConnection = dbConnection;
    }

    // CRUD Operation
    public void readBooks() {
        String query = "SELECT * from tblbooks WHERE isArchived = 0";

        // try with resources
        try (Connection connection = dbConnection.connect(); // connection with the db
                Statement statement = connection.createStatement(); // create sql statement
                ResultSet result = statement.executeQuery(query)) { // execute query

            // Table Header
            System.out.printf("| %-8s | %-30s | %-10s | %-12s | %-12s | %-8s |%n", "Book ID", "Book Title", "Author ID", "Publisher ID", "Edition Yr", "Pages");
            System.out.println("----------------------------------------------------------------------------------------------------");
            while (result.next()) {
                int bookId = result.getInt("book_id");
                String bookTitle = result.getString("book_title");
                int authorId = result.getInt("author_id");
                int pubId = result.getInt("pub_id");
                int editionYr = result.getInt("edition_year");
                int pages = result.getInt("pages");

                System.out.printf("| %-8d | %-30s | %-10d | %-12d | %-12d | %-8d |%n",
                        bookId, bookTitle, authorId, pubId, editionYr, pages);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
     // Books Inner Join Authors & Publishers
    public void readBooksUsingInnerJoin() {
        String query = "SELECT tblbooks.book_id, tblbooks.book_title, tblauthors.author_name, tblpublishers.pub_name "
                + "FROM tblbooks "
                + "INNER JOIN tblauthors "
                + "ON tblbooks.author_id = tblauthors.author_id "
                + "INNER JOIN tblpublishers "
                + "ON tblbooks.pub_id = tblpublishers.pub_id";

        // try with resources
        try (Connection connection = dbConnection.connect(); // connection with the db
                Statement statement = connection.createStatement(); // create sql statement
                ResultSet result = statement.executeQuery(query)) { // execute query

            // Table Header
            System.out.printf("| %-8s | %-30s | %-30s | %-30s | %n", "Book ID", "Book Title", "Author", "Publisher");
            System.out.println("----------------------------------------------------------------------------------------------------");
            while (result.next()) {
                int bookId = result.getInt("book_id");
                String bookTitle = result.getString("book_title");
                String authorName = result.getString("author_name");
                String pubName = result.getString("pub_name");
                

                System.out.printf("| %-8d | %-30s | %-30s | %-30s |%n",
                        bookId, bookTitle, authorName, pubName);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Books Inner Join Authors & Publishers & Editions
    public void readBooksWithFullDetails() {
        String query = "SELECT b.book_id, b.book_title, a.author_name, p.pub_name, e.edition_number, e.edition_year, e.pages "
                + "FROM tblbooks b "
                + "JOIN tblauthors a "
                + "ON b.author_id = a.author_id "
                + "JOIN tblpublishers p "
                + "ON b.pub_id = p.pub_id "
                + "JOIN tbleditions e "
                + "ON b.book_id = e.book_id";

        // try with resources
        try (Connection connection = dbConnection.connect(); // connection with the db
                Statement statement = connection.createStatement(); // create sql statement
                ResultSet result = statement.executeQuery(query)) { // execute query

            // Table Header
            System.out.printf("| %-8s | %-30s | %-30s | %-30s | %-15s | %-12s | %-6s |%n", "Book ID", "Book Title", "Author", "Publisher", "Book Edition", "Edition Year", "PAGES");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                int bookId = result.getInt("book_id");
                String bookTitle = result.getString("book_title");
                String authorName = result.getString("author_name");
                String pubName = result.getString("pub_name");
                String bookEdition = result.getString("edition_number");
                int editionYear = result.getInt("edition_year");
                int pages = result.getInt("pages");

                System.out.printf("| %-8d | %-30s | %-30s | %-30s | %-15s | %-12d | %-6d |%n",
                        bookId, bookTitle, authorName, pubName, bookEdition, editionYear, pages);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void readArchivedBooks() {
        String query = "SELECT * from tblbooks WHERE isArchived = 1";

        // try with resources
        try (Connection connection = dbConnection.connect(); // connection with the db
                Statement statement = connection.createStatement(); // create sql statement
                ResultSet result = statement.executeQuery(query)) { // execute query

            // Table Header
            System.out.printf("| %-8s | %-30s | %-10s | %-12s | %-12s | %-8s |%n", "Book ID", "Book Title", "Author ID", "Publisher ID", "Edition Yr", "Pages");
            System.out.println("----------------------------------------------------------------------------------------------------");
            while (result.next()) {
                int bookId = result.getInt("book_id");
                String bookTitle = result.getString("book_title");
                int authorId = result.getInt("author_id");
                int pubId = result.getInt("pub_id");
                int editionYr = result.getInt("edition_year");
                int pages = result.getInt("pages");

                System.out.printf("| %-8d | %-30s | %-10d | %-12d | %-12d | %-8d |%n",
                        bookId, bookTitle, authorId, pubId, editionYr, pages);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createBooks(String bookTitle, int authorId, int pubId, int editionYr, int pages) {
        String query = "INSERT INTO tblbooks (book_title, author_id, pub_id, edition_year, pages) "
                + "VALUES (?,?,?,?,?)"; // Anti SQL Injection

        try (Connection connect = dbConnection.connect();
                PreparedStatement prep = connect.prepareStatement(query)) {

            //  wildcards = parameter
            prep.setString(1, bookTitle);
            prep.setInt(2, authorId);
            prep.setInt(3, pubId);
            prep.setInt(4, editionYr);
            prep.setInt(5, pages);
            prep.executeUpdate();

            System.out.println("Book " + bookTitle + " added successfully!\n");
            readBooks(); // synchronization
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateBooks(String bookTitle, int bookId) {
        String query = "UPDATE tblbook SET book_title = ? WHERE book_id = ?";

        try (Connection connect = dbConnection.connect();
                PreparedStatement prep = connect.prepareStatement(query);) {

            prep.setString(1, bookTitle);
            prep.setInt(2, bookId);

            prep.executeUpdate();
            System.out.println("Book " + bookTitle + " updated successfully!\n");
            readBooks();
        } catch (Exception e) {
            System.out.println("Update Books: " + e.getMessage());
        }
    }

    public void deleteBooks(int bookId) {
        String query = "DELETE FROM tblbooks WHERE book_id = ?";

        try (Connection connect = dbConnection.connect();
                PreparedStatement prep = connect.prepareStatement(query);) {

            prep.setInt(1, bookId);

            prep.executeUpdate();
            System.out.println("Book " + bookId + " deleted successfully!\n");
            readBooks();

        } catch (Exception e) {
            System.out.println("Delete Books: " + e.getMessage());
        }
    }

    public void archiveBooks(int bookId) {
        String query = "UPDATE tblbooks SET isArchived = 1 WHERE book_id = ?";

        try (Connection connect = dbConnection.connect();
                PreparedStatement prep = connect.prepareStatement(query);) {

            prep.setInt(1, bookId);

            prep.executeUpdate();
            System.out.println("Book " + bookId + " deleted successfully!\n");
            readBooks();

        } catch (Exception e) {
            System.out.println("Delete Books: " + e.getMessage());
        }
    }

    public void restoreBooks(int bookId) {
        String query = "UPDATE tblbooks SET isArchived = 0 WHERE book_id = ?";

        try (Connection connect = dbConnection.connect();
                PreparedStatement prep = connect.prepareStatement(query);) {

            prep.setInt(1, bookId);

            prep.executeUpdate();
            System.out.println("Book " + bookId + " deleted successfully!\n");
            readBooks();

        } catch (Exception e) {
            System.out.println("Delete Books: " + e.getMessage());
        }
    }

    public void searchBooks(String bookTitleKW) {
        String query = "SELECT * FROM tblbooks WHERE book_title LIKE Mockingb AND isArchived = 0";

        try (Connection connect = dbConnection.connect();
                PreparedStatement prep = connect.prepareStatement(query);) {

            prep.setString(1, "%" + bookTitleKW + "%");

            ResultSet result = prep.executeQuery();

            // Table Header
            System.out.printf("| %-8s | %-30s | %-10s | %-12s | %-12s | %-8s |%n", "Book ID", "Book Title", "Author ID", "Publisher ID", "Edition Yr", "Pages");
            System.out.println("----------------------------------------------------------------------------------------------------");
            while (result.next()) {
                int bookId = result.getInt("book_id");
                String bookTitle = result.getString("book_title");
                int authorId = result.getInt("author_id");
                int pubId = result.getInt("pub_id");
                int editionYr = result.getInt("edition_year");
                int pages = result.getInt("pages");

                System.out.printf("| %-8d | %-30s | %-10d | %-12d | %-12d | %-8d |%n",
                        bookId, bookTitle, authorId, pubId, editionYr, pages);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

// Project Criteria -> Users Table -> Dashboard