package com.itenjoy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itenjoy.vo.ActionForward;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("LogoutAction 안으로 들어왔음");
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("id"));
		session.invalidate();
		
		request.setAttribute("message", "logout");
		ActionForward forward = new ActionForward();
		forward.setPath("shopMain.do");
		
		return forward;
	}
	
	

}
