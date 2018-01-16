<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.itenjoy.vo.ProductVO" %>
<%@ page import="java.util.*"%>
<%@ page import = "java.text.NumberFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>컴퓨터 쇼핑몰</title>
<link href="etc/style.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="module/favicon.ico">
</head>
<body>
<div id="wrapper">
<header>
	<jsp:include page="../module/top.jsp" flush="false" />
</header>

<article>
<div class="width">
<% 
	@SuppressWarnings("unchecked")
	List<ProductVO> productList = (ArrayList<ProductVO>)request.getAttribute("productList");
	String com_kindName = (String)request.getAttribute("com_kindName");
%>
<br>
<p class="subtitle"><%=com_kindName%></p>
<div class="function">
	<span>등록 제품: <%=productList.size()%>개</span>
	<ul class="sortBy">
		<li><a href="getProductList.do?com_kind=<%=productList.get(0).getCom_kind()%>&sort=date">신상품 |  </a></li>
		<li><a href="getProductList.do?com_kind=<%=productList.get(0).getCom_kind()%>&sort=title">상품명 |  </a></li>
		<li><a href="getProductList.do?com_kind=<%=productList.get(0).getCom_kind()%>&sort=priceH">낮은 가격 |  </a></li>
		<li><a href="getProductList.do?com_kind=<%=productList.get(0).getCom_kind()%>&sort=priceL">높은 가격</a></li>
	</ul>
</div>

<div class="section">
<%
	for(int i=0; i<productList.size(); i++) {
%>		<div class="contentBox2">
			<a href="getProductOne.do?com_id=<%=productList.get(i).getCom_id()%>">
				<img src="imageFile/<%=productList.get(i).getCom_image()%>" border="0" width="250" height="250">
			</a>
			<h3><%=productList.get(i).getCom_title()%></h3>
			<h3><%=NumberFormat.getInstance().format(productList.get(i).getCom_price())%>원</h3>
		</div>
<%	}
%>	</div>
</div>
</article>

<footer>
	<jsp:include page="../module/bottom.jsp" flush="false" />
</footer>
</div>
</body>
</html>