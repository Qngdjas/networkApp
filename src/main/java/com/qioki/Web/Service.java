package com.qioki.Web;

import java.sql.*;
import java.util.ArrayList;

public class Service {

    DAO dao = new DAO();

    public static void main(String[] args) throws SQLException {
        DAO d = new DAO();
        System.out.println(d.getStudents("ПИ 1-17-"));
    }

    public int authentication(String username,
                              String password){
        return dao.auth(username, password);
    }

    public ArrayList<String> getGroups(int teacherId){
        return dao.getGroups(teacherId);
    }

    public ArrayList<String> getStudents(String groupName){
        return dao.getStudents(groupName);
    }
}
