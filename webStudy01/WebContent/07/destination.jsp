<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcon to the B</title>
</head>
<body>
<pre>
도착페이지
request scope :<%=request.getAttribute("contents") %>
session scope : <%=session.getAttribute("contents") %>
</pre>
</body>
</html>