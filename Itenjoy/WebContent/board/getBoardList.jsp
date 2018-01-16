<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.itenjoy.vo.BoardVO"%>
<%@ page import = "com.itenjoy.vo.PagingVO"%>
<%@ page import = "java.util.*"%>
<%@ page import ="java.sql.Timestamp"%>
<%@ page import = "java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>컴퓨터 쇼핑몰</title>
<link href="etc/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrapper">
<header>
	<jsp:include page="../module/top.jsp" flush="false" />
</header>

<article>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	PagingVO pagingVO = new PagingVO();
	pagingVO = (PagingVO)request.getAttribute("pagingVO");
	int number = pagingVO.getNumber();
	String pageNum = (String)request.getAttribute("pageNum");
	@SuppressWarnings("unchecked")
	List<BoardVO> boardVOList = (ArrayList<BoardVO>)request.getAttribute("boardVOList");
%>
	<p class="subtitle">문의게시판</p>

	<table class="board">
	<tr height="30" bgcolor="#EAEAEA">
		<td align="center" width="50"> 번 호 </td>
		<td align="center" width="250">제 목 </td>
		<td align="center" width="50">작성자 </td>
		<td align="center" width="150">작성일 </td>
		<td align="center" width="50">조 회</td>
		<td align="center" width="100">IP </td>
	</tr>
<%
	for(int i=0; i<boardVOList.size(); i++) {
		BoardVO article = boardVOList.get(i);
%>	
	<tr height="30">
		<td> <%=number--%> </td>
		
		<td align="left">
<%			int wid = 0;
			if(article.getRe_level() > 0) {
				wid = 5 * (article.getRe_level());
%>				<img src="module/level.png" width="<%=wid%>" height="16">
				<img src="module/reply.png">
<%			} else {
%>				<img src="module/level.png" width="<%=wid%>" height="16">
<% 			}
%>			<a href="getBoardOne.do?num=<%=article.getNum()%>&pageNum=<%=pageNum%>"> <%=article.getSubject()%> </a>
<%			if(article.getReadcount() >= 20) {
%>				<img src="module/hot.png" border="0" height="16">
<% 			}
%> 		</td>
		<td align="left"><a href="mailto:<%=article.getEmail()%>"> <%=article.getWriter()%> </a></td>
		<td> <%=sdf.format(article.getReg_date())%> </td>
		<td> <%=article.getReadcount()%> </td>
		<td> <%=article.getIp()%> </td>
	</tr>
<% 	}
%>	</table> 

<!-- 버튼 관련 -->
<div class="botton">
<a href="insertBoardForm.do"><button name="write">글쓰기</button></a>
</div>

<!-- 페이징 처리 관련 -->
<%
	int pageCount = pagingVO.getPageCount();
	int startPage = 1;
	int currentPage = Integer.parseInt(pageNum);
	if(currentPage % 10 != 0) {
		startPage = (int)(currentPage / 10) * 10 + 1;
	}
	else
		startPage = ((int)(currentPage / 10) - 1) * 10 + 1;
	
	int PAGEBLOCK = 10;
	int endPage = startPage + PAGEBLOCK - 1;
	if(endPage > pageCount)
		endPage = pageCount;

	if(startPage > 10) {
%>		<a href="getBoardList.do?pageNum=<%=currentPage - 10%>">[이전]</a>
<%	}
  
	for(int i=startPage; i<=endPage; i++) {
		if(currentPage == i) {
%>			<span class="accent"><a href="getBoardList.do?pageNum=<%=i%>"> <%=i%> </a></span>
<%		} else {
%>			<span class="normal"><a href="getBoardList.do?pageNum=<%=i%>"> <%=i%> </a></span>
<%		}
	}
	
	if(endPage < pageCount) {
%>		<a href="getBoardList.do?pageNum=<%=startPage + 10%>"> [다음] </a>
<%	}
%>
</article>


<footer>
	<jsp:include page="../module/bottom.jsp" flush="false" />
</footer>
</div>
</body>
</html>
