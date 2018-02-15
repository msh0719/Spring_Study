package com.javalec.ex.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


//redirect 한 페이지에서 요청이 들어온 후 이를 다른 페이지로 redirect하기 위한 기능이다.
@Controller
public class RedirectController {

    @RequestMapping("/studentConfirm")
    public String studentRedirect(HttpServletRequest httpServletRequest, Model model){

        String id = httpServletRequest.getParameter("id");
        if(id.equals("abc")) {
            return "redirect:studentOk";
        }

        return "redirect:studentNg";
    }

    @RequestMapping("/studentOk")
    public String studentOk(Model model){

        return "student/studentOk";
    }


    @RequestMapping("/studentNg")
    public String studentNg(Model model){

        return "student/studentNg";
    }

    @RequestMapping("/studentURL1")
    public String studentURL1(Model model) {

        return "redirect:http://localhost:8080/studentURL1";
    }


    @RequestMapping("/studentURL2")
    public String studentURL2(Model model) {

        return "redirect:studentURL2";
    }

    @RequestMapping("/student/studentURL2")
    public String studentURL22(Model model) {

        return "redirect:http://localhost:8080/studentURL2.jsp";
    }
}
