<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.itenjoy.vo.BoardVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>컴퓨터 쇼핑몰</title>
<link href="etc/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="etc/boardScript.js"></script>
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
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");

%>
<script type="text/javascript">

	function UpdateBoard() {
		var dbPasswd = "<%=boardVO.getPasswd()%>";
		var passwd = document.getElementById('passwd').value;
		
		if(!writeform.writer.value){
		  alert("작성자를 입력하십시오.");
		  writeform.writer.focus();
		  return false;
		}
		if(!writeform.subject.value){
		  alert("제목을 입력하십시오.");
		  writeform.subject.focus();
		  return false;
		}
		if(!writeform.content.value){
		  alert("내용을 입력하십시오.");
		  writeform.content.focus();
		  return false;
		}
	    if(!writeform.passwd.value){
		  alert(" 비밀번호를 입력하십시오.");
		  writeform.passwd.focus();
		  return false;
		}
		if(dbPasswd != passwd) {
			alert('비번틀림');
			return false;
		}
		
		writeform.submit();
	}
</script>
<p>게시글 수정</p>
<br>
<form method="post" name="writeform" action="updateBoardPro.do?pageNum=<%=pageNum%>&num=<%=num%>" onsubmit="return writeSave()">
<table align="center" border=1 style="border-collapse: collapse;">
<tr>
	<td width="70" bgcolor="#EAEAEA" align="center">이름</td>
	<td width="600" align="left">
	<input type="text" size="20" maxlength="10" name="writer" value="<%=boardVO.getWriter()%>">
	<input type="hidden" name="num" value="<%=boardVO.getNum()%>"></td>
</tr>

<tr>
	<td width="70" bgcolor="#EAEAEA" align="center">제목</td>
	<td width="600" align="left">
	<input type="text" size="77" maxlength="50" name="subject" value="<%=boardVO.getSubject()%>"></td>
</tr>

<tr>
	<td width="70" bgcolor="#EAEAEA" align="center">Email</td>
	<td width="600" align="left">
	<input type="text" size="77" maxlength="30" name="email" value="<%=boardVO.getEmail()%>"></td>
</tr>

<tr>
	<td width="70" bgcolor="#EAEAEA" align="center">내용</td>
	<td width="600" align="left">
	<textarea name="content" rows="30" cols="78"><%=boardVO.getContent()%></textarea></td>
</tr>

<tr>
	<td width="70" bgcolor="#EAEAEA" align="center">비밀번호</td>
		<td width="600" align="left">
		<input type="password" size="20" maxlength="12" name="passwd" id="passwd"></td>
</tr>

<tr>
	<td colspan="2" bgcolor="#EAEAEA" align="center">
	<input type="button" value="글수정" onclick='UpdateBoard()'>
	<input type="reset" value="다시작성">
	<input type="button" value="목록보기" onclick="document.location.href='getBoardList.do?pageNum=<%=pageNum%>'"></td>
</tr>
</table>
</form>
</article>
<footer>
	<jsp:include page="../module/bottom.jsp" flush="false" />
</footer>
</div>
</body>
</html>