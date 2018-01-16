<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.itenjoy.vo.BoardVO"%>
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
	BoardVO boardVO = new BoardVO();
	boardVO = (BoardVO)request.getAttribute("boardVO");
	String pageNum = (String)request.getAttribute("pageNum");
	if (pageNum == null) pageNum = "1";
	Object message = request.getAttribute("message");
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	int ref = boardVO.getRef();
	int re_step = boardVO.getRe_step();
	int re_level = boardVO.getRe_level();
%>
<script type="text/javascript">
	
	function createBox() {
		button.innerHTML +="<br>";
		button.innerHTML +="<input type='text' name='passwd' size='10' placeholder='비밀번호 입력' id='passwd'>";
		button.innerHTML +="&nbsp";
		button.innerHTML +="<input type='button' value='삭제' onclick='deleteBoard()'>";
		document.getElementById('deleteBtn').onclick='false';
	}
	
	function deleteBoard() {
		var passwd = document.getElementById('passwd').value;
		var dbPasswd = "<%=(String)boardVO.getPasswd()%>";
		
		if(!passwd) {
			alert('비번입력');
			return false;
		}
		if(passwd != dbPasswd) {
			alert('비번틀림');
			return false;
		}
		writeform.action='deleteBoard.do';
		writeform.submit();
	}
</script>
<br>
<br>
<form name="writeform">
<table align="center" border=1 style="border-collapse: collapse;">
<tr height="30">
	<td align="center" width="150" bgcolor="#EAEAEA">글번호</td>
	<td align="center" width="350"><%=boardVO.getNum()%></td>
	<td align="center" width="150" bgcolor="#EAEAEA">조회수</td>
	<td align="center" width="350"><%=boardVO.getReadcount()%></td>
</tr>

<tr height="30">
	<td align="center" width="125" bgcolor="#EAEAEA">작성자</td>
	<td align="center" width="125"><%=boardVO.getWriter()%></td>
	<td align="center" width="125" bgcolor="#EAEAEA">작성일</td>
	<td align="center" width="125"><%=sdf.format(boardVO.getReg_date())%></td>
</tr>

<tr>
	<td align="center" width="125" bgcolor="#EAEAEA">글제목</td>
	<td align="center" width="375" colspan="3"><%=boardVO.getSubject()%></td>
</tr>

<tr>
	<td align="center" width="125" bgcolor="#EAEAEA">글내용</td>
	<td align="left" width="375" colspan="3"><pre style="white-space: pre-wrap;"> <%=boardVO.getContent()%> </pre></td>
</tr>
</table>
<input type="hidden" name="num" value="<%=boardVO.getNum()%>"></td>
<div class="button" id="button">
	<input type="button" value="글수정" onclick="document.location.href='updateBoardForm.do?num=<%=boardVO.getNum()%>&pageNum=<%=pageNum%>'">
	<input type="button" value="글목록" onclick="document.location.href='getBoardList.do?pageNum=<%=pageNum%>'">
	<input type="button" value="댓글쓰기" onclick="document.location.href='insertBoardForm.do?num=<%=boardVO.getNum()%>&ref=<%=ref%>&re_step=<%=re_step%>&re_level=<%=re_level%>'">
	<input type="button" value="글삭제" onclick='createBox()'id='deleteBtn'>
	
</div>
</form>
</article>
<footer>
	<jsp:include page="../module/bottom.jsp" flush="false" />
</footer>
</div>
</body>
</html>