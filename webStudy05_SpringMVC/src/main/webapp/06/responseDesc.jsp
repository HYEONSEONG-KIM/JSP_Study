<%@ page language="java" pageEncoding="UTF-8"%>
<%--
	response.setContentType("text/html; charset=UTF-8");
	response.setHeader("Content-type",  "text/html; charset=UTF-8");
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/responseDesc.jsp</title>
</head>
<body>
<h4>HttpServletResponse (response 기본객체)</h4>
<pre>
	: 서버에서 클라이언트로 전송되는 데이터를 갭슐화한 객체

	1. Response Line : protocol, status code(상태 코드)
	<%--
		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "강세 서버 에러");
		return;
	--%>
		** 상태 코드 : 요청 처리의 결과를 표현하는 숫자 체계
		100~ : ing...(웹소켓의 개념-> http기본 흐름=> connectless, stateless)
		200~ : OK
		300~ : 처리 완료를 위해 클라이언트로 부터 추가적인 액션이 필요한 경우
			304(Not Modified), 302/307(Moved <- Location)
		400~ : client side fail
			404(Not Found), 405(Not supported method)
			415(Not supported media type)
			400(Bad Request) - 데이터 형식을 잘못 넘겨주었을때, 파라미터 형태가 형식, 제약조건과 맞지 않을때  => DB의 구조와 관련
			401(unAuthorized-인가받지 않음), 403(Forbidden-금지) 권한처리
		500~ : server side fail
			500(Internal Server Error) => 클라이언트에게 서버의 정보를 주지 않기위해 거의 500으로 처리
	2. Response Header : Meta data, setHeader(name, value);
		* Content Type : body영역의 데이터 mime
		* Cache-Control(Http 1.1버전), Pragma(1.0), Expires(만료시점 결정->date타입) : 캐시를 제어할 때 사용
		 <a href = "cacheControl.jsp">캐시 제어 예제</a>
		* Refresh : auto request
			<a href = "autoRequest.jsp">Refresh를 통한 자동 요청</a>
		* Location : 페이지 이동
			<a href = "flowControl.jsp">페이지 이동 예제</a>
	3. response Body 

</pre>
<img src = "<%=request.getContextPath() %>/resources/images/합창단3.jpg"/>
</body>
</html>










