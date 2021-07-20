<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myapp</title>

<%@ include file="/inc/asset.jsp" %>

<style>
	
	.table th { width: 120px; }
	.table td { width: 680px; }
	
	.table #content { height: 300px; }
	
</style>

</head>
<body>
	<!-- template.jsp > add.jsp -->
	<%@ include file="/inc/header.jsp" %>
	
	<section class="main-section">
		
		<h1>Board <small>Delete</small></h1>
		
		<form method="POST" action="/myapp/board/delok.do">		
			<table class="table table-bordered">
				<tr>
					<th>번호</th>
					<td>${seq}번 게시물을 삭제합니다.</td>
				</tr>
			</table>
			
			<div class="btns">
				<button type="submit" class="btn btn-primary">삭제하기</button>
				<button type="button" class="btn btn-default" onclick="location.href='/myapp/board/view.do?seq=${seq}';">돌아가기</button>
			</div>
			
			<input type="hidden" name="seq" value="${seq}">
		</form>
		

	</section>	
	
	<%@ include file="/inc/init.jsp" %>
	<script>
		
	</script>
</body>
</html>
