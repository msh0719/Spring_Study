package com.mvc.board.command;

import com.mvc.board.dao.BDao;
import com.mvc.board.dto.BDto;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class BContentCommand implements BCommand {

    @Override
    public void execute(Model model) {

        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
        String bId = request.getParameter("bId");

        BDao dao = new BDao();
        BDto dto = dao.contentView(bId);

        model.addAttribute("content_view", dto);

    }

}
