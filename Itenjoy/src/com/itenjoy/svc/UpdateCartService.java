package com.itenjoy.svc;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.itenjoy.dao.CartDAO;
import com.itenjoy.vo.CartVO;

import common.JdbcUtil;

public class UpdateCartService {
	
	public int getCartList(String id) throws Exception {
		
		System.out.println("UpdateCartService 안으로 들어왔음");
		List<CartVO> cartList = new ArrayList<CartVO>();
		CartDAO cartDAO = CartDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		cartDAO.setConnection(con);
		cartList = cartDAO.getCart(id);
		JdbcUtil.close(con);
		
		return cartList.size();
	}
	
	public void updateCount(int cart_id, byte buy_count) throws Exception {
		
		int updateCheck = 0;
		CartDAO cartDAO = CartDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		cartDAO.setConnection(con);
		updateCheck = cartDAO.updateCart(cart_id, buy_count);
		
		if(updateCheck > 0) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
	}

}
