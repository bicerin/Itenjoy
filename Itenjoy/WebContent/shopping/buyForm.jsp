<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.itenjoy.vo.CustomerVO" %>
<%@ page import = "com.itenjoy.vo.ProductVO" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.text.NumberFormat" %>
<%@ page import="java.util.*, java.text.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>컴퓨터 쇼핑몰</title>
<link href="etc/style.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="../module/favicon.ico">
</head>
<body>
<div id="wrapper">
<header>
	<jsp:include page="../module/top.jsp" flush="false" />
</header>

<article>
<%
	@SuppressWarnings("unchecked")
	List<ProductVO> productVOList = (ArrayList<ProductVO>)request.getAttribute("productVOList");
	@SuppressWarnings("unchecked")
	List<Byte> buy_countList = (ArrayList<Byte>)request.getAttribute("buy_countList");
	CustomerVO customerVO = (CustomerVO)request.getAttribute("customerVO");
	String id = (String)session.getAttribute("id");
	
	session.setAttribute("productVOList", productVOList);
	session.setAttribute("buy_countList", buy_countList);
%>

<p class="subtitle">주문서작성</p>
<div align="center">
<ul class="buyStep">
	<li>01 장바구니&nbsp;&nbsp;>&nbsp;&nbsp;</li>
	<li><b>02 주문서작성&nbsp;&nbsp;>&nbsp;&nbsp;</b></li>
	<li>03 결제완료&nbsp;&nbsp;>&nbsp;&nbsp;</li>
	<li>04 주문완료</li>
</ul>
<br>
<form action="buyPro.do" method="post">
<table class="cartTable">
<thead>
	<tr>
		<th width="100px">이미지</th>
		<th width="500px">상품정보</th>
		<th width="150px">판매가</th>
		<th width="100px">수량</th>
		<th width="100px">배송비</th>
		<th width="100px">합계</th>
	</tr>
</thead>
<%
	int total = 0;
	for(int i=0; i<productVOList.size(); i++) {
		ProductVO productVO = productVOList.get(i);
		total += buy_countList.get(i) * productVO.getCom_price();
%>
	
<tbody>
	<tr>
		<td>				<img src="imageFile/<%=productVO.getCom_image()%>" width="80px" height="80px"> </td>
		<td> 				<%=productVO.getCom_title() %></td>
		<td align="center"> <%=NumberFormat.getInstance().format(productVO.getCom_price())%>원 </td>
		<td> 				<%=buy_countList.get(i)%> </td>
		<td> 				무료 </td>
		<td align="center"> <% out.println(NumberFormat.getInstance().format(buy_countList.get(i) * productVO.getCom_price()));%>원 </td>
	</tr>
</tbody>
<%	}
%>
<tfoot>
	<tr>
		<td colspan="8">
			상품구매금액 <% out.println(NumberFormat.getInstance().format(total));%>원 + 배송비 0 (무료) = 합계 : <font size="5"><% out.println(NumberFormat.getInstance().format(total));%>원</font>
		</td>
	</tr>
</tfoot>
</table>
<br>
<p class="subtitle">주문정보</p>
<p>(<font color="red">*</font>은 필수입력 정보입니다.)</p>
<br>
<table class="buyForm">
	<tr>
		<td width="150px"> 받으시는 분<font color="red">*</font> </td>
		<td width="900px"> <input type="text" name="deliveryName" value="<%=customerVO.getName()%>"> </td>
	</tr>
	<tr>
		<td valign="top"> 주소<font color="red">*</font> </td>
		<td>
			<input type="text" name="deliveryAddress" value="<%=customerVO.getAddress()%>" id="address"> 기본주소 <br>
			<input type="text" name="addr2"> 나머지주소
			<p>잠깐! 동,호수 등 상세주소가 입력되었는지 확인해주세요!</p>
		</td>
	</tr>
	<tr>
		<td> 일반전화 </td>
		<td> <input type="text"> </td>
	</tr>
	<tr>
		<td> 휴대전화<font color="red">*</font> </td>
		<td> <input type="text" name="deliveryTel" value="<%=customerVO.getTel()%>"> </td>
	</tr>
	<tr>
		<td valign="top"> 이메일<font color="red">*</font> </td>
		<td>
			<input type="email" name="mail" value="<%=customerVO.getEmail()%>">
			<p>- 이메일을 통해 주문처리과정을 보내드립니다.<br>
			- 이메일 주소란에는 반드시 수신가능한 이메일주소를 입력해 주세요</p>
		</td>
	</tr>
	<tr>
		<td valign="top"> 배송메시지 </td>
		<td>
			<textarea cols="70" rows="5" maxlength="30" name="memo">빠른배송 부탁드립니다!</textarea>
			<p> 안전한 배송처 혹은 직접수령이 아닌경우 택배 분실이 발생할수 있으며,<br>
			이에대한 문의는 택배사에 확인 부탁드립니다.</p>
		</td>
	</tr>
</table>

<input type="submit" value="구매하기" class="buy_btn">
</form>
</div>
</article>

<footer>
	<jsp:include page="../module/bottom.jsp" flush="false" />
</footer>
</div>
</body>
</html>