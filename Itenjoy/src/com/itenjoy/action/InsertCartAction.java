package com.itenjoy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itenjoy.svc.InsertCartService;
import com.itenjoy.vo.ActionForward;
import com.itenjoy.vo.CartVO;

public class InsertCartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("InsertCartAction 안으로 들어왔음");
		CartVO cartVO = new CartVO();
		cartVO.setBuyer(request.getParameter("id"));
		cartVO.setCom_id(Integer.parseInt(request.getParameter("com_id")));
		cartVO.setCom_title(request.getParameter("com_title"));
		cartVO.setBuy_price(Integer.parseInt(request.getParameter("com_price")));
		cartVO.setBuy_count(Byte.parseByte(request.getParameter("buy_count")));
		cartVO.setCom_image((String)request.getParameter("com_image"));
		
		InsertCartService insertCartService = new InsertCartService();
		insertCartService.insertCart(cartVO);
		
		ActionForward forward = new ActionForward();
		forward.setPath("getCartList.do");
		return forward;
		
		
	}
	
	

}
