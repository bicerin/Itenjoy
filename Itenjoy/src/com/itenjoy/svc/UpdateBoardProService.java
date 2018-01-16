package com.itenjoy.svc;

import java.sql.Connection;

import com.itenjoy.dao.BoardDAO;
import com.itenjoy.vo.BoardVO;

import common.JdbcUtil;

public class UpdateBoardProService {
	
	public int UpdateBoard(BoardVO boardVO) throws Exception {
		
		System.out.println("UpdateBoardProService 안으로 들어왔음");
		int passWdCheck = 0;
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		boardDAO.setConnection(con);
		passWdCheck = boardDAO.updateArticle(boardVO);
		JdbcUtil.commit(con);
		JdbcUtil.close(con);
		System.out.println(passWdCheck);
		
		return passWdCheck;
	}

}
