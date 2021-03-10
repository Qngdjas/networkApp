package com.qioki.Web;

import java.sql.*;

public class Service {

    String a;
    public Service(String dataBaseHost,
                   String dataBaseName,
                   String dataBaseUser,
                   String dataBasePass) {
    }

    public static void art() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/journal_rp",
                                                            "root",
                                                            "");
        Statement stmnt = connection.createStatement();
        ResultSet res = stmnt.executeQuery("Select * from teacher");
        while (res.next()){
            System.out.printf("id: %d, name: %s\n", res.getInt(1), res.getString(2));
        }

        connection.close();
    }

    public static void main(String[] args) throws SQLException {
        art();
    }
}
