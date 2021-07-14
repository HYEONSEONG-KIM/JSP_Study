<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4>웰컴 페이지</h4>
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
			<c:if test="${not empty authMember.memImg}">
			<img src = "data:image/*;base64,${ authMember.base64Img}"/>
			</c:if>
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



















