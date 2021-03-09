package com.qioki.Web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {

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

//        Если имя и пароль с базы совпадает с пришедшими, то дергаем фио из базы и кидает в страницу
        int teacherId = service.authentication(login, password);
        if (teacherId != 0) {
//            service.get
            model.addAttribute("groups", service.getGroups(teacherId));
            return "groups";
        }

        return "index";
    }

    @RequestMapping("/group")
    public String group(@RequestParam("group") String group,
                        Model model){
        model.addAttribute("students", service.getStudents(group));
        return "group-info";
    }

    @RequestMapping("/test")
    public String text(Model model){
        return "index";
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
