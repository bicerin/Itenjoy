package com.itenjoy.svc;

import java.sql.Connection;

import com.itenjoy.dao.ProductDAO;
import com.itenjoy.vo.ProductVO;

import common.JdbcUtil;

public class GetProductOneService {
	
	public ProductVO getProductOne(int com_id) throws Exception {
		
		System.out.println("GetProductOneService 안으로 들어왔음");
		ProductVO product = new ProductVO();
		ProductDAO productDAO = ProductDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		productDAO.setConnection(con);
		product = productDAO.getProductOne(com_id);
		JdbcUtil.close(con);
		
		return product;
	}

}
