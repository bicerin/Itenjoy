package com.itenjoy.action;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itenjoy.svc.BuyProService;
import com.itenjoy.vo.ActionForward;
import com.itenjoy.vo.BuyVO;
import com.itenjoy.vo.ProductVO;

public class BuyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("BuyProAction 안으로 들어왔음");
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		BuyProService buyProService = new BuyProService();
		@SuppressWarnings("unchecked")
		List<ProductVO> productVOList = (ArrayList<ProductVO>)session.getAttribute("productVOList");
		@SuppressWarnings("unchecked")
		List<Byte> buy_countList = (ArrayList<Byte>)session.getAttribute("buy_countList");
		String[] cart_idArr = (String[])session.getAttribute("cart_idArr");
		
		for(int i=0; i<productVOList.size(); i++) {
			BuyVO buyVO = new BuyVO();
			buyVO.setBuyer(id);
			buyVO.setCom_id(productVOList.get(i).getCom_id());
			buyVO.setCom_title(productVOList.get(i).getCom_title());
			buyVO.setBuy_price(productVOList.get(i).getCom_price());
			buyVO.setBuy_count(buy_countList.get(i));
			buyVO.setCom_image(productVOList.get(i).getCom_image());
			buyVO.setDeliveryName((String)request.getParameter("deliveryName"));
			buyVO.setDeliveryTel((String)request.getParameter("deliveryTel"));
			buyVO.setDeliveryAddress((String)request.getParameter("deliveryAddress"));
			buyVO.setMemo((String)request.getParameter("memo"));

			buyProService.insertBuy(buyVO);
		}
		
		if(session.getAttribute("cart_idArr") != null) {
			System.out.println(Arrays.toString(cart_idArr));
			buyProService.deleteCartOrdered(cart_idArr);
		}
		
		forward = new ActionForward();
		forward.setPath("shopMain.do");
		return forward;
	}
	
}
