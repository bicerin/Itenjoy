<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.itenjoy.vo.ProductVO" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>컴퓨터 쇼핑몰</title>
<link href="etc/style.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="module/favicon.ico">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
<div id="wrapper">
<%
	@SuppressWarnings("unchecked")
	List<ProductVO> introList = (ArrayList<ProductVO>)request.getAttribute("introList");
	
	String message = (String)request.getAttribute("message");
%>
<script type="text/javascript">
	if("<%=message%>" == "joinFail") {
		swal({title: "회원가입에 실패했습니다. 고객센터로 문의해주십시오",icon: "error",});
	}
	if("<%=message%>" == "joinSuccess") {
		swal("회원가입 성공!", "환영합니다 회원님", "success");
	}
	if("<%=message%>" == "logout") {
		swal({title: "로그아웃 되었습니다",icon: "success",});
	}
</script>
<header>
	<jsp:include page="../module/top.jsp" flush="false" /> 
</header>

<article>
	<div class="width">
		<div class="content" style="width:1200px;height:450px;overflow:hidden;" >
			<div id="slider">
				<a href="#"><img src="module/1.jpg"/></a>
				<a href="#"><img src="module/2.jpg"/></a>
				<a href="#"><img src="module/3.jpg"/></a>
				<a href="#"><img src="module/4.jpg"/></a>
				<a href="#"><img src="module/5.jpg"/></a>
				<a href="#"><img src="module/6.jpg"/></a>
				<a href="#"><img src="module/7.jpg"/></a>
			</div>
		</div>
		
		<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
		<script src="module/vmc.slider.full.js"></script>
		<script>
		$('#slider').vmcSlider({
				width: 1200,
				height: 450,
				gridCol: 10,
				gridRow: 5,
				gridVertical: 20,
				gridHorizontal: 10,
				autoPlay: true,
				ascending: true,
				effects: [
					'fade', 'fadeLeft', 'fadeRight', 'fadeTop', 'fadeBottom', 'fadeTopLeft', 'fadeBottomRight',
					'blindsLeft', 'blindsRight', 'blindsTop', 'blindsBottom', 'blindsTopLeft', 'blindsBottomRight',
					'curtainLeft', 'curtainRight', 'interlaceLeft', 'interlaceRight', 'mosaic', 'bomb', 'fumes'
				],
				ie6Tidy: false,
				random: true,
				duration: 2000,
				speed: 900
			});
		</script>
	</div>
	<section>
	<div class="width">
<%
		String com_kindName = "";
		for(int i=0; i<=7; i++) {
			
			switch(introList.get(4 * i).getCom_kind()) {
			case "100": com_kindName = "CPU";		break;
			case "200": com_kindName = "그래픽카드";	break;
			case "300": com_kindName = "RAM"; 		break;
			case "400": com_kindName = "메인보드"; 	break;
			case "500": com_kindName = "HDD"; 		break;
			case "600": com_kindName = "SSD"; 		break;
			case "700": com_kindName = "케이스"; 		break;
			case "800": com_kindName = "파워"; 		break;
			}
%>
			<div class="title"><span class="kind"><%=com_kindName%></span>
				<span class="more"><a href="getProductList.do?com_kind=<%=introList.get(4 * i).getCom_kind()%>"> 더보기> </a></span>
			</div>
			<div class="clear"></div>
			<hr>
			<div class="section">
<%
			for(int j=i*4; j<(i+1)*4; j++) {			
%>				<div class="contentBox">
					<a href="getProductOne.do?com_id=<%=introList.get(j).getCom_id()%>&Com_kind=<%=introList.get(j).getCom_kind()%>">
						<img src="imageFile/<%=introList.get(j).getCom_image()%>" border="0" width="250" height="250">
					</a>
					<h3><%=introList.get(j).getCom_title()%></h3>
				</div>
<%			}
%>			</div>
<%
		}
%>
	<div class="guide">
		<a href="#"><img src="module/guide1.png"></a>
		<a href="#"><img src="module/guide2.png"></a>
		<a href="#"><img src="module/guide3.png"></a>
	</div>
	</div>
	</section>
</article>

<footer>
	<jsp:include page="../module/bottom.jsp" flush="false" />
</footer>
</div>
</body>
</html>