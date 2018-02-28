package com.mvc.board.Controller;

import com.mvc.board.command.BCommand;
import com.mvc.board.command.BListCommand;
import com.mvc.board.command.BWriteCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BController {

    BCommand command;

    @RequestMapping("/list")
    public String list(Model model){

        System.out.println("start list()......");

        command = new BListCommand();
        command.execute(model);


        return "";
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

        return "";
    }


}
