package com.itenjoy.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itenjoy.svc.InsertBoardProService;
import com.itenjoy.vo.ActionForward;
import com.itenjoy.vo.BoardVO;

public class InsertBoardProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("InsertBoardProAction 안으로 들어왔음");
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(Integer.parseInt(request.getParameter("num")));
		boardVO.setWriter((String)request.getParameter("writer"));
		boardVO.setEmail((String)request.getParameter("email"));
		boardVO.setSubject((String)request.getParameter("subject"));
		boardVO.setPasswd((String)request.getParameter("passwd"));
		boardVO.setReg_date(new Timestamp(System.currentTimeMillis()));
		boardVO.setRef(Integer.parseInt(request.getParameter("ref")));
		boardVO.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		boardVO.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		boardVO.setContent((String)request.getParameter("content"));
		boardVO.setIp(request.getRemoteAddr());
		
		InsertBoardProService insertBoardProService = new InsertBoardProService();
		insertBoardProService.insertBoard(boardVO);
		
		ActionForward forward = new ActionForward();
		forward.setPath("getBoardList.do?");
		return forward;
	}
	
}
