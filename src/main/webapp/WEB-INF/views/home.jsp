<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	첫 페이지 입니다.
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="${pageContext.request.contextPath }/login"> 로그인 페이지</a>
</body>
</html>
