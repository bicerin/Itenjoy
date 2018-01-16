package com.itenjoy.svc;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.itenjoy.dao.CartDAO;
import com.itenjoy.dao.ProductDAO;
import com.itenjoy.vo.CartVO;
import com.itenjoy.vo.ProductVO;

import common.JdbcUtil;

public class IntroListService {

	public List<ProductVO> getIntroList() throws Exception {
		
		List<ProductVO> list= new ArrayList<ProductVO>();
		ProductDAO productDAO = ProductDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		productDAO.setConnection(con);
		
		for(int i=1; i<=8; i++) {
			List<ProductVO> tmp = productDAO.getIntroList(i + "00");
			list.addAll(tmp);
		}
		
		JdbcUtil.close(con);
		return list;
	}
	
	public List<CartVO> getCartList(String id) throws Exception {
		
		List<CartVO> cartList = new ArrayList<CartVO>();
		CartDAO cartDAO = CartDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		cartDAO.setConnection(con);
		cartList = cartDAO.getCart(id);
		JdbcUtil.close(con);
		return cartList;
	}
	
	
}
