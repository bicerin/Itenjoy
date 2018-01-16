<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.text.NumberFormat" %>
<%@ page import="com.itenjoy.vo.ProductVO" %>
<%@ include file = "../etc/color.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>컴퓨터 쇼핑몰</title>
<link href="etc/style.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="../module/favicon.ico">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
<div id="wrapper">
<header>
	<jsp:include page="../module/top.jsp" flush="false" />
</header>

<article>
<%
	String com_id = request.getParameter("com_id");
	String id = (String)session.getAttribute("id");
	ProductVO product = (ProductVO) request.getAttribute("product");
	
	NumberFormat nf = NumberFormat.getInstance();
	String price = nf.format(product.getCom_price());
%>
<script type="text/javascript">

	function calculation() {
		
		var price = <%=product.getCom_price()%>;
		var count = document.inform.count.value;
		
		var total = price * count;
		var end = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		document.getElementById('price').textContent = end + '원';
	}
	
	function moveBuyForm() {
		
		var stock = "<%=product.getCom_count()%>";
		stock *= 1;
		var count = document.inform.count.value;
		count *= 1;
		if(stock < count) {
			alert('삐빅 재고부족');
			swal("재고가 부족합니다!", "주문수량을 조정해주세요", "error");
			return false;
		}
		
		if("<%=id%>" == "null") {
			swal({
				title: "로그인이 필요한 서비스입니다",
				icon: "warning"
				}).then(function() {
				window.location.href = "login.do";
				});
		}else {
			var count = document.inform.count.value;
			location.href='buyForm.do?com_id=' + <%=com_id%> + '&buy_count=' + count;
		}
	}
	
	function addCart() {
		
		var stock = "<%=product.getCom_count()%>";
		stock *= 1;
		var count = document.inform.count.value;
		count *= 1;
		if(stock < count) {
			swal("재고가 부족합니다!", "주문수량을 조정해주세요", "error");
			return false;
		}
		
		if("<%=id%>" == "null") {
			swal({
				title: "로그인이 필요한 서비스입니다",
				icon: "warning"
				}).then(function() {
				window.location.href = "login.do";
				}); 
		}else {
			swal({
				title: "상품이 장바구니에 담겼습니다",
				icon: "success"
				}).then(function() {
				document.inform.action = "insertCart.do";
				document.inform.submit();
				});
		}
	}
</script>
<div class="content1">

	<div class="productImage">
		<img src="imageFile/<%=product.getCom_image()%>" border="0" width="450" height="450">
	</div>
	
	<div class="infoArea">
	
		<div class="com_title"> <%=product.getCom_title()%> </div>
		<hr>
		<hr>
		<table class="infoTable">
			<tr>
				<td width="150px"> 판매가 </td>
				<td width="150px"> <%=price%>원 </td>
			</tr>
			<tr>
				<td> 배송비 </td>
				<td> 무료 </td>
			</tr>
			<tr>
				<td> 제조사 </td>
				<td> <%=product.getManufacture_com()%> </td>
			</tr>
			<tr>
				<td> 출시일 </td>
				<td> <%=product.getManufacture_date()%> </td>
			</tr>
		</table>
		<hr>
		<form method="post" name="inform">	  		
			<div class="count">
				<label for="count">수량(재고:<%=product.getCom_count()%>)</label>
				<input type="number" name="buy_count" value="1" min="1" max="127" id="count" onclick="calculation();" autocomplete="off"> 
				<span id="price"><%=nf.format(product.getCom_price())%>원</span>
			</div>
		
		<input type="button" value="BUY IT NOW" class="buyBtn" onclick="moveBuyForm()">
		<input type="button" value="ADD TO CART" class="addCartBtn" onclick="addCart()">
		
		<input type="hidden" name="com_id" value="<%=com_id%>">
		<input type="hidden" name="com_image" value="<%=product.getCom_image()%>">
		<input type="hidden" name="com_title" value="<%=product.getCom_title()%>">
		<input type="hidden" name="com_price" value="<%=product.getCom_price()%>">
		<input type="hidden" name="id" value="<%=id%>">
		</form>
	</div>
</div>

<div class="content2">
	<img src="imageFile/<%=product.getCom_content()%>">
</div>
</article>

<footer>
	<jsp:include page="../module/bottom.jsp" flush="false" />
</footer>
</div>
</body>
</html>