package com.itenjoy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itenjoy.svc.UpdateBoardProService;
import com.itenjoy.vo.ActionForward;
import com.itenjoy.vo.BoardVO;

public class UpdateBoardProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("UpdateBoardProAction 안으로 들어왔음");
		String num = request.getParameter("num");
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(Integer.parseInt(num));
		boardVO.setWriter((String)request.getParameter("writer"));
		boardVO.setEmail((String)request.getParameter("email"));
		boardVO.setSubject((String)request.getParameter("subject"));
		boardVO.setPasswd((String)request.getParameter("passwd"));
		boardVO.setContent((String)request.getParameter("content"));
		
		UpdateBoardProService updateBoardProService = new UpdateBoardProService();
		updateBoardProService.UpdateBoard(boardVO);
		
		ActionForward forward = new ActionForward();
		forward.setPath("getBoardOne.do?num=" + num);
		
		return forward;
		
		
		
	}

}
