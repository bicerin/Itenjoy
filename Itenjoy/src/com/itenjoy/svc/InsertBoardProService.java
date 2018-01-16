package com.itenjoy.svc;

import java.sql.Connection;

import com.itenjoy.dao.BoardDAO;
import com.itenjoy.vo.BoardVO;

import common.JdbcUtil;

public class InsertBoardProService {
	
	public void insertBoard(BoardVO boardVO) throws Exception {
		
		System.out.println("InsertBoardProService 안으로 들어왔음");
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		boardDAO.setConnection(con);
		boardDAO.insertArticle(boardVO);
		JdbcUtil.commit(con);
		JdbcUtil.close(con);
		
		return;
	}

}
