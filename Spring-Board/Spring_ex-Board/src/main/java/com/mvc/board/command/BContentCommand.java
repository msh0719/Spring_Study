package com.mvc.board.command;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class BContentCommand implements BCommand {

    @Override
    public void execute(Model model) {

        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        String bId = request.getParameter("bID");

     }
}
