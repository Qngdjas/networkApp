package com.qioki.Web;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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

    /**
     * The method checks whether the user with the entered data exists
     * @param username entered user name
     * @param password entered user password
     * @return id of entered teacher
     */
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

    /**
     * The method fetches names of groups from data base
     * @param teacherId id of teacher
     * @return list of groups attached to teacher
     */
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

    /**
     * The method fetches names of students from data base
     * @param groupName name of group
     * @return list of students attached to group
     */
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

    /**
     * The method fetches teacher full name
     * @param teacherId id of teacher
     * @return teacher full name
     */
    public String getTeacher(int teacherId){
        this.createConnectionDataBase();
        String query = "select FIO_teacher from teacher where id_teacher=?";
        String result = "";
        try {
            PreparedStatement statement = this.connectionBD.prepareStatement(query);
            statement.setString(1, Integer.toString(teacherId));
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                result = resultSet.getString(1);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        finally {
            this.closeConnectionDataBase();
            return result;
        }
    }

    /**
     * The method checks availability of data base fetching some data
     * @return result of checking (true - available /false - not available)
     */
    public boolean checkAvailability(){
        this.createConnectionDataBase();
        boolean result = false;
        String query = "select * from type_activity";
        try{
            PreparedStatement statement = this.connectionBD.prepareStatement(query);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()){
                result = true;
            }

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        finally {
            this.closeConnectionDataBase();
            return result;
        }
    }
    public ArrayList<String[]> getMarks(String date,
                                        String topic,
                                        String discipline,
                                        String lessonType){
        String query = "select id_student, id_estimation " +
                "from journal " +
                "where date=? and topic=? and " +
                "id_disciplines=(select id_disciplines from disciplines where disciplines=?) and " +
                "id_type=(select id_type from type_activity where type=?)";
        ArrayList<String[]> result = new ArrayList<>();
        this.createConnectionDataBase();
        try{
            PreparedStatement statement = this.connectionBD.prepareStatement(query);
            statement.setString(1, date);
            statement.setString(2, topic);
            statement.setString(3, discipline);
            statement.setString(4, lessonType);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()){
                String[] mark = new String[] {resultSet.getString(1), resultSet.getString(2)};
                result.add(mark);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        finally {
            this.closeConnectionDataBase();
            return result;
        }
    }

    public void insertMarks(String date,
                            String topic,
                            String discipline,
                            String lessonType,
                            HashMap<String, String> marks
                            ){
        this.createConnectionDataBase();
        String query = "insert into journal (id_disciplines, " +
                "id_student, id_type, id_estimation, date, topic) " +
                "values (" +
                "(select id_disciplines from disciplines where disciplines=?)," +
                "(select id_student from students where FIO_student=?)," +
                "(select id_type from type_activity where type=?)," +
                "(select  id_estimation from estimation where estimation=?)," +
                "?, ?)";
        try {
            for(String name: marks.keySet()){
                PreparedStatement statement = this.connectionBD.prepareStatement(query);
                statement.setString(1, discipline);
                statement.setString(2, name);
                statement.setString(3, lessonType);
                statement.setString(4, marks.get(name));
                statement.setString(5, date);
                statement.setString(6, topic);
                statement.execute();
            }

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        finally {
            this.closeConnectionDataBase();
        }
    }
//
//    public void updateMarks(String date,
//                            String topic,
//                            String discipline,
//                            String lessonType,
//                            HashMap<String, String> marks){
//        ArrayList<String[]> journalIds = getJournalRecords(date, topic, discipline, lessonType);
//        String query = "update journal" +
//                "set id_estimation=?" +
//                "where id_journal=? and id_student=?";
//        this.createConnectionDataBase();
//        try{
//            for (String[] temp: journalIds ){
//                if
//            }
//            PreparedStatement statement = this.connectionBD.prepareStatement(query);
//            statement.setString(1, );
//
//        }catch (SQLException throwables){
//            throwables.printStackTrace();
//        }
//        finally {
//            this.closeConnectionDataBase();
//        }
//
//    }


    public String getStudent(int studentId){
        this.createConnectionDataBase();
        String query = "select FIO_student from students where id_student=?";
        String result = "";
        try {
            PreparedStatement statement = this.connectionBD.prepareStatement(query);
            statement.setString(1, Integer.toString(studentId));
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                result = resultSet.getString(1);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        finally {
            this.closeConnectionDataBase();
            return result;
        }
    }

    public String getMark(int markId){
        this.createConnectionDataBase();
        String query = "select estimation from estimation where id_estimation=?";
        String result = "";
        try {
            PreparedStatement statement = this.connectionBD.prepareStatement(query);
            statement.setString(1, Integer.toString(markId));
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                result = resultSet.getString(1);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        finally {
            this.closeConnectionDataBase();
            return result;
        }
    }


    public ArrayList<String[]> getJournalRecords(String date,
                                               String topic,
                                               String discipline,
                                               String lessonType){
        String query = "Select id_journal, id_student from journal " +
                "where date=? and " +
                "topic=? and " +
                "id_disciplines=(select id_disciplines from disciplines where disciplines=?) and " +
                "id_type=(select id_type from type_activity where type=?)";
        ArrayList<String[]> result = new ArrayList<>();
        this.createConnectionDataBase();
        try{
            PreparedStatement statement = this.connectionBD.prepareStatement(query);
            statement.setString(1, date);
            statement.setString(2, topic);
            statement.setString(3, discipline);
            statement.setString(4, lessonType);
            System.out.println(statement);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                String[] temp = new String[] {resultSet.getString(1), resultSet.getString(2)};
                result.add(temp);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        finally {
            this.closeConnectionDataBase();
            return result;
        }
    }

    public ArrayList<String> getDisciplines(){
        ArrayList<String> result = new ArrayList<>();
        String query = "select disciplines from disciplines";
        this.createConnectionDataBase();
        try{
            PreparedStatement statement = this.connectionBD.prepareStatement(query);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()){
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


//    по дате, теме, типу занятия и дисциплине создаем записи по студентам в journal


//    public String getTeacher(int teacherId){
//        this.createConnectionDataBase();
//        String query = "select FIO_teacher from teacher where id_teacher=?";
//        String result = "";
//        try {
//            PreparedStatement statement = this.connectionBD.prepareStatement(query);
//            statement.setString(1, Integer.toString(teacherId));
//            statement.execute();
//            ResultSet resultSet = statement.getResultSet();
//            while (resultSet.next()){
//                result = resultSet.getString(1);
//            }
//        }catch (SQLException throwables){
//            throwables.printStackTrace();
//        }
//        finally {
//            this.closeConnectionDataBase();
//            return result;
//        }
//    }

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
