package com.javalec.ex.Controller;


import com.javalec.ex.member.StudentInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping("/index")
    public String goIndex(){
        return "index";
    }

    @RequestMapping(method= RequestMethod.GET, value="/student")
    public String goStudent(HttpServletRequest httpServletRequest, Model model){
        System.out.println("RequestMethod.GET");

        String id = httpServletRequest.getParameter("id");
        System.out.println("id : "  + id);
        model.addAttribute("studentId", id);

        return "student/studentId";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/student")
    public ModelAndView goStudent(HttpServletRequest httpServletRequest) {

        System.out.println("RequestMethod.POST");

        String id = httpServletRequest.getParameter("id");
        System.out.println("id : " + id);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("student/studentId");
        mv.addObject("studentId", id);

//		model.addAttribute("studentId", id);

//		return "student/studentId";
        return mv;
    }

    @RequestMapping("/student/studentIndex")
    public String Index(){
        return "/student/studentIndex";
    }

    //@ModelAttribute를 이용해서 studentInfo 라는 별명을 만들어 놓으면 jsp에서 studentInfo 로 사용가능.
    @RequestMapping("/student/studentview")
    public String studentView(@ModelAttribute("studentInfo") StudentInformation studentInformation){
        return "/student/studentview";
    }


}
