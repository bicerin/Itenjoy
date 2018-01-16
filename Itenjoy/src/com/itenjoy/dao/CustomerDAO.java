package com.itenjoy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.itenjoy.vo.CustomerVO;

import common.JdbcUtil;

public class CustomerDAO {
	
	Connection con;
	private static CustomerDAO customerDAO;
	
	private CustomerDAO() { }
	
	public static CustomerDAO getInstance() {
		
		if(customerDAO == null) {
			customerDAO = new CustomerDAO();
		}
		
		return customerDAO;
	}
	
	public void setConnection(Connection con) {
		
		this.con = con;
	}
	
	//회원 가입
	public int insertMember(CustomerVO customer) throws Exception {
		PreparedStatement pstmt = null;
		int insertCheck = 0;
		String sql = "";
		try {
			sql = "insert into customer(id,passwd,name,tel,address,email,reg_date) values(?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer.getId());
			pstmt.setString(2, customer.getPasswd());
			pstmt.setString(3, customer.getName());
			pstmt.setString(4, customer.getTel());
			pstmt.setString(5, customer.getAddress());
			pstmt.setString(6, customer.getEmail());
			pstmt.setTimestamp(7, customer.getReg_date());
			
			insertCheck = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return insertCheck;
	}
	
	//회원 1명정보 얻는 메소드
	public CustomerVO getCustomerOne(String id) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		CustomerVO customer = null;
		
		try {
			sql = "select * from customer where id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				customer = new CustomerVO();
				customer.setId(id);
				customer.setPasswd(rs.getString("passwd"));
				customer.setName(rs.getString("name"));
				customer.setTel(rs.getString("tel"));
				customer.setAddress(rs.getString("address"));
				customer.setEmail(rs.getString("email"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return customer;
	}
	
	//사용자 인증처리
	public int userCheck(String id, String passwd) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String dbpasswd = "";
		int x = -1;
		
		try {
			sql = "select passwd from customer where id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbpasswd = rs.getString("passwd");
				if(dbpasswd.equals(passwd))
					x = 1; //인증 성공
				else 
					x = 0; // 비밀번호 틀림
			} else
				x = -1; // 해당아이디 없음
		} catch(Exception ex) {
				ex.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return x;
	}

}
