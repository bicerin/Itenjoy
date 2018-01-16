package com.itenjoy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itenjoy.svc.DeleteBoardService;
import com.itenjoy.vo.ActionForward;

public class DeleteBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("DeleteBoardAction 안으로 들어왔음");
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");
		DeleteBoardService deleteBoardService = new DeleteBoardService();
		deleteBoardService.deleteBoard(num, passwd);
		
		ActionForward forward = new ActionForward();
		forward.setPath("getBoardList.do");
		return forward;
	}

}
