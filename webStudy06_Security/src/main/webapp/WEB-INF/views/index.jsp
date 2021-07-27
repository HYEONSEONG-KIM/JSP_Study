<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<h4><spring:message code="welcomeMsg"/></h4>
누적 방문자수 :${userCount}  , 현재 방문자 수 : ${currentUserCount }

로그인 되었다면,
<c:if test="${not empty authMember}">
접속자 리스트 : ${currentUserList}
</c:if>
<c:if test="${not empty message}">
	<h6>${message}</h6>
</c:if>

<%
	request.setCharacterEncoding("UTF-8");
%>
	


<security:authorize access="isFullyAuthenticated()">
	<!-- 인증 객체 접근 -->
	<security:authentication property="principal.adaptee" var="authMember"/>
	<security:authentication property="authorities" var="authMemRoles"/>
	<h4>
			<a href="${pageContext.request.contextPath }/mypage.do">${authMember.memName}[${authMemRoles }]님 </a><br>
			<%-- <c:if test="${not empty authMember.memImg}">
			<img src = "data:image/*;base64,${ authMember.base64Img}"/>
			</c:if> --%>
			<a href="${pageContext.request.contextPath }/login/logout.do">로그아웃</a>
		</h4>
</security:authorize>


<security:authorize access="!isFullyAuthenticated()">
		<h4>
			<a href="${pageContext.request.contextPath }/login/loginForm.jsp"><spring:message code="login"/></a><br>
			<a href="${pageContext.request.contextPath }/member/memberInsert.do"><spring:message code="regist"/></a>
		</h4>

</security:authorize>




<script>
	console.log($)
	$(function(){
		console.log($.fn.modal);
	})
</script>



















