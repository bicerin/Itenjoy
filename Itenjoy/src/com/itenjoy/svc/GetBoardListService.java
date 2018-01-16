package com.itenjoy.svc;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.itenjoy.dao.BoardDAO;
import com.itenjoy.vo.BoardVO;

import common.JdbcUtil;

public class GetBoardListService {
	
	public List<BoardVO> getBoardList(int startRow, int endRow) throws Exception {
		
		System.out.println("GetBoardListService의 getBoardList() 안으로 들어왔음");
		List<BoardVO> boardVOList = new ArrayList<BoardVO>();
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		boardDAO.setConnection(con);
		boardVOList = boardDAO.getArticleList(startRow, endRow);
		JdbcUtil.close(con);
		
		return boardVOList;
	}
	
	public int getBoardCount() throws Exception {
		
		System.out.println("GetBoardListService의 getBoardCount() 안으로 들어왔음");
		int count;
		BoardDAO boardDAO = BoardDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		boardDAO.setConnection(con);
		count = boardDAO.getArticleCount();
		JdbcUtil.close(con);
		
		return count;
	}
}
