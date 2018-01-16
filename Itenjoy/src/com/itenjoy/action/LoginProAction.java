package com.itenjoy.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itenjoy.svc.LoginProService;
import com.itenjoy.vo.ActionForward;

public class LoginProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("LoginProAction 안으로 들어왔음");
		HttpSession session = request.getSession();
		ActionForward forward = null;
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		int check = 0;
		
		LoginProService loginProService = new LoginProService();
		check = loginProService.userCheck(id, passwd);
		
		if(check == 1) {
			session.setAttribute("id", id);
			forward = new ActionForward();
			forward.setPath("shopMain.do");
		} else if(check == 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 맞지 않습니다');");
			out.println("history.back();");
			out.println("</script>");
		} else if(check == -1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디가 존재하지 않습니다');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}
	
	

}
