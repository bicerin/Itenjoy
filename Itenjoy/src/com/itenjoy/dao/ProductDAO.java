package com.itenjoy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.itenjoy.vo.ProductVO;

import common.JdbcUtil;

public class ProductDAO {
	
	Connection con;
	private static ProductDAO productDAO;
	
	private ProductDAO() { }
	
	public static ProductDAO getInstance() {
		
		if(productDAO == null) {
			productDAO = new ProductDAO();
		}
		
		return productDAO;
	}
	
	public void setConnection(Connection con) {
		
		this.con = con;
	}
	
	//관리자 인증 메소드
//	public int managerCheck(String id, String passwd) throws Exception {
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String dbpasswd = "";
//		String sql = "";
//		int x = -1;
//		
//		try {
//			sql = "select managerPasswd from manager where managerId = ?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, id);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				dbpasswd = rs.getString("managerPasswd");
//				if(dbpasswd.equals(passwd))
//					x = 1; //인증 성공
//				else
//					x = 0; //비밀번호 틀림
//			} else {
//				x = -1; // 아이디 틀림
//			}
//		} catch(Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//		
//		return x;
//	}
	
	//제품등록 메소드
	public void insertComputer(ProductVO com) throws Exception {
		
		PreparedStatement pstmt = null;
		String sql = "";
		
		try {
			sql = "insert into product values(auto_increment.nextval,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, com.getCom_kind());
			pstmt.setString(2, com.getCom_title());
			pstmt.setInt(3, com.getCom_price());
			pstmt.setShort(4, com.getCom_count());
			pstmt.setString(5, com.getManufacture_com());
			pstmt.setString(6, com.getManufacture_date());
			pstmt.setString(7, com.getCom_image());
			pstmt.setString(8, com.getCom_content());
			pstmt.setTimestamp(9, com.getReg_date());
			
			pstmt.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	//전체등록된 제품의 수를 얻어내는 메소드
	public int getComputerCount() throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int x = 0;
		
		try {
			sql = "select count(*) from product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				x = rs.getInt(1);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}
	
	//분류별또는 전체등록된 제품의 정보를 얻어내는 메소드
	public List<ProductVO> getProductList(String com_kind) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductVO> computerList = null;
		String sql = "";
		
		try {
			sql = "select * from product where com_kind = ? order by reg_date desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, com_kind);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				computerList = new ArrayList<ProductVO>();
				do {
					ProductVO computer = new ProductVO();
					
					computer.setCom_id(rs.getInt("com_id"));
					computer.setCom_kind(rs.getString("com_kind"));
					computer.setCom_title(rs.getString("com_title"));
					computer.setCom_price(rs.getInt("com_price"));
					computer.setCom_count(rs.getShort("com_count"));
					computer.setManufacture_com(rs.getString("manufacture_com"));
					computer.setManufacture_date(rs.getString("manufacture_date"));
					computer.setCom_image(rs.getString("com_image"));
					computer.setReg_date(rs.getTimestamp("reg_date"));
					
					computerList.add(computer);
				} while(rs.next());
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return computerList;
	}
	
	//메인페이지에 제품별로 4개씩 제품을 나타내기위해 4개의 제품을 얻어내는 메소드
	public List<ProductVO> getIntroList(String com_kind) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductVO> computerList = new ArrayList<ProductVO>();
		String sql = "";
		
		try {
			sql = "select * from product where com_kind = ? order by reg_date desc";
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, com_kind);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ProductVO computer = new ProductVO();
				computer.setCom_id(rs.getInt("com_id"));
				computer.setCom_kind(rs.getString("com_kind"));
				computer.setCom_title(rs.getString("com_title"));
				computer.setCom_price(rs.getInt("com_price"));
				computer.setCom_count(rs.getShort("com_count"));
				computer.setManufacture_com(rs.getString("manufacture_com"));
				computer.setManufacture_date(rs.getString("manufacture_date"));
				computer.setCom_image(rs.getString("com_image"));
				computer.setReg_date(rs.getTimestamp("reg_date"));
				
				computerList.add(computer);
				
				if(rs.getRow() == 4)
					rs.afterLast();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return computerList;
	}
	
	public List<ProductVO> getProductList(String com_kind, String sort) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductVO> computerList = new ArrayList<ProductVO>();
		ProductVO computer = null;
		
		try {
			String sql = "";
			String sql1 = "select * from product where com_kind=? order by manufacture_date desc";
			String sql2 = "select * from product where com_kind=? order by com_title";
			String sql3 = "select * from product where com_kind=? order by com_price asc";
			String sql4 = "select * from product where com_kind=? order by com_price desc";
			
			switch(sort) {
			case "date": sql = sql1; break;
			case "title": sql = sql2; break;
			case "priceH": sql = sql3; break;
			case "priceL": sql = sql4; break;
			}
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, com_kind);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				computer = new ProductVO();
				
				computer.setCom_id(rs.getInt("com_id"));
				computer.setCom_kind(rs.getString("com_kind"));
				computer.setCom_title(rs.getString("com_title"));
				computer.setCom_price(rs.getInt("com_price"));
				computer.setCom_count(rs.getShort("com_count"));
				computer.setManufacture_com(rs.getString("manufacture_com"));
				computer.setManufacture_date(rs.getString("manufacture_date"));
				computer.setCom_image(rs.getString("com_image"));
				computer.setReg_date(rs.getTimestamp("reg_date"));
				
				computerList.add(computer);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return computerList;
	}
	
	//com_id에 해당하는 제품의 정보를 얻어내는 메소드
	public ProductVO getProductOne(int com_id) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ProductVO computer = null;
		
		try {
			sql = "select * from product where Com_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, com_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				computer = new ProductVO();
				
				computer.setCom_id(com_id);
				computer.setCom_kind(rs.getString("com_kind"));
				computer.setCom_title(rs.getString("com_title"));
				computer.setCom_price(rs.getInt("com_price"));
				computer.setCom_count(rs.getShort("com_count"));
				computer.setManufacture_com(rs.getString("manufacture_com"));
				computer.setManufacture_date(rs.getString("manufacture_date"));
				computer.setCom_image(rs.getString("com_image"));
				computer.setCom_content(rs.getString("com_content"));
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return computer;
	}
	
	//등록된 책의 정보를 수정시 사용하는 메소드
	public void updateComputer(ProductVO computer, int comId) throws Exception {
		
		PreparedStatement pstmt = null;
		String sql = "";
		
		try {
			sql = "update product set com_kind=?, com_title=?, com_price=?,"
					+ " com_count=?, manufacture_com=?, manufacture_date=?,"
					+ " com_image=?, com_content=? where com_id=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, computer.getCom_kind());
			pstmt.setString(2, computer.getCom_title());
			pstmt.setInt(3, computer.getCom_price());
			pstmt.setShort(4, computer.getCom_count());
			pstmt.setString(5, computer.getManufacture_com());
			pstmt.setString(6, computer.getManufacture_date());
			pstmt.setString(7, computer.getCom_image());
			pstmt.setString(8, computer.getCom_content());
			pstmt.setInt(9, comId);
			
			pstmt.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	//comId에 해당하는 제품의 정보를 삭제시 사용하는 메소드
	public void deleteComputer(int comId) throws Exception {
		
		PreparedStatement pstmt = null;
		String sql = "";
		
		try {
			sql = "delete from product where com_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comId);
			pstmt.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	// 검색결과 보여주는 메소드 <--- top.jsp에서 사용함
	public List<ProductVO> seachProduct(String keyword) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ProductVO search = null;
		List<ProductVO> lists = null;
		
		try {
			sql = "select * from product where com_title like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			lists = new ArrayList<ProductVO>();
			
			while(rs.next()) {
				search = new ProductVO();
				search.setCom_id(rs.getInt("com_id"));
				search.setCom_title(rs.getString("com_title"));
				search.setCom_price(rs.getInt("com_price"));
				search.setCom_image(rs.getString("com_image"));
				
				lists.add(search);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return lists;
	}

}
