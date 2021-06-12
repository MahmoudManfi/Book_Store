package com.library.Controller;

import com.library.Model.Book;
import com.library.Model.Info;

import java.sql.*;
import java.util.*;

public class DatabaseConnector {
    final String myUrl = "jdbc:mysql://localhost:3306/book_store";
    String password; // change to your passowrd
    private static DatabaseConnector databaseConnector;

    private DatabaseConnector() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your password: ");
        password = sc.next();
        sc.close();
    }

    static DatabaseConnector getInstance() {
        if (databaseConnector == null) {
            databaseConnector = new DatabaseConnector();
        }
        return databaseConnector;
    }

    private void execute_select_query(String query) {
        try {
            Connection conn;
            Statement st;
            conn = DriverManager.getConnection(myUrl, "root", password);
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            // iterate through the java resultset
//            while (rs.next()) {
//                String isbn = rs.getString("ISBN_number");
//                String title = rs.getString("title");
//                String author_name = rs.getString("author_name");
//
//                // print the results
//                System.out.format("%s, %s, %s\n", isbn, title, author_name);
//            }
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    // update or insert
    private void execute_query(String query) {
        try {
            Connection conn;
            Statement st;
            conn = DriverManager.getConnection(myUrl, "root", password);
            st = conn.createStatement();
            int ret = st.executeUpdate(query);
            st.close();
            System.out.println("Query done successfully !! ");
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }


    void addBook(Book book) {
        String query = generateAddQuery(book);
        execute_query(query);
    }

    void updateBook(Book book) {
        String query = generateUpdateQuery(book);
        execute_query(query);
    }

    List<Book> searchForBooks(Book book) {
        String query = generateSearchedBooks(book);
        List<Book> result = new ArrayList<>();
        try {
            Connection connection;
            Statement statement;
            connection = DriverManager.getConnection(myUrl, "root", password);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Book resultBook = new Book();
                resultBook.setIsbn(resultSet.getString(1));
                resultBook.setTitle(resultSet.getString(2));
                resultBook.setAuthorName(resultSet.getString(3));
                resultBook.setPublisherName(resultSet.getString(4));
                resultBook.setPublicationYear(resultSet.getString(5));
                resultBook.setPrice(resultSet.getInt(6));
                resultBook.setCategory(resultSet.getString(7));
                resultBook.setNumberCopies(resultSet.getInt(8));
                resultBook.setThreshold(resultSet.getInt(9));
                result.add(resultBook);
            }
            statement.close();
//            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String generateAddQuery(Book book) {
        String query = "insert into book(ISBN_number, title, author_name,publisher_name, " +
                "publication_year, price, category, number_copies, threshold) ";

        query += "values (";
        query += "\"" + book.getIsbn() + "\"" + ", ";
        query += "\"" + book.getTitle() + "\"" + ", ";
        query += "\"" + book.getAuthorName() + "\"" + ", ";
        query += "\"" + book.getPublisherName() + "\"" + ", ";
        query += book.getPublicationYear() + ", ";
        query += book.getPrice() + ", ";
        query += "\"" + book.getCategory() + "\"" + ", ";
        query += book.getNumberCopies() + ", ";
        query += book.getThreshold();
        query += ")";
        System.err.println("Executing query : " + query);
        return query;
    }

    private String generateUpdateQuery(Book book) {
        String updateFields = "";
        String query = "";
        if (book.getTitle() != null && !book.getTitle().trim().equals("")) {
            updateFields += " title = \"" + book.getTitle() + "\" ,";
        }
        if (book.getAuthorName() != null && !book.getAuthorName().trim().equals("")) {
            updateFields += " author_name = \"" + book.getAuthorName() + "\" ,";
        }
        if (book.getPublisherName() != null && !book.getPublisherName().trim().equals("")) {
            updateFields += " publisher_name = \"" + book.getPublisherName() + "\" ,";
        }
        if (book.getPublicationYear() != null) {
            updateFields += " publication_year = " + book.getPublicationYear() + " ,";
        }
        if (book.getCategory() != null && !book.getCategory().trim().equals("")) {
            updateFields += " category = \"" + book.getCategory() + "\" ,";
        }
        if (book.getNumberCopies() != null) {
            updateFields += " number_copies = " + book.getNumberCopies() + " ,";
        }
        if (book.getThreshold() != null) {
            updateFields += " threshold = " + book.getThreshold() + " ,";
        }
        if (!updateFields.trim().equals("")) {
            updateFields = updateFields.substring(0, updateFields.length() - 2);
            query = "update book set " + updateFields;
            query += " where ISBN_number = " + book.getIsbn() + ";";
        }
        System.err.println("update Query: " + query);

        return query;
    }

    private String generateSearchedBooks(Book book) {
        String query = "select * from book";
        String where = " where";
        String conditions = "";
        if (book.getIsbn() != null && !book.getIsbn().isEmpty()) {
            conditions += " ISBN_number = " + book.getIsbn() + " &&";
        }
        if (book.getTitle() != null && !book.getTitle().trim().equals("")) {
            conditions += " title = \"" + book.getTitle() + "\" &&";
        }
        if (book.getAuthorName() != null && !book.getAuthorName().trim().equals("")) {
            conditions += " author_name = \"" + book.getAuthorName() + "\" &&";
        }
        if (book.getPublisherName() != null && !book.getPublisherName().trim().equals("")) {
            conditions += " publisher_name = \"" + book.getPublisherName() + "\" &&";
        }
        if (book.getPublicationYear() != null && !book.getPublicationYear().trim().equals("")) {
            conditions += " publication_year = \"" + book.getPublicationYear() + "\" &&";
        }
        if (book.getCategory() != null && !book.getCategory().trim().equals("")) {
            conditions += " category = \"" + book.getCategory() + "\" &&";
        }

        //there exists one condition
        if (!conditions.trim().equals("")) {
            conditions = conditions.substring(0, conditions.length() - 2) + ";";
            query += (where + conditions);
        } else {
            query += ";";
        }
        System.err.println("Search Query: " + query);
        return query;
    }



    // Note : if we update a book with non-existing ISBN, the database will not give us any feedback
    // Should we inform the user if there are no records mathcing his update query
//    public static void main(String[] args) {
//        Book book = new Book();
//        book.setIsbn("123333123");
//        book.setTitle("new harry potter");
//        book.setThreshold(100);
//        DatabaseConnector.getInstance().updateBook(book);
//    }
}
