package com.qioki.Web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class WebController {

    String currentGroup = "";
    Service service = new Service();

    @RequestMapping("/")
    public String logIn(HttpServletRequest request) {
        System.out.println(request.getRemoteAddr());
        return "index";
    }

    @RequestMapping("/auth")
    public String authorization(@RequestParam("login") String login,
                                @RequestParam("password") String password,
                                Model model){


        int teacherId = service.authentication(login, password);
        if (teacherId != 0) {
            HashMap<String, String> name = service.getTeacherName(teacherId);
            model.addAttribute("firstname", name.get("firstname"));
            model.addAttribute("lastname", name.get("lastname"));
            model.addAttribute("fathername", name.get("fathername"));
            model.addAttribute("groups", service.getGroups(teacherId));
            return "groups";
        }

        return "index";
    }

    @RequestMapping("/group")
    public String group(@RequestParam("group") String group,
                        Model model){
        currentGroup = group;
        model.addAttribute("disciplines", service.getDisciplines());
        model.addAttribute("group", group);
        model.addAttribute("students", service.getStudents(group));
        return "group-info";
    }

//    @RequestMapping("/test")
//    public String text(@RequestParam("date") String date,
//                       @RequestParam("topic") String topic,
//                       @RequestParam("lessontype") String lessonType,
//                       Model model){
//        System.out.println(date);
//        System.out.println(topic);
//        System.out.println(lessonType);
//        return "index";
//    }
    @RequestMapping("/test")
    public String text(@RequestParam Map<String, String> allParams, Model model){
        service.insertMarks(allParams);
        return "group-info-mark";
    }



//    @RequestMapping(value="/sheet")
//    public String sheetMethod(@RequestParam("firstname") String firstname,
//                              @RequestParam("lastname") String lastname,
//                              @RequestParam("fathername") String fathername,
//                              Model model
//                              ) {
//        model.addAttribute("firstname", firstname);
//        model.addAttribute("lastname", lastname);
//        model.addAttribute("fathername", fathername);
//        return "completed_auth";
//    }
//
//    @RequestMapping("/group-info")
//    public String getGroupInfo(@RequestParam(name="groupName", required=true) String groupName, Model model) {
//        model.addAttribute("groupName", groupName);
//        return "group-info";
//    }
//
//    @RequestMapping("/groups")
//    public String getGroups(@RequestParam(name="teacherName", required=false) String teacherName, Model model) {
//        System.out.println(model.getAttribute("firstname"));
//        model.addAttribute("teacherName", teacherName);
//        return "groups";
//    }

}
