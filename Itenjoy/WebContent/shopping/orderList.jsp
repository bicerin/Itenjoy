<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.itenjoy.vo.BuyVO" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.NumberFormat" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>컴퓨터 쇼핑몰</title>
<link rel="shortcut icon" href="module/favicon.ico">
</head>
<body>
<div id="wrapper">
<header>
	<jsp:include page="../module/top.jsp" flush="false" />
</header>

<article>
<%
	@SuppressWarnings("unchecked")
	List<BuyVO> buyVOList = (ArrayList<BuyVO>)request.getAttribute("prodcutVOList");
	BuyVO buyVO = new BuyVO();
		
%>
<div align="center">
	<p class="subtitle">주문목록 / 배송조회</p>
	<table class="cartTable">
		<thead>
			<tr>
				<th width="130px">주문일자</th>
				<th width="90px">이미지</th>
				<th width="500px">상품정보</th>
				<th width="70px">수량</th>
				<th width="120px">상품구매금액</th>
				<th width="120px">주문처리상태</th>
				<th width="120px">취소/교환/반품</th>
			</tr>
		</thead>
<%
	for(int i=0; i<buyVOList.size(); i++) {
		buyVO = buyVOList.get(i);
%>		<tbody>
			<tr>
				<td><%=buyVO.getBuy_date().toString().substring(0, 10)%></td>
				<td><a href="product_info.jsp?com_id=<%=buyVO.getCom_id()%>"><img src="imageFile/<%=buyVO.getCom_image()%>" width="80px" height="80px"></a></td>
				<td><%=buyVO.getCom_title()%></td>
				<td><%=buyVO.getBuy_count()%></td>
				<td><%=NumberFormat.getInstance().format(buyVO.getBuy_count() * buyVO.getBuy_price())%>
				<td><%=buyVO.getSanction()%></td>
				<td>
					<input type="button" value="취소" width="50px" height="22px"><br>
					<input type="button" value="교환" width="50px" height="22px"><br>
					<input type="button" value="반품" width="50px" height="22px">
				</td>
			</tr>
		</tbody>
<%
	}
%>	
</table>
</div>	
</article>

<footer>
	<jsp:include page="../module/bottom.jsp" flush="false" />
</footer>
</div>
</body>
</html>