<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h4>웰컴 페이지</h4>
<%
	request.setCharacterEncoding("UTF-8");
	String authId = (String)session.getAttribute("authId");
	String message = request.getParameter("message");
	if(message != null && !message.isEmpty()){
		out.println(message);
	}
	if(authId == null || authId.isEmpty()){
	%>
	

<h4>
	<a href = "<%=request.getContextPath() %>/login/loginForm.jsp">로그인하러 가기</a>
</h4>

<%} else{%>
<h4><%=session.getAttribute("authId") %> 님 
<a href = "<%=request.getContextPath() %>/login/logout.do">로그아웃</a></h4>
<%} %>

<script>
	console.log($)
	$(function(){
		console.log($.fn.modal);
	})
</script>



















