package com.itenjoy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.itenjoy.vo.BoardVO;

import common.JdbcUtil;

public class BoardDAO {
	
	Connection con;
	private static BoardDAO boardDAO;
	
	private BoardDAO() { }
	
	public static BoardDAO getInstance() {
		
		if(boardDAO == null) {
			boardDAO = new BoardDAO();
		}
		
		return boardDAO;
	}
	
	public void setConnection(Connection con) {
		
		this.con = con;
	}
	
	//board 테이블에 글을 추가(insert문)
	public void insertArticle(BoardVO article) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int num = article.getNum();
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
		int number = 0;
		String sql = "";
		
		try {
			sql = "select max(num) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				number = rs.getInt(1) + 1;
			} else {
				number = 1;
			}
			
			if(num != 0) {
				sql = "update board set re_step = re_step + 1 where ref = ? and re_step > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				re_step = re_step + 1;
				re_level = re_level + 1;
			} else {
				ref = number;
				re_step = 0;
				re_level = 0;
			}
			
			sql = "insert into board(num,writer, email, subject, passwd, reg_date,"
				+ "ref, re_step, re_level, content, ip) values(board_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getEmail());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPasswd());
			pstmt.setTimestamp(5, article.getReg_date());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, re_step);
			pstmt.setInt(8, re_level);
			pstmt.setString(9, article.getContent());
			pstmt.setString(10, article.getIp());
			
			pstmt.executeUpdate();
		} catch(Exception ex ) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//board 테이블에 저장된 전체 글의 수를 얻어냄(select문)
	public int getArticleCount() throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		String sql = "";
		
		try {
			sql = "select count(*) from board";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}
	
	//글의 목록(복수 개의 글)을 가져옴(select문)
	public List<BoardVO> getArticleList(int start, int end) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> articleList = null;
		String sql = "";
		try {
			sql = "SELECT * FROM (SELECT A.*, ROW_NUMBER() OVER(ORDER BY ref desc, re_step) AS rnum FROM board A) WHERE rnum BETWEEN ? AND ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				articleList = new ArrayList<BoardVO>();
				do {
					BoardVO article = new BoardVO();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					article.setReg_date(rs.getTimestamp("reg_date"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setRe_step(rs.getInt("re_step"));
					article.setRe_level(rs.getInt("re_level"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					
					articleList.add(article);
				} while(rs.next());
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return articleList;
	}
	
	//글의 내용을 보기(1개의 글)(select문)
	public BoardVO getArticle(int num) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO article = null;
		String sql = "";
		
		try {
			sql = "update board set readcount = readcount + 1 where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			sql = "select * from board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new BoardVO();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPasswd(rs.getString("passwd"));
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return article;
	}
	
	//글수정 폼에서 사용할 글의 내용(1개의 글)(select문)
	public BoardVO updateGetArticle(int num) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO article = null;
		String sql = "";
		
		try {
			sql = "select * from board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new BoardVO();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPasswd(rs.getString("passwd"));
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return article;
	}
	
	//글수정 처리에서 사용(update문)
	public int updateArticle(BoardVO article) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		String sql = "";
		int x = -1;
		
		try {
			sql = "select passwd from board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbpasswd = rs.getString("passwd");
				if(dbpasswd.equals(article.getPasswd())) {
					sql = "update board set writer=?, email=?, subject=?, passwd=?, content=? where num = ?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getEmail());
					pstmt.setString(3, article.getSubject());
					pstmt.setString(4, article.getPasswd());
					pstmt.setString(5, article.getContent());
					pstmt.setInt(6, article.getNum());
					pstmt.executeUpdate();
					x = 1;
				} else {
					x = 0;
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}
	
	//글삭제 처리시 사용(delete문)
	public int deleteArticle(int num, String passwd) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		String sql = "";
		int x = -1;
		
		try {
			sql = "select passwd from board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbpasswd = rs.getString("passwd");
				if(dbpasswd.equals(passwd)) {
					pstmt = con.prepareStatement("delete from board where num = ?");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					x = 1; //글삭제 성공
				} else {
					x = 0; //비밀번호 틀림
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}

}
