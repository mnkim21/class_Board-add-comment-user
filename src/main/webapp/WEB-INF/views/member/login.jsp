<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myapp</title>

<%@ include file="/inc/asset.jsp" %>

<style>
	
</style>

</head>
<body>
	<!-- views > member > login.jsp -->
	<%@ include file="/inc/header.jsp" %>
	
	<section class="main-section">
		
		<h1>User</h1>
		
		<div class="user-list">
		
			<!-- loginok.do?id=hong&pw=1111 -->
		
			<button type="button" class="btn btn-default" onclick="location.href='/myapp/member/loginok.do?id=hong&pw=1111';">홍길동</button>
			<button type="button" class="btn btn-default" onclick="location.href='/myapp/member/loginok.do?id=test&pw=1111';">테스트</button>
			<button type="button" class="btn btn-default" onclick="location.href='/myapp/member/loginok.do?id=admin&pw=1111';">관리자</button>
		</div>
		
	</section>	
	
	
	<%@ include file="/inc/init.jsp" %>
	<script>
		
	</script>
</body>
</html>































