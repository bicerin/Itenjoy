package com.itenjoy.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itenjoy.svc.InsertMemberService;
import com.itenjoy.vo.ActionForward;
import com.itenjoy.vo.CustomerVO;

public class InsertMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("InsertMemberAction 안으로 들어왔음");
		ActionForward forward = null;
		CustomerVO customer = new CustomerVO();
		customer.setId(request.getParameter("id"));
		customer.setPasswd(request.getParameter("passwd"));
		customer.setName(request.getParameter("name"));
		customer.setTel(request.getParameter("tel"));
		customer.setAddress(request.getParameter("address"));
		customer.setEmail(request.getParameter("email"));
		customer.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		InsertMemberService insertMemberService = new InsertMemberService();
		boolean isWriteSuccess = insertMemberService.insertMember(customer);
		
		if(!isWriteSuccess) {
			request.setAttribute("message", "joinFail");
		}else {
			request.setAttribute("message", "joinSuccess");
		}
		forward = new ActionForward();
		forward.setPath("shopMain.do");
		return forward;
	}
	
	

}
