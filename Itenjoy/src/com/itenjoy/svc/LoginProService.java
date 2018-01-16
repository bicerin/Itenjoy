package com.itenjoy.svc;

import java.sql.Connection;

import com.itenjoy.dao.CustomerDAO;

import common.JdbcUtil;

public class LoginProService {
	
	public int userCheck(String id, String passwd) throws Exception {
		
		System.out.println("LoginProService 안으로 들어왔음");
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		customerDAO.setConnection(con);
		int check = customerDAO.userCheck(id, passwd);
		JdbcUtil.close(con);
		return check;
	}

}
