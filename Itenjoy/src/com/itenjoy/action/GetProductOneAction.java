package com.itenjoy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itenjoy.svc.GetProductOneService;
import com.itenjoy.vo.ActionForward;
import com.itenjoy.vo.ProductVO;

public class GetProductOneAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("GetProductOneAction 안으로 들어왔음");
		int com_id = Integer.parseInt(request.getParameter("com_id"));
		ProductVO product = new ProductVO();
		GetProductOneService getProductOneService = new GetProductOneService();
		product = getProductOneService.getProductOne(com_id);
		request.setAttribute("product", product);
		
		ActionForward forward = new ActionForward();
		forward.setPath("shopping/getProductOne.jsp"); 
		
		return forward;
	}
	
}
