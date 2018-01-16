package com.itenjoy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itenjoy.svc.GetBoardOneService;
import com.itenjoy.vo.ActionForward;
import com.itenjoy.vo.BoardVO;

public class GetBoardOneAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("GetBoardOneAction 안으로 들어왔음");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		GetBoardOneService getBoardOneService = new GetBoardOneService();
		BoardVO boardVO = new BoardVO();
		boardVO = getBoardOneService.getBoardOne(num);
		
		ActionForward forward = new ActionForward();
		forward.setPath("board/getBoardOne.jsp");
		request.setAttribute("boardVO", boardVO);
		request.setAttribute("pageNum", pageNum);
		
		return forward;
	}

}
