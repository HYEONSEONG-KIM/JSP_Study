<%@page import="kr.or.ddit.servlet01.ImageListServlet"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/resourceIdentify.jsp</title>
</head>
<body>
	<h4>자원의 식별</h4>
<pre>	
	*주소를 사용할 시 서버에서 사용하지 클라이언트에서 사용할지 구분!!!!
	1. file system resource : ex)d:\contetns\xxx.jpg -> 하드에있는 경로
	2. web resource : (URL/URI), http://localhost:port/contextPath/images/xxx.jpg
	=> URL을 갖고 있는 파일, webcontent 안에 있는 파일
	3. classpath resource : kr/or/ddit/servlet01/xxx.jpg -> res에 있는 경로
	<%
		// file system resource
		File fileSystemRes = new File("C:/Users/PC/Desktop/중간프로젝트/마티네.jpg");
		//서버의 설치 경로를 바탕
		// web resource
		String realPath = application.getRealPath("/images/마티네.jpg");
		File webRes = new File(realPath);
		
		// classpath resource
		String realPath2 = ImageListServlet.class.getResource("/kr/or/ddit/servlet01/합창단1.jpg").getFile();
		File classPathRes = new File(realPath2);  
	%>
	<%=fileSystemRes.length() %>
	<%=realPath%> : <%=webRes.length() %>
 	 <%=realPath2 %> : <%=classPathRes.length() %>  

	** web resource 식별 방법
	URI(Uniform Resource Identifier)
	- URL(Uniform Resource Locator) : 자원의 위치를 기준으로 식별
	- URN(Uniform Resource Name) : 자원에 등록된 이름으로 식별
	- URC(Uniform Resource Content) : 자원의 등록된 컨텐츠로 식별
	<%=request.getRequestURI() %>
	<%=request.getRequestURL() %>
	
	자원에 접근하는 경로 표기법
	1. 상대 경로 : 경로가 생략된 구조. whil card(., ..)
				현재 위치(브라우저의 주소)를 기분으로 실제 자원의 절대 경로를 판단
	2. 절대 경로 : "/" 로 시작
		1) client side : content root 부터 시작되는 경로 표기 
		2) server side : content root 이후의 경로를 표기
</pre>
<img src = "../images/만프레드.jpg"/>
<img src = "http://localhost/webStudy01/images/만프레드.jpg"/>
<img src = "<%=request.getContextPath()%>/images/만프레드.jpg"/>
</body>
</html>




















