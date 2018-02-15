package com.javalec.ex.Controller;

import com.javalec.ex.member.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


/**
 * Handles requests for the application home page.
 */

@Controller
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */

    //HttpServletRequest를 이용하여 데이터를 전송하는 방법
    @RequestMapping("/member/memberView")
    public String viewMember(HttpServletRequest httpServletRequest, Model model){
        String id = httpServletRequest.getParameter("id");
        String pw = httpServletRequest.getParameter("pw");

        model.addAttribute("id",id); //get 방식으로 값 설정
        model.addAttribute("pw", pw);

        return "/member/memberView";
    }

    //Requestparam 어노테이션을 이용해서 데이터를 전송하는 방법
    //RequestParam 은 값을 설정하지 않으면 오류가 발생하게 된다.
    //변수의 값이 많아지면 비효율적인 방법이다.
    @RequestMapping("/member/confirm")
    public String memberConfirm(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model){
        model.addAttribute("identify", id);
        model.addAttribute("password", pw);

        return "/member/confirm";
    }
    //member 클래스를 이용하여 손쉽게 사용
    @RequestMapping("/join/formOK")
    public String join(Member member){

        return "/join/formOK";
    }

    // @PathValriable 어노테이션을 이용하면 경로(path)에 변수를 넣어 요청메소드에서 파라미터로 이용할 수 있다.
    // ex) http://localhost:8080/member/student/10
    // 자주사용하는 방법은 아니다.
    @RequestMapping("member/student/{studentId}")
    public String getStudent(@PathVariable String studentId, Model model){
        model.addAttribute("studentId", studentId);

        return "/member/student";
    }
}
