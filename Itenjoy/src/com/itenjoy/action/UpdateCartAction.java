package com.itenjoy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itenjoy.svc.GetProductOneService;
import com.itenjoy.svc.UpdateCartService;
import com.itenjoy.vo.ActionForward;

public class UpdateCartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("UpdateCartAction 안으로 들어왔음");
		String id = request.getParameter("id");
		
		UpdateCartService updateCartService = new UpdateCartService();
		int cartSize = updateCartService.getCartList(id);
		
		for(int i=0; i<cartSize; i++) {
			int cart_id = Integer.parseInt(request.getParameter("cart_id" + i));
			byte buy_count = Byte.parseByte(request.getParameter("buy_count" + i));
			int com_id = Integer.parseInt(request.getParameter("com_id" + i));
			GetProductOneService getProductOneService = new GetProductOneService();
			int stock = getProductOneService.getProductOne(com_id).getCom_count();
			
			if(stock >= buy_count) {
				updateCartService.updateCount(cart_id, buy_count);
			}else {
				request.setAttribute("message", "재고부족");
				request.setAttribute("stock", stock);
				System.out.println(stock);
				request.setAttribute("com_title", getProductOneService.getProductOne(com_id).getCom_title());
				break;
			}
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("getCartList.do");
		
		return forward;
	}
	
	

}
