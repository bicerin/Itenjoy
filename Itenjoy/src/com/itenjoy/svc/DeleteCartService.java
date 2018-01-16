package com.itenjoy.svc;

import java.sql.Connection;

import com.itenjoy.dao.CartDAO;

import common.JdbcUtil;

public class DeleteCartService {
	
	public void deleteCart(int cart_id) throws Exception {
		
		System.out.println("DeleteCartService 안으로 들어왔음");
		int deleteCheck = 0;
		CartDAO cartDAO = CartDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		cartDAO.setConnection(con);
		deleteCheck = cartDAO.deleteCart(cart_id);
		
		if(deleteCheck > 0) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
	}
	
	public void deleteCart(String[] chkArr) throws Exception {
		
		System.out.println("DeleteCartService 안으로 들어왔음");
		int deleteCheck = 0;
		CartDAO cartDAO = CartDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		cartDAO.setConnection(con);
		
		for(int i=0; i<chkArr.length; i++) {
			deleteCheck = cartDAO.deleteCart(Integer.parseInt(chkArr[i]));
		}
		if(deleteCheck > 0) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
	}

}
