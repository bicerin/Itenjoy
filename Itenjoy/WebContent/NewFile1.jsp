<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.itenjoy.dao.ProductDAO"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.DataSource" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
	function success() {
		swal("Good job!", "You clicked the button!", "success");
	}
	function warning() {
		swal({title: "아이디를 입력해주세요", icon: "warning"});
	}
	function error() {
		swal("Good job!", "You clicked the button!", "error");
	}
	function info() {
		swal("Good job!", "You clicked the button!", "info");
	}
	function normal() {
		swal({
			title: "로그인이 필요한 서비스입니다",
			text: "에베베베",
			icon: "warning"
			}).then(function() {
			window.location.href = history.go(0);
			});
	}
</script>
</head>
<body>

<%
	int stock = 10;
	int count = 100;
	if(stock < count) {
%>		<p>재고부족입니다</p>
<%} %>
	<input type="button" value="경고" onclick='warning()'>
	<input type="button" value="에러" onclick='error()'>
	<input type="button" value="성공" onclick='success()'>
	<input type="button" value="정보" onclick='info()'>
	<input type="button" value="일반" onclick='normal()'>
</body>
</html>