<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myapp</title>

<%@ include file="/inc/asset.jsp" %>

<style>
	
	.main-section .table th { width: 120px; }
	.main-section .table td { width: 280px; text-align: left;}
	
	.main-section .table tr:nth-child(4) th {height: 300px}
	
	#tblAddComment, #tblListComment {width: 700px; margin: 15px auto;}
	
	#tblAddComment {margin-top: 30px;}
	#tblAddComment td:nth-child(1) {width: 600px;}
	#tblAddComment td:nth-child(2) {width: 100px;}
	
	#tblListComment td:nth-child(1) {width: 600px;}
	#tblListComment td:nth-child(2) {width: 100px;}
	#tblListComment td {
		position: relative;
		left: 0;
		top: 0;
	}
	#tblListComment td span {
		position: absolute;
		right: 10px;
		bottom: 5px;
		color: #AAA;
		font-size: 11px;
	}
</style>

</head>
<body>
	<!-- template.jsp > add.jsp > view.jsp-->
	<%@ include file="/inc/header.jsp" %>
	
	<section class="main-section">
		
		<h1>Board <small>View</small></h1>
		
		
		<table class="table table-bordered">
			<tr>
				<th>번호</th>
				<td>${dto.seq}</td>
				<th>이름</th>
				<td>${dto.name}(${dto.id})</td>
			</tr>
			<tr>
				<th>날짜</th>
				<td>${dto.regdate}</td>
				<th>읽음</th>
				<td>${dto.readcount}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">${dto.subject}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">${dto.content}</td>
			</tr>
			
		</table>
			
		<div class="btns">
			<c:if test="${not empty id}">
			
			<c:if test="${dto.id == id}"> <!-- 해당글의 글쓴이 아이디와 로그인한 사람의 아이디가 같다면 -->
			<button type="button" class="btn btn-primary" onclick="location.href='/myapp/board/edit.do?seq=${dto.seq}';">수정하기</button>
			<button type="button" class="btn btn-primary" onclick="location.href='/myapp/board/del.do?seq=${dto.seq}';">삭제하기</button>
			</c:if>
			
			<button type="button" class="btn btn-primary" onclick="location.href='/myapp/board/add.do';">답변달기</button>
			</c:if>
			
			<button type="button" class="btn btn-default" onclick="location.href='/myapp/board/list.do?column=${column}&search=${search}';">돌아가기</button>
		</div>
		
		<div>
		
			<c:if test="${not empty id}">
			<form method="POST" action="/myapp/board/addcomment.do">
				<table id="tblAddComment" class="table table-bordered">
					<tr>
						<td><input type="text" name="content" id="content" class="form-control" required placeholder="댓글을 작성하세요."></td>
						<td><input type="submit" value="댓글쓰기" class="btn btn-default"></td>
					</tr>
				</table>
				<input type="hidden" name="pseq" value="${dto.seq}">
			</form>
			</c:if>
			
			<table id="tblListComment" class="table table-bordered">
				<c:if test="${clist.size() == 0}">
				<tr>
					<td colspan="2">댓글이 없습니다.</td>
				</tr>
				</c:if>
				<c:forEach items="${clist}" var="cdto">
				<tr>
					<td>
						${cdto.content}
						<span>${cdto.name}. ${cdto.regdate}</span>
					</td>
					<td>
						<c:if test="${not empty id}">
							<c:if test="${cdto.id == id}">
								<input type="button" value="삭제하기" class="btn btn-default" onclick="location.href='/myapp/board/delcomment.do?seq=${cdto.seq}&pseq=${dto.seq}';">
							</c:if>
						</c:if>
					</td>
				</tr>		
				</c:forEach>	
			</table>
		</div>
		

	</section>	
	
	<%@ include file="/inc/init.jsp" %>
	<script>
		
	</script>
</body>
</html>
