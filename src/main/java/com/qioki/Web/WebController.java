package com.qioki.Web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @RequestMapping("/")
    public String logIn() {
        return "index";
    }

    @RequestMapping("/auth")
    public String authorization(@RequestParam("login") String login,
                                @RequestParam("password") String password,
                                Model model){
//        Если имя и пароль с базы совпадает с пришедшими, то дергаем фио из базы и кидает в страницу
        if (login.equals("Anton") & password.equals("anton")){

            String[] groups = new String[] {"PI", "KI", "SI"};
            model.addAttribute("groups", groups);
            return "groups";
        }

        return "index";
    }

    @RequestMapping("/group")
    public String group(@RequestParam("group") String group,
                        Model model){
        model.addAttribute("group", group);
        return "group-info";
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
