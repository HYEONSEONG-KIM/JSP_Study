<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/standard.jsp</title>
</head>
<body>
	<h4>JSP(Java Server Page) 표준 구성 요소</h4>
<pre>
	1. 정적 텍스트(Front-End, client side) : HTML, JavaScript, CSS
	2. Back-End, Server side
		1) scriptlet : <% // java code %> , 지역 코드화
		2) expression : <%="출력 데이터" %>
		3) directive : <%--@ 지시자명  --%>
			- page : 현재 jsp페이지에 대한 설정정보(mime, import, errorPage...)
			- include : 정적 내포
			- taglib : 커스텀 태그 라이브러리 로딩
		4) declaration : <%! // 전역 멤버 선언 %>
		5) comment : <%-- --%>
			- client side comment ; HTML, Javascript, CSS
			<!-- <div></div> -->
			<script type="text/javascript">
// 				자바스크립트 주석
			</script>
			<style type="text/css">
/* 				table{ */
/* 				} */
			</style>
			- server side comment : Java, jsp
			<%
				// single line
				
				/*
					multi line
				*/			
				
				/**
					doc
				**/
			%>
			<%--
				JSP comment
			 --%>
	3. 기본 객체
	4. 액션 태그
	5. EL(표현언어)
	6. JSTL(tag library)
	
</pre>
</body>
</html>