package com.itenjoy.svc;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.itenjoy.dao.CartDAO;
import com.itenjoy.vo.CartVO;

import common.JdbcUtil;

public class GetCartListService {
	
	public List<CartVO> getCartList(String id) throws Exception {
		
		System.out.println("CartListService 안으로 들어왔음");
		List<CartVO> cartList = new ArrayList<CartVO>();
		CartDAO cartDAO = CartDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		cartDAO.setConnection(con);
		cartList = cartDAO.getCart(id);
		JdbcUtil.close(con);
		
		return cartList;
		
	}

}
