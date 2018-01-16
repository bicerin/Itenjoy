package com.itenjoy.svc;

import java.sql.Connection;

import com.itenjoy.dao.CustomerDAO;
import com.itenjoy.vo.CustomerVO;

import common.JdbcUtil;

public class InsertMemberService {

	public boolean insertMember(CustomerVO customer) throws Exception {
		
		System.out.println("InsertMemberService 안으로 들어왔음");
		boolean isWriteSuccess = false;
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		customerDAO.setConnection(con);
		int insertCheck = customerDAO.insertMember(customer);
		
		if (insertCheck > 0) {
			JdbcUtil.commit(con);
			isWriteSuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		return isWriteSuccess;
		
	}

}
