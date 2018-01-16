package com.itenjoy.svc;

import java.sql.Connection;

import com.itenjoy.dao.CartDAO;
import com.itenjoy.dao.CustomerDAO;
import com.itenjoy.dao.ProductDAO;
import com.itenjoy.vo.CustomerVO;
import com.itenjoy.vo.ProductVO;

import common.JdbcUtil;

public class BuyFormService {
	
	public ProductVO getProductOne(int com_id) throws Exception {
		
		System.out.println("BuyFormService의 getProductOne 안으로 들어왔음");
		ProductVO productVO = new ProductVO();
		ProductDAO productDAO = ProductDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		productDAO.setConnection(con);
		productVO = productDAO.getProductOne(com_id);
		JdbcUtil.close(con);
		
		return productVO;
	}
	
	public CustomerVO getCustomerOne(String id) throws Exception {
		
		System.out.println("BuyFormService의 getCustomerOne 안으로 들어왔음");
		CustomerVO customerVO = new CustomerVO();
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		customerDAO.setConnection(con);
		customerVO = customerDAO.getCustomerOne(id);
		JdbcUtil.close(con);
		
		return customerVO;
	}
	
	public int getCartCom_id(int cart_id) throws Exception {
		
		System.out.println("BuyFormService의 getCartCom_id 안으로 들어왔음");
		int com_id = 0;
		CartDAO cartDAO = CartDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		cartDAO.setConnection(con);
		com_id = cartDAO.getCartCom_id(cart_id);
		JdbcUtil.close(con);
		
		return com_id;
	}
	
	public Byte getCartBuy_count(int cart_id) throws Exception {
		
		System.out.println("BuyFormService의 getCartBuy_count 안으로 들어왔음");
		Byte buy_count = 0;
		CartDAO cartDAO = CartDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		cartDAO.setConnection(con);
		buy_count = cartDAO.getCartBuy_count(cart_id);
		JdbcUtil.close(con);
		
		return buy_count;
	}

}
