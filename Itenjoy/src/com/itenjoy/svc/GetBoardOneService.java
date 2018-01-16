package com.itenjoy.svc;

import java.sql.Connection;

import com.itenjoy.dao.BoardDAO;
import com.itenjoy.vo.BoardVO;

import common.JdbcUtil;

public class GetBoardOneService {
	
	public BoardVO getBoardOne(int num) throws Exception {
		
		System.out.println("GetBoardOneService 안으로 들어왔음");
		BoardVO boardVO = new BoardVO();
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		boardDAO.setConnection(con);
		boardVO = boardDAO.getArticle(num);
		JdbcUtil.commit(con);
		JdbcUtil.close(con);
		
		return boardVO;
	}

}
