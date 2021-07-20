<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myapp</title>

<%@ include file="/inc/asset.jsp" %>

<style>

	.table th:nth-child(1) {width: 60px;}
	.table th:nth-child(2) {width: auto;}
	.table th:nth-child(3) {width: 80px;}
	.table th:nth-child(4) {width: 120px;}
	.table th:nth-child(5) {width: 60px;}
	.table td:nth-child(2) {text-align: left;}
	
	.searchbox {text-align: center; margin-bottom: 20px;}
	.searchbox .form-control {
		display: inline-block;
		width: auto;
	}
	.searchbox #search {width: 250px;}
	.searchBar {margin: 10px; text-align: center;}
	
</style>

</head>
<body>
	<!-- board/list.jsp -->
	<%@ include file="/inc/header.jsp" %>
	
	<section class="main-section">
		
		<h1>Board <small>List</small></h1>
		
		<c:if test="${map.isSearch == 'y'}">
			<div class="searchBar">
				'${map.search}'으로 검색한 결과 ${list.size()}개의 게시물이 있습니다.
			</div>
		</c:if>
		
		<table class="table table-bordered">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>이름</th>
				<th>날짜</th>
				<th>읽음</th>
			</tr>
			
			<c:if test="${list.size() == 0}">
				<tr>
					<td colspan="5">게시물이 없습니다.</td>
				</tr>
			</c:if>
			 
			<c:forEach items="${list}" var="dto">
			<tr>
				<td>${dto.seq}</td>
				<td>
					<a href="/myapp/board/view.do?seq=${dto.seq}&column=${map.column}&search=${map.search}">${dto.subject}</a>
					
					<c:if test="${dto.ccnt > 0}">
						<span class="badge">${dto.ccnt}</span>
					</c:if>
					
					<!-- 2시간 이내의 글만 new가 생긴다. -->
					<c:if test="${dto.isnew < (2 / 24)}">
						<span class="label label-warning">new</span>
					</c:if>
				</td>
				<td>${dto.name}</td>
				<td>${dto.regdate}</td>
				<td>${dto.readcount}</td>
			</tr>
			</c:forEach>
		</table>
		
		<div class="searchbox">
			<!-- 
				대부분(99%)의 <form>은 method="POST"를 사용
				예외로 전송 다시의 상태를 유지하고 싶을 때는 method="GET"을 사용(ex. 검색결과 북마크)
			 -->
			<form method="GET" action="/myapp/board/list.do">
				<select name="column" id="column" class="form-control">
					<option value="subject">제목</option>
					<option value="content">내용</option>
					<option value="name">이름</option>			
				</select>
				<input type="text" name="search" id="search" class="form-control" required placeholder="검색어를 입력하세요.">
				<input type="submit" value="검색하기" class="btn btn-default">
			</form>			
		</div>
		
		<div class="btns">
		
			<c:if test="${not empty id}">
			<button type="button" class="btn btn-primary" onclick="location.href='/myapp/board/add.do';">글쓰기</button>
			</c:if>
			
			<button type="button" class="btn btn-default" onclick="location.reload();">새로고침</button>
			<button type="button" class="btn btn-default" onclick="location.href='/myapp/board/list.do';">새로고침</button>
		</div>
		
		
		
	</section>	
	
	<%@ include file="/inc/init.jsp" %>
	<script>
		<c:if test="${map.isSearch == 'y'}">
		
			//상태 복원
			$('#column').val('${map.column}');
			$('#search').val('${map.search}');
	
		</c:if>
	</script>
</body>
</html>