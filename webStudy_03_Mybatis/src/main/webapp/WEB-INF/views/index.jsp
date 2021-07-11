<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4>웰컴 페이지</h4>

<c:if test="${not empty message}">
	<h6>${message}</h6>
</c:if>

<%
	request.setCharacterEncoding("UTF-8");
%>
	




<c:choose>
	<c:when test="${empty authMember}">
		<h4>
			<a href="${pageContext.request.contextPath }/login/loginForm.jsp">로그인</a><br>
			<a href="${pageContext.request.contextPath }/member/memberInsert.do">회원가입</a>
		</h4>
	</c:when>
	
	<c:otherwise>
		<h4>${authMember.memId},
			<a href="${pageContext.request.contextPath }/mypage.do">${authMember.memName}님 </a><br>
			<a href="${pageContext.request.contextPath }/login/logout.do">로그아웃</a>
		</h4>
	</c:otherwise>

</c:choose>



<script>
	console.log($)
	$(function(){
		console.log($.fn.modal);
	})
</script>



















