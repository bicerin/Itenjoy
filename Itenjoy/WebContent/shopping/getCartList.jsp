<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.NumberFormat" %>
<%@ page import = "com.itenjoy.vo.CartVO" %>
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
<script type="text/javascript">

	function toggle(source) {
		checkboxes = document.getElementsByName('selected');
		for(var i=0; i<checkboxes.length; i++) {
		    checkboxes[i].checked = source.checked;
		  }
	}
	
	function buyAll() {
		checkboxes = document.getElementsByName('selected');
		for(var i=0; i<checkboxes.length; i++) {
			checkboxes[i].checked = true;
		}
		document.cartForm.action="buyForm.do";
		document.cartForm.submit();
	}
	function deleteAll() {
		checkboxes = document.getElementsByName('selected');
		for(var i=0; i<checkboxes.length; i++) {
			checkboxes[i].checked = true;
		}
		document.cartForm.action='deleteCart.do';
		document.cartForm.submit();
	}
	
 	function chkConfirm(x) {
		checkboxes = document.getElementsByName('selected');
		var sum = 0;
		
		for(var i=0; i<checkboxes.length; i++) {
			if(!checkboxes[i].checked)
				sum += 0;
			if(checkboxes[i].checked)
				sum += 1;
		}
		
		if(sum == 0) {
			swal({title: "제품을 선택해 주세요",icon: "warning",});
		}else if(sum!=0 && x=="delete") {
			swal({
				title: "선택한 상품이 삭제되었습니다",
				icon: "success"
				}).then(function() {
					document.cartForm.action='deleteCart.do';
					document.cartForm.submit();
				});
		}else if(sum!=0 && x==undefined) {
			document.cartForm.action='buyForm.do';
			document.cartForm.submit();
		}
	}
 	
 	function updateCount() {
 		swal({
			title: "수량이 변경되었습니다",
			icon: "success"
			}).then(function() {
			cartForm.action = "updateCart.do";
	 		cartForm.submit();
			});	
 	}
</script>
<article>
<div class="width">
<%
	String id = (String)session.getAttribute("id");
	if(id == null)
		response.sendRedirect("shopMain.do");
	
	@SuppressWarnings("unchecked")
	List<CartVO> cartList = (ArrayList<CartVO>)request.getAttribute("cartList");
	
	Object message = request.getAttribute("message");
	Object com_title = request.getAttribute("com_title");
	Object stock = request.getAttribute("stock");
%>

<script type="text/javascript">

	var message = "<%=message%>";
	if(message != "null") {
		var com_title = "<%=com_title%>";
		var stock = "<%=stock%>";
		swal(com_title + "상품의 재고가 부족합니다.", "상품의 재고: " + stock, "error");
	}
</script>
	<br>
	<p class="subtitle">장바구니</p>
<%	
	if(cartList.size() == 0) {
%>		<div class="emptyCart">
			<p>장바구니가 비어 있습니다.</p>
		</div>
<%	}	
%>
	<div align="center">
	<form method="post" name="cartForm">
	<table class="cartTable">
	<thead>
		<tr>
			<th width="30px"><input type="checkbox" onclick="toggle(this)"></th>
			<th width="100px">이미지</th>
			<th width="500px">상품정보</th>
			<th width="150px">판매가</th>
			<th width="100px">수량</th>
			<th width="100px">배송비</th>
			<th width="100px">합계</th>
			<th width="100px">선택</th>
		</tr>
	</thead>
	<tbody>
<%
	int total = 0;
	List<Byte> buy_countList = new ArrayList<Byte>();
	
	for(int i=0; i<cartList.size(); i++) {
		CartVO cartVO = cartList.get(i);
		total += cartVO.getBuy_price() * cartVO.getBuy_count();
		buy_countList.add(cartVO.getBuy_count());
%>		<tr>
			<td><input type="checkbox" name="selected" value="<%=cartVO.getCart_id()%>"></td>
			<td><a href="getProductOne.do?com_id=<%=cartVO.getCom_id()%>"><img src="imageFile/<%=cartVO.getCom_image()%>" width="80px" height="80px"></a></td>
			<td><%=cartVO.getCom_title()%></td>
			<td><%=NumberFormat.getInstance().format(cartVO.getBuy_price())%>원</td>
			<td>
				<input type="number" value="<%=cartVO.getBuy_count()%>" min="1" max="127" name="buy_count<%=i%>" autocomplete="off"><br>
				<input type="hidden" name="com_id<%=i%>" value="<%=cartVO.getCom_id()%>">
				<input type="hidden" name="cart_id<%=i%>" value="<%=cartVO.getCart_id()%>">
				<input type="hidden" name="id" value="<%=id%>">
				<input type="button" value="변경" width="50px" onclick="updateCount()">
			</td>
			<td>무료</td>
			<td><% out.println(NumberFormat.getInstance().format(cartVO.getBuy_count() * cartVO.getBuy_price()));%>원</td>
			<td>
				<input type="button" value="주문하기" width="50px" height="22px" onclick="location.href='buyForm.do?com_id=<%=cartVO.getCom_id()%>&buy_count=<%=cartVO.getBuy_count()%>'"><br>
				<input type="button" value="삭제" width="50px" height="22px" onclick="location.href='deleteCart.do?cart_id=<%=cartVO.getCart_id()%>'">
			</td>
		</tr>
<%	}
%>	</tbody>
	<tfoot>
		<tr>
			<td colspan="8">
				상품구매금액 <%=NumberFormat.getInstance().format(total)%>원 + 배송비 0 (무료) = 합계 : <font size="5"><%=NumberFormat.getInstance().format(total)%>원</font>
			</td>
		</tr>
	</tfoot>
	</table>
	<div>
		<div class="floatBtn">
		<span class="btnLeft"> 선택상품을 <input type="button" value="삭제하기" onclick="chkConfirm('delete');"> </span>
		<span class="btnRight"> <input type="button" value="장바구니비우기" onclick="deleteAll()"> </span>
	</div>
	<div>
	<table class="cartTable" id="cart">
		<tr height="70px">
			<td width="295px">총 상품금액</td>
			<td width="295px">총 배송비</td>
			<td width="590px">결제예정금액</td>
		</tr>
		<tr height="88px">
			<td><font size="5"><%=NumberFormat.getInstance().format(total)%>원</font></td>
			<td><font size="5">+0(무료)</font></td>
			<td><font size="5">=<%=NumberFormat.getInstance().format(total)%>원</font></td>
		</tr>
	</table>
	</div>
	<div class="buyBtns">
		<input type="button" value="전체상품 주문" onclick="buyAll();" id="buy_All">
		<input type="button" value="선택상품 주문" onclick="chkConfirm();">
	</div>
	</div>
	</form>
	</div>
<%
	request.setAttribute("buy_countList", buy_countList);
%>
</div>
</article>

<footer>
	<jsp:include page="../module/bottom.jsp" flush="false" />
</footer>
</div>
</body>
</html>