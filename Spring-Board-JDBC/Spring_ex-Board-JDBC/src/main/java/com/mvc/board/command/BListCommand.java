package com.mvc.board.command;

import com.mvc.board.dao.BDao;
import com.mvc.board.dto.BDto;
import org.springframework.ui.Model;

import java.util.ArrayList;

public class BListCommand implements BCommand {
    /**
     * jsp page로 dao 의 값을 뿌리는 명령을 실행하는 부분
     */
    @Override
    public void execute(Model model) {

        BDao dao = new BDao();
        ArrayList<BDto> dtos = dao.list();

        model.addAttribute("list", dtos);

    }
}
