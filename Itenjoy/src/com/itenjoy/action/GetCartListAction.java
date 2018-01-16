package com.itenjoy.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itenjoy.svc.GetCartListService;
import com.itenjoy.svc.IntroListService;
import com.itenjoy.vo.ActionForward;
import com.itenjoy.vo.CartVO;

public class GetCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("CartListAction 안으로 들어왔음");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		List<CartVO> cartList = new ArrayList<CartVO>();
		
		GetCartListService getCartListService = new GetCartListService();
		cartList = getCartListService.getCartList(id);
		request.setAttribute("cartList", cartList);
		
		// 장바구니옆() 안에 상품갯수 표시하는 코드
		IntroListService introListService = new IntroListService();
		List<CartVO> cartSize = introListService.getCartList(id);
		session.setAttribute("cartListSize", cartSize.size());
		
		ActionForward forward = new ActionForward();
		forward.setPath("shopping/getCartList.jsp");
		return forward;
		 
	}
	
	

}
