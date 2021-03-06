package com.mvc.board.Controller;

import com.mvc.board.command.*;
import com.mvc.board.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BController {

    BCommand command;
    public JdbcTemplate template;

    // 자동으로 세터를 호출해서 JdbcTemplate을 사용하여야 한다.

    @Autowired  // 자동으로 알아서 객체를 생성되서 할당된다.
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
        Constant.template  = this.template; // 어디서나 사용할 수 있도록 설정하는 방법이다.
    }

    @RequestMapping("/list")
    public String list(Model model){

        System.out.println("start list()......");

        command = new BListCommand();
        command.execute(model);

        return "list";
    }

    @RequestMapping("/write_view")
    public String write_view(Model model){

        System.out.println("start write_view()...");

        return "write_view";   // jsp view 페이지로 바로 보내면 되기 때문에 바로 리턴한다.
    }

    @RequestMapping("/write")
    public String write(HttpServletRequest request, Model model){

        System.out.println("start write().....");

        model.addAttribute("request", request);
        command = new BWriteCommand();
        command.execute(model);

        return "redirect:list";
    }

    @RequestMapping("/content_view")
    public String content_view(HttpServletRequest request, Model model){

        System.out.println("start content_view().....");

        model.addAttribute("request", request);
        command = new BContentCommand(); // content 를 가져오는 명령을 실행하는 부분
        command.execute(model);

        return "content_view";
    }

    @RequestMapping(method= RequestMethod.POST, value = "/modify")
    public String modify(HttpServletRequest request, Model model){
        System.out.println("modify()");

        model.addAttribute("request", request);
        command = new BModifyCommand();
        command.execute(model);

        return "redirect:list"; //수정 후 리스트로 돌아가게 하기 위해서
    }

    @RequestMapping("/reply_view")
    public String reply_view(HttpServletRequest request, Model model){
        System.out.println("reply_view()...");

        model.addAttribute("request", request);
        command = new BReplyViewCommand();
        command.execute(model);

        return "reply_view";
    }

    @RequestMapping("/reply")
    public String reply(HttpServletRequest request, Model model){
        System.out.println("reply()....");

        model.addAttribute("request", request);
        command = new BReplyCommand();
        command.execute(model);

        return "redirect:list";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, Model model){
        System.out.println("delete().....");

        model.addAttribute("request", request);
        command = new BDeleteCommand();
        command.execute(model);

        return "redirect:list";
    }

}
