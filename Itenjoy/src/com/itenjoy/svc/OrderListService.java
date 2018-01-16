package com.itenjoy.svc;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.itenjoy.dao.BuyDAO;
import com.itenjoy.vo.BuyVO;

import common.JdbcUtil;

public class OrderListService {
	
	public List<BuyVO> getBuyList(String id) throws Exception {
		
		System.out.println("OrderListService 안으로 들어왔음");
		List<BuyVO> prodcutVOList = new ArrayList<BuyVO>();
		BuyDAO buyDAO = BuyDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		buyDAO.setConnection(con);
		prodcutVOList = buyDAO.getBuyList(id);
		
		return prodcutVOList;
	}

}
