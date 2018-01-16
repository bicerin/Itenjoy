<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "com.itenjoy.vo.CartVO" %>
<%@ page import = "com.itenjoy.dao.CartDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>컴퓨터 쇼핑몰</title>
<link href="etc/style.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="favicon.ico">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=0.3">
<script type="text/javascript">
	function keywordCheck() {
		
		if(!search.keyword.value) {
			swal({
				title: "검색어를 입력해주세요",
				icon: "warning"
				}).then(function() {
				search.keyword.focus();
				});
			return false;
		}
	
		search.action = "search.do";
		search.submit();
	}
</script>
</head>
<body>
<div class="header">
<div class="width">
<%
String keyword = request.getParameter("keyword");
String id = (String)session.getAttribute("id");
try {
	if(id == null) {
%>		<div class="topNavi">
			<ul>
				<li><a href="login.do" class="hover">로그인</a></li>
				<li>|</li>
				<li><a href="joinForm.do" class="hover">회원가입</a></li>
				<li>|</li>
				<li><a href="login.do" class="hover">장바구니</a></li>
				<li>|</li>
				<li><a href="login.do" class="hover">주문조회</a></li>
				<li>|</li>
				<li><a href="showPDF.do" target="_blank" class="hover">마이페이지(이력서예정)</a></li>
				<li>|</li>
				<li><a href="getBoardList.do" class="hover">  게시판</a></li>
			</ul>
		</div>
<%  } else if(id.equals("admin")) {
%>		<div class="topNavi">
			<ul>
				<li><a href="../manager/productProcess/computerRegisterForm.jsp" class="hover">상품등록</a></li>
				<li>|</li>
				<li><a href="../manager/productProcess/computerList.jsp?com_kind=all" class="hover">상품전체 조회(수정/삭제)</a></li>
				<li>|</li>
				<li><a href="../manager/orderedProduct/orderedList.jsp" class="hover">상품전체 구매목록 조회</a></li>
				<li>|</li>
				<li><a href="logout.jsp" class="hover">로그아웃</a></li>
			</ul>
		</div>		
<%	} else {
		int cartCount = (int)session.getAttribute("cartListSize");
%>		<div class="topNavi">
			<ul>
				<li><a href="getCartList.do" class="hover">장바구니(<%=cartCount%>)</a></li>
				<li>|</li>
				<li><a href="orderList.do" class="hover">주문조회</a></li>
				<li>|</li>
				<li><a href="showPDF.do" target="_blank" class="hover">마이페이지(이력서예정)</a></li>
				<li>|</li>
				<li><a href="getBoardList.do" class="hover">게시판</a></li>
				<li>|</li>
				<li><a href="logout.do" class="hover">로그아웃</a></li>
			</ul>
		</div>
<%	}
%>
	<div>
	<div class="logoAndFinder">
		<div class="logo">
			<a href="shopMain.do"><img src="module/logo.jpg"></a>
		</div>
		
		<div class="finder">
		<form action="search.do" method="post" name="search">
			<div class="finder1">
				<input type="text" placeholder="검색" name="keyword" <%if(keyword == null) { %>value=""<%}else {%> value="<%=keyword%>" <% } %>>
			</div>
			
			<div class="finder2">
				<button type="button" class="btn_image" onclick="keywordCheck()"><img src="module/search_icon.png" width="30px" height="30px"></button>
			</div>
		</form>
		</div>
	</div>
	</div>
</div>
	<div class="menuBar">
		<ul>
			<li><a href="shopMain.do" class="hover">전체상품</a></li>
			<li><a href="getProductList.do?com_kind=100" class="hover">CPU</a></li>
			<li><a href="getProductList.do?com_kind=200" class="hover">그래픽카드</a></li>
			<li><a href="getProductList.do?com_kind=300" class="hover">RAM</a></li>
			<li><a href="getProductList.do?com_kind=400" class="hover">메인보드</a></li>
			<li><a href="getProductList.do?com_kind=500" class="hover">HDD</a></li>
			<li><a href="getProductList.do?com_kind=600" class="hover">SSD</a></li>
			<li><a href="getProductList.do?com_kind=700" class="hover">케이스</a></li>
			<li><a href="getProductList.do?com_kind=800" class="hover">파워</a></li>
		</ul>
	</div>
<%
} catch(Exception e) {
	e.printStackTrace();
}
%>
</div>
</body>
</html>