package com.itenjoy.svc;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.itenjoy.dao.ProductDAO;
import com.itenjoy.vo.ProductVO;

import common.JdbcUtil;

public class GetProductListService {
	
	public List<ProductVO> getProductList(String com_kind, String sort) throws Exception {
			
			System.out.println("GetProductListService 안으로 들어왔음");
			List<ProductVO> list= new ArrayList<ProductVO>();
			ProductDAO productDAO = ProductDAO.getInstance();
			Connection con = JdbcUtil.getConnection();
			productDAO.setConnection(con);
			if(sort == null)
				list = productDAO.getProductList(com_kind);
			else
				list = productDAO.getProductList(com_kind, sort);
			JdbcUtil.close(con);
			return list;
		}

}
