package com.mvc.board.dao;



import com.mvc.board.dto.ContentDto;

import java.util.ArrayList;

public interface IDao {
	
	public ArrayList<ContentDto> listDao();
	public void writeDao(String mWriter, String mContent);
	public ContentDto viewDao(String strID);
	public void deleteDao(String bId);
	
}
