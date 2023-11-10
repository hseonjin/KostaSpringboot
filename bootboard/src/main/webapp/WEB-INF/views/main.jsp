<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style>
	
	#header {height:110px; display:flex; margin: 0 auto;}
	a, b {text-decoration: none; color: black; line-height: 110px; display:inline-block;}
	a:hover {text-decoration: underline; color:grey;}
	img {margin: 0 20px;}
	h3 {text-align: center;}
</style>
</head>
<body>
<div id="header">
	<a href="/"><img src="/resources/img/mirro.jpg" height="100px" width="100px"></a>
	<c:choose>
		<c:when test="${user eq empity}">
			<a href="${contextPath}/login">로그인</a>&nbsp;&nbsp;&nbsp;
			<a href="${contextPath}/join">회원가입</a>&nbsp;&nbsp;&nbsp;
		</c:when>
		<c:otherwise>
			<b>${user.name}</b>&nbsp;&nbsp;&nbsp;
			<a href="${contextPath}/logout">로그아웃</a>&nbsp;&nbsp;&nbsp;
		</c:otherwise>
	</c:choose>
			<a href="${contextPath}/boardlist">게시판</a>
</div>
</body>
</html>