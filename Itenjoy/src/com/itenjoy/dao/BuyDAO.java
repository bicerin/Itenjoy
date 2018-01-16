package com.itenjoy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.itenjoy.vo.BuyVO;

import common.JdbcUtil;

public class BuyDAO {
	
	Connection con;
	private static BuyDAO buyDAO;
	
	private BuyDAO() { }
	
	public static BuyDAO getInstance() {
		
		if(buyDAO == null) {
			buyDAO = new BuyDAO();
		}
		
		return buyDAO;
	}
	
	public void setConnection(Connection con) {
		
		this.con = con;
	}
	
	// "구매"버튼을 누르면 구매 테이블인 buy에 구매목록 등록
	public void insertBuy(BuyVO buyVO) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		short nowCount;
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		try {
			sql = "insert into buy (buy_id, buyer, com_id, com_title, buy_price, buy_count, "
							   	  + "com_image, buy_date, deliveryName, deliveryTel, deliveryAddress, memo) "
								  + "values (auto_increment_buy.nextval,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, buyVO.getBuyer());
			pstmt.setInt(2, buyVO.getCom_id());
			pstmt.setString(3, buyVO.getCom_title());
			pstmt.setInt(4, buyVO.getBuy_price());
			pstmt.setByte(5, buyVO.getBuy_count());
			pstmt.setString(6, buyVO.getCom_image());
			pstmt.setTimestamp(7, now);
			pstmt.setString(8, buyVO.getDeliveryName());
			pstmt.setString(9, buyVO.getDeliveryTel());
			pstmt.setString(10, buyVO.getDeliveryAddress());
			pstmt.setString(11, buyVO.getMemo());
			pstmt.executeUpdate();
			
			//상품이 구매되었으므로 computer 테이블의 상품수량을 재조정함
			sql = "select com_count from product where com_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, buyVO.getCom_id());
			rs = pstmt.executeQuery();
			rs.next();
			
			nowCount = (short)(rs.getInt(1) - buyVO.getBuy_count());
			
			sql = "update product set com_count = ? where com_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setShort(1, nowCount);
			pstmt.setInt(2, buyVO.getCom_id());
			pstmt.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	//id에 해당하는 buy테이블의 구매목록을 얻어내는 메소드 <---orderList.jsp에서 사용
	public List<BuyVO> getBuyList(String id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		BuyVO buy = null;
		List<BuyVO> lists = null;
		
		try {
			sql = "select * from buy where buyer = ? order by buy_date desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			lists = new ArrayList<BuyVO>();
			
			while(rs.next()) {
				buy = new BuyVO();
				buy.setBuy_id(rs.getLong("buy_id"));
				buy.setBuyer(rs.getString("buyer"));
				buy.setCom_id(rs.getInt("com_id"));
				buy.setCom_title(rs.getString("com_title"));
				buy.setBuy_price(rs.getInt("buy_price"));
				buy.setBuy_count(rs.getByte("buy_count"));
				buy.setCom_image(rs.getString("com_image"));
				buy.setBuy_date(rs.getTimestamp("buy_date"));
				buy.setDeliveryName(rs.getString("deliveryName"));
				buy.setDeliveryTel(rs.getString("deliveryTel"));
				buy.setDeliveryAddress(rs.getString("DeliveryAddress"));
				buy.setMemo(rs.getString("memo"));
				buy.setSanction(rs.getString("sanction"));
				lists.add(buy);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return lists;
	}
	
	//buy테이블의 전체 목록을 얻어내는 메소드 <---orderedList.jsp에서 사용
/*	public List<BuyDataBean> getBuyList() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		BuyDataBean buy = null;
		List<BuyDataBean> lists = null;
		
		try {
			conn = getConnection();
			sql = "select * from buy";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			lists = new ArrayList<BuyDataBean>();
			
			while(rs.next()) {
				buy = new BuyDataBean();
				buy.setBuy_id(rs.getLong("buy_id"));
				buy.setBuyer(rs.getString("buyer"));
				buy.setCom_id(rs.getInt("com_id"));
				buy.setCom_title(rs.getString("com_title"));
				buy.setBuy_price(rs.getInt("buy_price"));
				buy.setBuy_count(rs.getByte("buy_count"));
				buy.setCom_image(rs.getString("com_image"));
				buy.setBuy_date(rs.getTimestamp("buy_date"));
				buy.setDeliveryName(rs.getString("deliveryName"));
				buy.setDeliveryTel(rs.getString("deliveryTel"));
				buy.setDeliveryAddress(rs.getString("DeliveryAddress"));
				buy.setMemo(rs.getString("memo"));
				buy.setSanction(rs.getString("sanction"));
				lists.add(buy);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException ex) { }
			if(conn != null) try { conn.close(); } catch(SQLException ex) { }
			if(rs != null) try { rs.close(); } catch(SQLException ex) { }
		}
		
		return lists;
	}*/
	
}
