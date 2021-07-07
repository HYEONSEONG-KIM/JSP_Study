<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h4>웰컴 페이지</h4>
<%
	request.setCharacterEncoding("UTF-8");
	MemberVO authMember = (MemberVO)session.getAttribute("authMember");
	String message = request.getParameter("message");
	if(message != null && !message.isEmpty()){
		out.println(message);
	}
	if(authMember == null){
	%>
	

<h4>
	<a href = "<%=request.getContextPath() %>/login/loginForm.jsp">로그인하러 가기</a>
	<a href = "<%=request.getContextPath() %>/member/memberInsert.do">회원가입</a>
</h4>

<%} else{%>
<h4><%=authMember.getMemId()%>, <a href = "<%=request.getContextPath()%>/mypage.do"><%=authMember.getMemName() %> 님 </a>
<a href = "<%=request.getContextPath() %>/login/logout.do">로그아웃</a></h4>
<%} %>

<script>
	console.log($)
	$(function(){
		console.log($.fn.modal);
	})
</script>



















