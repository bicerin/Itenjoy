package com.itenjoy.svc;

import java.sql.Connection;

import com.itenjoy.dao.BuyDAO;
import com.itenjoy.dao.CartDAO;
import com.itenjoy.vo.BuyVO;

import common.JdbcUtil;

public class BuyProService {
	
	public void insertBuy(BuyVO buyVO) throws Exception {
		
		System.out.println("BuyProService의 insertBuy안으로 들어왔음");
		BuyDAO buyDAO = BuyDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		buyDAO.setConnection(con);
		buyDAO.insertBuy(buyVO);
		JdbcUtil.commit(con);
		JdbcUtil.close(con);
	}
	
	public void deleteCartOrdered(String[] cart_idArr) throws Exception {
		
		System.out.println("BuyProService의 deleteCartOrdered안으로 들어왔음");
		CartDAO cartDAO = CartDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		cartDAO.setConnection(con);
		for(int i=0; i<cart_idArr.length; i++) {
			cartDAO.deleteCartOrdered(Integer.parseInt(cart_idArr[i]));
		}
		JdbcUtil.commit(con);
		JdbcUtil.close(con);
	}

}
