<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 바디 만들기 -> post
바디 쪼개기 -> multipart
입력 데이터가 포함되서 넘어 가야함 -> enctype -->
<form method = "post" action = "${pageContext.request.contextPath }/fileUploadUsingFilter.do"
	enctype="multipart/form-data">
	<input type = "text" name = "textParam" value = "${textParam}"/>
	<c:remove var="textParam" scope="session"/>	
	<input type = "file" name = "filePart" accept="image/*"/>
	<button type = "submit">전송</button>
</form>
<c:if test="${not empty imageURL}">
	<img src = "<c:url value='${imageURL }'/>" alt = "${imageFile.originalFilename }">
	<c:remove var="imageURL" scope="session"/>
</c:if>
</body>
</html>