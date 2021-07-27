<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
   <!--  errorPage="/error/errorView.jsp" -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>custom error page</h4>
<pre>
	1. 지역적 에러 처리 : page 지시자 활용( 1 순위 )
	<%
		if(1 == 1)
			throw new SQLException("강제 발생 예외");
	%>
	2. 전역적 에러 처리 : web.xml 파일 활용
		- 발생한 예외 타입별 처리 : exception-type( 2 순위 )
		- 에러 상태 코드별 처리 : error-code( 3 순위 )
</pre>
</body>
</html>