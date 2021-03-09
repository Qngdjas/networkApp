package com.qioki.Web;

import org.apache.tomcat.util.net.jsse.JSSEUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    Connection connectionBD = null;
    //database link
    private final String DB_URL = "jdbc:mysql://localhost:3306/journal_rp";

    /**
     * Creating a database connection
     */
    private void createConnectionDataBase(/*String user, String password*/) {
        try {
            this.connectionBD = DriverManager.getConnection(DB_URL, "root", "");
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

    public int auth(String username, String password) {
        int result = 0;
        this.createConnectionDataBase();
        String query = "select id_teacher from login" +
                " where teacher_login=? and teacher_password=?";
        try {
            PreparedStatement statement = this.connectionBD.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            boolean hasResult = resultSet.next();
            if (hasResult) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            this.closeConnectionDataBase();
            return result;
        }
    }

    public ArrayList<String> getGroups(int teacherId){
        this.createConnectionDataBase();
        String query = "SELECT name_group FROM groups " +
                "WHERE groups.id_group in (SELECT id_grup from teacher_groups where id_teacher=?)";
        ArrayList<String> result = new ArrayList<>();
        try{
            PreparedStatement statement = this.connectionBD.prepareStatement(query);
            statement.setString(1, Integer.toString(teacherId));
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                result.add(resultSet.getString(1));
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        finally {
            this.closeConnectionDataBase();
            return result;
        }
    }

    public ArrayList<String> getStudents(String groupName){
        this.createConnectionDataBase();
        ArrayList<String> result = new ArrayList<>();
        String query = "select FIO_student from students" +
                " WHERE id_group in (select id_group from groups where name_group=?)";
        try{
            PreparedStatement statement = this.connectionBD.prepareStatement(query);
            statement.setString(1, groupName);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                result.add(resultSet.getString(1));
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        finally {
            this.closeConnectionDataBase();
            return result;
        }
    }

    public String getTeacher(int teacherId){
        this.createConnectionDataBase();
        String query = "select FIO_teacher where id_teacher=?";
        try {
            PreparedStatement statement = this.connectionBD.prepareStatement(query);
            statement.setString(1, Integer.toString(teacherId));
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                System.out.println();
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        finally {
            this.closeConnectionDataBase();
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
