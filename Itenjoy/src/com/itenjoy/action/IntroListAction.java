package com.itenjoy.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itenjoy.svc.IntroListService;
import com.itenjoy.vo.ActionForward;
import com.itenjoy.vo.CartVO;
import com.itenjoy.vo.ProductVO;

public class IntroListAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<ProductVO> introList = new ArrayList<ProductVO>();
		IntroListService introListService = new IntroListService();
		introList = introListService.getIntroList();
		request.setAttribute("introList", introList);
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id != null) {
			List<CartVO> cartList = introListService.getCartList(id);
			session.setAttribute("cartListSize", cartList.size());
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("shopping/shopMain.jsp");
		
		return forward;
	}
}
