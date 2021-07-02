<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcom to the A</title>
</head>
<body>
	<h4>웹 어플리케이션에서 흐름 제어(A->B)</h4>
<pre>
1. Request Dispatch : 도착지로 이동하는 과정에서 원본 요청에 대한 정보를 가지고 분기
	1) forward : 이동하기 전에 버퍼의 내용을 clear시킴
	2) include : 도착지에서 만들어진 결과데이터를 가지고 복귀
	<%--
		String dest = "/07/destination.jsp";
		RequestDispatcher rd =  request.getRequestDispatcher(dest);
		//rd.forward(request, response); // => 버퍼에 대한 제어권을 갖고있는 response를 넘겨줌
		rd.include(request, response);
	--%>
2. Redirect : Http의 stateless 특성에 따라 이동하는 과정에서 원본 요청에 대한 정보 삭제 
	이동하는 과정에서 원본요청에 대한 응답이 먼저 전송(****);
	- body가 없고, 상태코드(302) + header(Location)로 구성된 응답이 전송
	=> Location 방향으로 클라이언트의 새로운 요청이 발생
	<%
		String dest = request.getContextPath() + "/07/destination.jsp";
		response.sendRedirect(dest);
	%>
</pre>
</body>
</html>


















