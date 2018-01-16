package com.itenjoy.svc;

import java.sql.Connection;

import com.itenjoy.dao.BoardDAO;

import common.JdbcUtil;

public class DeleteBoardService {
	
	public void deleteBoard(int num, String passwd) throws Exception {
		
		System.out.println("DeleteBoardService 안으로 들어왔음");
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		boardDAO.setConnection(con);
		boardDAO.deleteArticle(num, passwd);
		JdbcUtil.commit(con);
		JdbcUtil.close(con);
		
		return;
	}

}
