package com.qioki.Web;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Service {

    DAO dao = new DAO();

    public static void main(String[] args) throws SQLException {
        DAO d = new DAO();
//        HashMap<String, String> a = new HashMap<>();
//        a.put("Шлыков Нестор Ульянович", "Удовлетворительно");
//        a.put("Шемякина Лариса Романовна", "Хорошо");
//        d.insertMarks("2020", "test", "math", "lecture", a);
    }

    /**
     * The method fetches data from data base
     * @param username name of user
     * @param password password of user
     * @return id of user
     */
    public int authentication(String username,
                              String password){
        return dao.auth(username, password);
    }

    /**
     * The method fetches data from data base
     * @param teacherId id of teacher
     * @return groups attached to a teacher
     */
    public ArrayList<String> getGroups(int teacherId){
        return dao.getGroups(teacherId);
    }

    /**
     * The method fetches data from data base
     * @param groupName name of group
     * @return list of students attached to a group
     */
    public ArrayList<String> getStudents(String groupName){
        return dao.getStudents(groupName);
    }

    /**
     * The method fetches data from data base and split it to HashMap
     * @param teacherId id of teacher
     * @return HashMap with first name, last name and father name
     */
    public HashMap<String, String> getTeacherName(int teacherId){
        String[] name = dao.getTeacher(teacherId).split(" ");
        HashMap<String, String> nameList = new HashMap<>();
        nameList.put("firstname", name[0]);
        nameList.put("lastname", name[1]);
        nameList.put("fathername", name[2]);
       return nameList;
    }

    public void insertMarks(Map<String, String> params) {
        HashMap<String, String> marks = new HashMap<>();
        for(String key: params.keySet()){
            if (key.contains("mark|")){
                marks.put(key.replace("mark|", ""), params.get(key));
            }
        }

        boolean check = true;
        for(String mark: marks.values()){
            if  (!mark.equals("-")){
                check = false;
                break;
            }
        }
        if (check){
            //Получить все оценки
        }
        else{
            dao.insertMarks(params.get("date"),
                params.get("topic"),
                params.get("disciplines"),
                params.get("lessontype"),
                marks);
        }
    }

    public ArrayList<String> getDisciplines(){
        return dao.getDisciplines();
    }
}

