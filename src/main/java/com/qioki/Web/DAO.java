package com.company;

import java.sql.*;
import java.util.ArrayList;

public class DAO {

    Connection connectionBD = null;
    //database link
    private final String DB_URL = "jdbc:mysql://localhost:3306/journal_rp.sql";

    /**
     * Creating a database connection
     */
    private void createConnectionDataBase(/*String user, String password*/) {
        try {
            this.connectionBD = DriverManager.getConnection(DB_URL/*, user, password*/);
        } catch (SQLException sql_exception) {
            sql_exception.printStackTrace();
        }
    }

    /**
     * Closing a database connection
     */
    private void closeConnectionDataBase() {
        try {
            this.connectionBD.close();
        } catch (SQLException sql_exception) {
            sql_exception.printStackTrace();
        }
    }

    /*public void sql_query(String sql) throws SQLException {

        // opening database connection to MySQL server
        createConnectionDataBase();

        // getting Statement object to execute query
        Statement statement = connectionBD.createStatement();

        // executing SELECT query
        ResultSet resultSet = statement.executeQuery(sql);

        *//*while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String author = resultSet.getString(3);
            System.out.printf("id: %d, name: %s, author: %s %n", id, name, author);
        }*//*

        // closing streams
        closeConnectionDataBase();

        //close statement
        try {
            statement.close();
        } catch (SQLException se) {
        }

        //close resultSet
        try {
            resultSet.close();
        } catch (SQLException se) {
        }

    }*/


    /*public ArrayList<String> getNameTeacher() {

        ArrayList<String>


        return
    }*/


}
