package com.itenjoy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itenjoy.svc.DeleteCartService;
import com.itenjoy.vo.ActionForward;

public class DeleteCartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("DeleteCartAction 안으로 들어왔음");
		ActionForward forward = null;
		String[] chkArr = request.getParameterValues("selected");
		
		if(chkArr == null) { // 체크박스 선택없이 '삭제'버튼 눌렀을 경우
			int cart_id = Integer.parseInt(request.getParameter("cart_id"));
			
			DeleteCartService deleteCartService = new DeleteCartService();
			deleteCartService.deleteCart(cart_id);
		} else {			// 체크박스 선택하여 '선택상품 삭제' 버튼 눌렀을 경우
			DeleteCartService deleteCartService = new DeleteCartService();
			deleteCartService.deleteCart(chkArr);
		}
		
		forward = new ActionForward();
		forward.setPath("getCartList.do");
		return forward;
	}
}
