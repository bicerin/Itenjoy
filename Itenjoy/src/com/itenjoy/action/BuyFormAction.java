package com.itenjoy.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itenjoy.svc.BuyFormService;
import com.itenjoy.svc.GetProductOneService;
import com.itenjoy.vo.ActionForward;
import com.itenjoy.vo.CustomerVO;
import com.itenjoy.vo.ProductVO;

public class BuyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("BuyFormAction 안으로 들어왔음");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String[] cart_idArr = request.getParameterValues("selected");
		BuyFormService buyFormService = new BuyFormService();
		ProductVO productVO = new ProductVO();
		List<ProductVO> productVOList = new ArrayList<ProductVO>();
		List<Byte> buy_countList = new ArrayList<Byte>();
		
		// 상품 소개페이지에서 구매 버튼 클릭시
		if(cart_idArr == null) {
			int com_id = Integer.parseInt(request.getParameter("com_id"));
			Byte buy_count = Byte.parseByte(request.getParameter("buy_count"));
			
			productVO = buyFormService.getProductOne(com_id);
			
			productVOList.add(productVO);
			request.setAttribute("productVOList", productVOList);

			buy_countList.add(buy_count);
			request.setAttribute("buy_countList", buy_countList);
			
			session.removeAttribute("cart_idArr");
		}
		
		// 장바구니 페이지에서 구매 버튼 클릭시
		if(cart_idArr != null) {
			GetProductOneService getProductOneService = new GetProductOneService();
			for(int i=0; i<cart_idArr.length; i++) {
				int com_Id = buyFormService.getCartCom_id(Integer.parseInt(cart_idArr[i]));
				productVO = getProductOneService.getProductOne(com_Id);
				productVOList.add(productVO);
				request.setAttribute("productVOList", productVOList);
				
				Byte buy_Count = buyFormService.getCartBuy_count(Integer.parseInt(cart_idArr[i]));
				buy_countList.add(buy_Count);
				request.setAttribute("buy_countList", buy_countList);
				
				session.setAttribute("cart_idArr", cart_idArr); // 구매후 장바구니에서 구매한 상품들 삭제하기 위함
			}
		}
		
		CustomerVO customerVO = new CustomerVO();
		customerVO = buyFormService.getCustomerOne(id);
		request.setAttribute("customerVO", customerVO);
		
		ActionForward forward = new ActionForward();
		forward.setPath("shopping/buyForm.jsp");
		return forward;
	}
	
}
