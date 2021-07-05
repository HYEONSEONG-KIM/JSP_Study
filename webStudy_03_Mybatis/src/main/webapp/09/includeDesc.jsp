<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/includeDesc.jsp</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
<h4> include 의 종류</h4>
<pre>
	: include 되는 시점과 대상에 따른 분류
	1. 정적 include : jsp 소스 파싱 단계, 소스 코드가 내포
		코드의 중복을 제거하는데 활용(비추!!!)
	<%--@ include file="/02/standard.jsp" --%>
	web.xml 활용도 가능
	<%--
		String testResult = test();
	--%>
	2. 동적 include : runtime, 실행 결과가 내포
	<%
		String dest = "/02/standard.jsp";
		//request.getRequestDispatcher(dest).include(request,response);
// 		pageContext.include(dest); 
	%>
	<jsp:include page="/02/standard.jsp"/>
	남은 잔여 코드<%--=testResult --%>
</pre>
</body>
</html>