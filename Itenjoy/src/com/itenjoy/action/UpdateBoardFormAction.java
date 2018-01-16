package com.itenjoy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itenjoy.svc.GetBoardOneService;
import com.itenjoy.vo.ActionForward;
import com.itenjoy.vo.BoardVO;

public class UpdateBoardFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("UpdateBoardFormAction 안으로 들어왔음");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		BoardVO boardVO = new BoardVO();
		GetBoardOneService getBoardOneService = new GetBoardOneService();
		boardVO = getBoardOneService.getBoardOne(num);
		request.setAttribute("boardVO", boardVO);
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setPath("board/updateBoardForm.jsp");
		
		return forward;
	}

}
