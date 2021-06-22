<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/implicitObject.jsp</title>
</head>
<body>
<h4>기본객체(내장객체)</h4>
<pre>
<%-- <%=request.getContextPath()%>
<%=((HttpServletRequest)pageContext.getRequest()).getContextPath() %>
el에서 지원하는 유일한 기본객체=> pageContext
${pageContext.request.contextPath} --%>
	: jsp container에 의해 서블릿 소스가 파싱될때 자동으로 생성되는 객체
	1. request(*) : client 와 request에 대한 정보를 가진 객체
	2. response(*) : client 로 전송될 response에 대한 정보를 가진 객체
	3. out(JspWriter, *) : 응답데이터를 버퍼에 기록할 출력 스트림
		=> buffer를 제어하거나 상태를 확인할때도 활용
	4. session(HttpSession, *) : 하나의 클라이언트가 하나의 브라우저를 사용할때, 해당 클라이언트를 식별할 용도로 사용됨
	5. application(ServletContext, **) : 현재 서버와 어플리케이션 자체에 대한 정보를 가진 객체
	6. config(ServletConfig) : 서블릿 객체 하나당 하나씩 생성 => 싱글톤
	7. page(Object)==this : jsp 인스턴스 자체
	8. exception(throwable) : 에러나 예외가 발생했을때 그 상황을 처리할 목적의 페이지에서 사용됨
		=> page지시자의 isErrorPage="ture" 인 경우에만 활성화 됨
	9. pageContext(*****) : 모든 기본객체 중 가장 먼저 생성되고, 나머지 기본객체에 대한 참조를 가짐
</pre>
</body>
</html>