package com.itenjoy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.itenjoy.vo.CartVO;

import common.JdbcUtil;

public class CartDAO {
	
	Connection con;
	private static CartDAO cartDAO;
	
	private CartDAO() { }
	
	public static CartDAO getInstance() {
		
		if(cartDAO == null) {
			cartDAO = new CartDAO();
		}
		
		return cartDAO;
	}
	
	public void setConnection(Connection con) {
		
		this.con = con;
	}
	
	//"Add to Cart" 클릭시 실행되는 메소드로 cart 테이블에 새로운 레코드를 추가
		public int insertCart(CartVO cart) throws Exception {
			PreparedStatement pstmt = null;
			String sql = "";
			int insertCheck = 0;
			
			try {
				sql = "insert into cart values(auto_increment_cart.nextval,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, cart.getBuyer());
				pstmt.setInt(2, cart.getCom_id());
				pstmt.setString(3, cart.getCom_title());
				pstmt.setInt(4, cart.getBuy_price());
				pstmt.setByte(5, cart.getBuy_count());
				pstmt.setString(6, cart.getCom_image());
				insertCheck = pstmt.executeUpdate();
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
			}
			
			return insertCheck;
		}
		
		//user의 장바구니 목록을 보여주는 메소드
		public List<CartVO> getCart(String id) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "";
			CartVO cart = null;
			List<CartVO> lists = null;
			
			try {
				sql = "select * from cart where buyer = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				lists = new ArrayList<CartVO>();
				
				while(rs.next()) {
					cart = new CartVO();
					cart.setCart_id(rs.getInt("cart_id"));
					cart.setCom_id(rs.getInt("com_id"));
					cart.setCom_title(rs.getString("com_title"));
					cart.setBuy_price(rs.getInt("buy_price"));
					cart.setBuy_count(rs.getByte("buy_count"));
					cart.setCom_image(rs.getString("com_image"));
					
					lists.add(cart);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
				JdbcUtil.close(rs);
			}
			
			return lists;
		}
		
		//장바구니에서 "변경" 버튼 클릭시 실행되는 메소드
		public int updateCart(int cart_id, byte buy_count) throws Exception {
			PreparedStatement pstmt = null;
			String sql = "";
			int updateCheck = 0;
			
			try {
				sql = "update cart set buy_count = ? where cart_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setByte(1, buy_count);
				pstmt.setInt(2, cart_id);
				updateCheck = pstmt.executeUpdate();
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
			}
			
			return updateCheck;
		}
		
		// cart_id에 해당하는 로우를 cart테이블에서 삭제하는 메소드
		public int deleteCart(int cart_id) throws Exception {
			PreparedStatement pstmt = null;
			int deleteCheck = 0;
			String sql = "";
			
			try {
				sql = "delete from cart where cart_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cart_id);
				deleteCheck = pstmt.executeUpdate();
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
			}
			
			return deleteCheck;
		}
		
		// cart_id에 해당하는 com_id 리턴하는 메소드
		public int getCartCom_id(int cart_id) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int com_id = 0;
			String sql = "";
			
			try {
				sql = "select com_id from cart where cart_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cart_id);
				rs = pstmt.executeQuery();
				
				if(rs.next())
					com_id = rs.getInt("com_id");
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
				JdbcUtil.close(rs);
			}
			
			return com_id;
		}

		// cart_id에 해당하는 buy_count 리턴하는 메소드
		public Byte getCartBuy_count(int cart_id) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Byte buy_count = 0;
			String sql = "";
			
			try {
				sql = "select buy_count from cart where cart_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cart_id);
				rs =  pstmt.executeQuery();
				
				if(rs.next())
					buy_count = rs.getByte("buy_count");
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
				JdbcUtil.close(rs);
			}
			
			return buy_count;
		}
		
		public void deleteCartOrdered(int cart_id) throws Exception {
			PreparedStatement pstmt = null;
			String sql = "";
			
			try {
				sql = "delete from cart where cart_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cart_id);
				pstmt.executeUpdate();
				
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
			}
		}
		
/*		//"장바구니 비우기" 클릭시 id에 해당하는 모든 레코드를 삭제하는 메소드
		public void deleteAll(String id) throws Exception {
			PreparedStatement pstmt = null;
			String sql = "";
			
			try {
				sql = "delete from cart where buyer = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.executeUpdate();
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
			}
		}*/
		
		//cart_id에 해당하는 cart테이블의 목록을 얻어내는 메소드
/*		public List<CartVO> getCartList(String[] cart_id) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "";
			CartVO cart = null;
			List<CartVO> lists = null;
			
			try {
				sql = "select * from Cart where cart_id = ?";
				lists = new ArrayList<CartVO>();
				
				for(int i=0; i<cart_id.length; i++) {
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(cart_id[i]));
					rs = pstmt.executeQuery();
					cart = new CartVO();
					
					if(rs.next()) {
						cart.setCart_id(rs.getInt("cart_id"));
						cart.setBuyer(rs.getString("buyer"));
						cart.setCom_id(rs.getInt("com_id"));
						cart.setCom_title(rs.getString("com_title"));
						cart.setBuy_price(rs.getInt("buy_price"));
						cart.setBuy_count(rs.getByte("buy_count"));
						cart.setCom_image(rs.getString("com_image"));	
						lists.add(cart);
					}
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
				JdbcUtil.close(rs);
			}
			
			return lists;
		}*/

}
