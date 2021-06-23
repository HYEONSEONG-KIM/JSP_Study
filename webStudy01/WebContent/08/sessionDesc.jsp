<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>세션(HttpSession)</h4>
<pre>
	: Http가 가진 Stateless의 단점을 보완하기 위한 최소한의 상태정보를 저장하는 개념
	단, 해당 상태정보가 서버에 저장된 경우 : Session
			클라이언트에 저장된 경우 : Cookie
	session lifecycle
	1. 클라이언트로부터 최초의 요청이 발생하면 생성(ID)
		ID : <%=session.getId()%>, <%=new Date(session.getCreationTime())%>
		
	2. 최초의 요청에 대한 응답에 ID가 실려서 클라이언트로 전송
	
	3. 다음 요청이 발생할때 서버로 ID가 재전송되면 세션이 유지 
		<%=new Date(session.getLastAccessedTime()) %>
		2번과 3번 단계에서 세션ID를 주고 받는 방법(tracking mode)
		- COOKIE : JSESSIONID와 같은 형태의 쿠기로 세션 아이디를 주고받는 방법
		- URL : jsessionid와 같은 세션 파라미터의 형태로 주고받는 방법
			<a href = "sessionDesc.jsp;jsessionid = <%=session.getId()%>">세션을 유지하는 방법</a> 
		- SSL(Secure Socket Server -> Transfer Layer Secure)
	
	4. 세션의 소멸 이벤트 : timeout 이내에 새로운 요청이 발생하지 않으면 소멸
		1) 명시적인 로그아웃
		2) 브라우저 종료
		3) 쿠키 삭제
		4) timeout
</pre>
</body>
</html>




























