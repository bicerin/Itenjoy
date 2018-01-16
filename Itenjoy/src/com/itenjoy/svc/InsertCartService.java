package com.itenjoy.svc;

import java.sql.Connection;

import com.itenjoy.dao.CartDAO;
import com.itenjoy.vo.CartVO;

import common.JdbcUtil;

public class InsertCartService {
	
	public void insertCart(CartVO cartvo) throws Exception {
		
		CartDAO cartDAO = CartDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		cartDAO.setConnection(con);
		int insertCheck = cartDAO.insertCart(cartvo);
		
		if (insertCheck > 0) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
	}

}
