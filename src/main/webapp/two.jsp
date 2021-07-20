<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<%@ include file="/inc/asset.jsp" %>

<style>

</style>

</head>
<body>
	<div class="container">
		<h1 class="page-header">두번째 페이지</h1>
		
		<!-- 서버로부터 페이지를 새로 받아옴 -->
		<input type="button" value="첫번째 페이지" onclick="location.href='one.jsp';">
		
		<!-- 이전 페이지를 스냅샷 찍어 뒀다가 돌아감 -->
		<input type="button" value="첫번째 페이지" onclick="history.back();"> 
	</div>
	
	<script>
		
	</script>
</body>
</html>