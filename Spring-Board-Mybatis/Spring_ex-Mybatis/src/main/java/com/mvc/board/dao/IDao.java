package com.mvc.board.dao;

import com.mvc.board.dto.ContentDto;

import java.util.ArrayList;

public interface IDao {
         ArrayList<ContentDto> listDao();
         void writeDao(String mWriter, String mContent);
         ContentDto viewDao(String strID);
         void deleteDao(String bId);
}
