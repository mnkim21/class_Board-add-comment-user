<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="main-header">
	<section>
		<nav>
			<ul>
				<li><img src="/myapp/asset/images/logo.png"></li>
				<li onclick="location.href='/myapp/index.do';" title="시작 페이지">Home</li>
				<li>Something</li>
				<li onclick="location.href='/myapp/board/list.do';" title="게시판">Board</li>
			</ul>
		</nav>
		<div class="auth">
			
		
			<c:if test="${ not empty id }">
			<div>${ name }(${ id })</div>
			<div class="btn-auth" onclick="location.href='/myapp/member/logout.do';" title="로그아웃">Logout</div>
			</c:if>
			
			<c:if test="${ empty id }">
			<div class="btn-auth" onclick="location.href='/myapp/member/login.do';" title="로그인">Login</div>
			</c:if>
		</div>
	</section>
</header>









