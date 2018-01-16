package com.itenjoy.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itenjoy.svc.SearchService;
import com.itenjoy.vo.ActionForward;
import com.itenjoy.vo.ProductVO;

public class SearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("SearchAction 안으로 들어왔음");
		String keyword = request.getParameter("keyword");
		
		SearchService searchService = new SearchService();
		List<ProductVO> productList = new ArrayList<ProductVO>();
		productList = searchService.searchProduct(keyword);
		
		request.setAttribute("productList", productList);
		request.setAttribute("keyword", keyword);
		request.setAttribute("listSize", productList.size());
		ActionForward forward = new ActionForward();
		forward.setPath("shopping/search.jsp");
		
		return forward;
		
	}

}
