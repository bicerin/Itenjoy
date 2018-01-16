package com.itenjoy.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itenjoy.svc.OrderListService;
import com.itenjoy.vo.ActionForward;
import com.itenjoy.vo.BuyVO;

public class OrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("OrderListAction 안으로 들어왔음");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		List<BuyVO> prodcutVOList = new ArrayList<BuyVO>();
		OrderListService orderListService = new OrderListService();
		prodcutVOList = orderListService.getBuyList(id);
		request.setAttribute("prodcutVOList", prodcutVOList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("shopping/orderList.jsp");
		
		return forward;
		
	}

}
