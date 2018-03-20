package com.mvc.board.command;

import com.mvc.board.dao.BDao;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class BReplyCommand implements BCommand {
    @Override
    public void execute(Model model) {
        // TODO Auto-generated method stub

        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        String bId = request.getParameter("bId");
        String bName = request.getParameter("bName");
        String bTitle = request.getParameter("bTitle");
        String bContent = request.getParameter("bContent");
        String bGroup = request.getParameter("bGroup");
        String bStep = request.getParameter("bStep");
        String bIndent = request.getParameter("bIndent");

        BDao dao = new BDao();
        dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);

    }
}
