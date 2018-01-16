<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.itenjoy.vo.ProductVO" %>
<%@ page import = "java.text.NumberFormat" %>
<%@ page import = "java.util.*" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>컴퓨터 쇼핑몰</title>
<link rel="shortcut icon" href="../module/favicon.ico">
</head>
<body>
<div id="wrapper">
<header>
	<jsp:include page="../module/top.jsp" flush="false" />
</header>

<article>
<div class="width">
<%
	String keyword = (String)request.getParameter("keyword");
	int listSize = (int)request.getAttribute("listSize");
	@SuppressWarnings("unchecked")
	List<ProductVO> productList = (List<ProductVO>)request.getAttribute("productList");
	ProductVO productVO = new ProductVO();
%>

<div class="section">
<br>
<p class="subtitle">'<%=keyword%>' 검색 결과</p>
<p align="left" class="function"><%=productList.size()%>개의 상품이 검색되었습니다.</p>
<%
	if(productList.size() == 0) {
%>		<p><b>검색결과가 없습니다.</b></p>
		<p><b>정확한 검색어인지 확인하시고 다시 검색해 주세요.</b></p>
<%	}
	for(int i=0; i<productList.size(); i++) {
		productVO = productList.get(i);
%>		<div class="contentBox2">
			<a href="getProductOne.do?com_id=<%=productVO.getCom_id()%>">
			<img src="imageFile/<%=productVO.getCom_image()%>" border="0" width="250" height="250">
			</a>
			<h3><%=productVO.getCom_title()%></h3>
			<h3><%=NumberFormat.getInstance().format(productVO.getCom_price())%>원</h3>
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