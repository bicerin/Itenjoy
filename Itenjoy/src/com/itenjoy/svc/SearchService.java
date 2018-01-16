package com.itenjoy.svc;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.itenjoy.dao.ProductDAO;
import com.itenjoy.vo.ProductVO;

import common.JdbcUtil;

public class SearchService {
	
	public List<ProductVO> searchProduct(String keyword) throws Exception {
		
		System.out.println("SearchService 안으로 들어왔음");
		List<ProductVO> productList = new ArrayList<ProductVO>();
		ProductDAO productDAO = ProductDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		productDAO.setConnection(con);
		productList = productDAO.seachProduct(keyword);
		JdbcUtil.close(con);
		
		return productList;
	}

}
