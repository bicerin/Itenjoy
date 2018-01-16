package com.itenjoy.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itenjoy.svc.GetProductListService;
import com.itenjoy.vo.ActionForward;
import com.itenjoy.vo.ProductVO;

public class GetProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("getProductListAction 안으로 들어왔음");
		String com_kind = (String) request.getParameter("com_kind");
		String sort = (String) request.getParameter("sort");
		List<ProductVO> productList = new ArrayList<ProductVO>();
		GetProductListService getProductListService = new GetProductListService();
		productList = getProductListService.getProductList(com_kind, sort);
		String com_kindName = "";
		switch(productList.get(0).getCom_kind()) {
		case "100": com_kindName = "CPU";		break;
		case "200": com_kindName = "그래픽카드";	break;
		case "300": com_kindName = "RAM"; 		break;
		case "400": com_kindName = "메인보드"; 	break;
		case "500": com_kindName = "HDD"; 		break;
		case "600": com_kindName = "SSD"; 		break;
		case "700": com_kindName = "케이스"; 		break;
		case "800": com_kindName = "파워"; 		break;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("shopping/getProductList.jsp"); 
		request.setAttribute("productList", productList);
		request.setAttribute("com_kindName", com_kindName);
		return forward;
	}
	
	

}
