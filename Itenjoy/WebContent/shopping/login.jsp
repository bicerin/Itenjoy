<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
	function checkForm() {
		
		if(!loginForm.id.value) {
			swal({
				title: "아이디를 입력해 주세요",
				icon: "warning"
				}).then(function() {
				loginForm.id.focus();
				});
			return false;
		}
		
		if(!loginForm.passwd.value) {
			swal({
				title: "비밀번호를 입력해 주세요",
				icon: "warning"
				}).then(function() {
				loginForm.passwd.focus();
				});
			return false;
		}
		
		loginForm.action = "loginPro.do";
		loginForm.submit();
	}
</script>

<article>
	<div class="memberLogin">
	<h3>MEMBER LOGIN</h3>
	<br>
		<div class="login">
			<form method="post" name="loginForm">
			<input type="text" name="id" maxlength="20" autofocus placeholder="ID" autocomplete="false" value="jsj"><br>
			<input type="password" name="passwd" maxlength="16" placeholder="PASSWORD" value="1234"><br>
			<input type="button" value="LOGIN" onclick="checkForm()">
			</form>
		</div>

		<div class="login2">
			<a href="joinForm.do">
				<div class="login3">
					<p>NEW MEMBER REGISTRATION</p>
					<p>회원가입하시고 특별한 혜택을 받으세요</p>
				</div>
			</a>
			<ul>
				<li><a href="#">FORGOT YOUR ID?</a></li>
				<li><a href="#">FORGOT YOUR PASSWORD?</a></li>
			</ul>
		</div>
	</div>
</article>

<footer>
	<jsp:include page="../module/bottom.jsp" flush="false" />
</footer>
</div>
</body>
</html>