<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/elDesc.jsp</title>
</head>
<body>
<h4>표현 언어(Expression Lanuage : EL)</h4>
<pre>
	: (속성)값을 출력(표현)할 목적만 가진 언어
	\${속성명}
	<%
		String sample = "데이터";
		pageContext.setAttribute("sampleAttr", "페이지");
		request.setAttribute("sampleAttr", sample);
		session.setAttribute("sampleAttr", "세션");
		application.setAttribute("sampleAttr", "app");
		
		pageContext.setAttribute("text1", "  ");
		pageContext.setAttribute("array1", new String[]{});
		%>
	<%=sample%> , ${requestScope.sampleAttr}, <%=request.getAttribute("sampleAttr") %>
	${sampleAttra}, <%=request.getAttribute("sampleAttra") %>
	
	지원 기능
	1. 연산자
		산술연산 : +-*/%(+은 문자열 결합x) ${"5" + "2" } => 연산의 중심은 연산자 -> 앞뒤의 피연산자 강제로 형변환 \${"a" + 1 } -> 형변환 오류
				${5/2} -> 연산자 중심이므로 자동으로 실수로 연산/ ${adv - asd} -> 속성명을 뒤짐
				=> el은 스크립트의 특성으로 지원 => 값이 할당 되거나 연산이 수행될 때 타입 결정
		
		논리연산 : &&(and) ||(or) !(not) ${true and true }/ ${"true" and "true" } /${true and abc } -> abc는 속성명 -> null을 강제로 논리형식으로 변환
				${asd and asd } / ${not abc} -> true 
		
		비교연산 : >(gt) , <(lt) , >=(ge) , <=(le) , ==(eq -> 내부적으로는 equals 사용), !=(ne) 
				${3 lt 4} / ${3 gt dgg } -> false/ ${true ne false } ${sampleAttr eq  "페이지"}
		
		단항연산자 : empty => 비워져있으면 true / ${empty sampleAttra }/ ${empty array1}, ${empty text1 } => 속성에서 있는지 본후, 있으면 해당 값의 길이를 비교하여 0이면 비어있는걸로 판단
				
		
		삼항연산자 : 조건식 ? 참 : 거짓 
			${not empty abc? "없다" : "있다" }
		
	2. 자바 객체의 메서드 호출 : ${applicationScope.sampleAttr.length()} / el Spec 2.2부터 사용가능-> 자바 빈 규약에 의해 출력
	=> 속성의 형태로 지원되고 있는 객체, 자바 빈 규약에 생성된 객체
	<%
		MemberVO member = MemberVO.builder().memName("김은대") .build();
		pageContext.setAttribute("member", member);
	%>
	${ member.getMemName() },${member.getMemTest() } -> 2,2버전 / ${member.memName } , ${member.memTest } -> 2.1 버전 /
	
	3. collection 접근 방법 <a href = "elCollection.jsp">collection 접근 방법</a>
	4. 기본 객체 (11 -> map)
		Scope : pageScope, requestScope, sessionScope, applicationScope
				${sessionScope.sampleAttr}, ${sessionScope['sampleAttr'] }
				${pageScope.member.memName }, ${pageScope['member'].memName },  ${pageScope['member']['memName'] } 
			
		요청parameter : param(Map&lt;String, String>), paramValues(Map&lt;String, String[]>)
				${param.param1 } , ${paramValues.param1[1] }
		
		요청 header : header(Map&lt;String, String>), headerValues(Map&lt;String, String[]>))
				${header.accept }, ${header['accept'] }
				${header.user-agent }, ${header['user-agent'] }
				
				<%
					Cookie[] cookies = request.getCookies();		
					for(Cookie tmp : cookies){
						if(tmp.getName().equals("JSESSIONID")){
							out.println(tmp.getValue());
						}
					}
				%>
		cookie(Map&lt;String,Cookie>) : ${cookie.JSESSIONID.value }, ${cookie['JSESSIONID']['value'] }
		
		contextParameter : initParam
			<%= application.getInitParameter("cParam1") %>
			${initParam.cParam1 }, ${initParam['cParam1'] }
		
		pageContext
			<%=pageContext.getRequest() %>, ${pageContext.request.contextPath }
</pre>	
</body>
</html>















