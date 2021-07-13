<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	
	.error{
		color : red;
	}

</style>
<script type="text/javascript" src = "<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src = "https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src = "https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.min.js"></script>
</head>
<body>
<%--
	String failId = (String)session.getAttribute("failId");
--%>
<%-- <%
	// flash attribute
	String message = (String)session.getAttribute("message");
	session.removeAttribute("message");
%> --%>
<div class = "error">
	${message }
	<c:remove var="message" scope="session"/>
	${errors }
	
</div>
<form name = "loginForm" id = "loginForm" action = "<%=request.getContextPath()%>/login/loginCheck.do" method = "post">
	<ul>
		<li>
		<%--
			String pattern = "아이디 : <input type = 'text' id = 'mem_id' name = 'mem_id' value = '%s' required/>";
			if(failId != null && !failId.isEmpty()){
				out.print(String.format(pattern,failId));
			}else{
				out.print(String.format(pattern,""));
			}
		--%>
		
			아이디 : <input type = 'text' data-msg-required = "필수 데이터" id = 'mem_id' name = 'mem_id' value = '${failId }'  required/>
		</li>
		
		<li>
			비밀번호 : <input   type = "text" data-msg-pattern = "형식확인" id= "mem_pass" name = "mem_pass" required/>
			<!-- pattern = "^(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[0-9]+)(?=.*[!@#\\$%\\^\\&\\*]+).{4,8}$" -->
			<input type = "submit" value = "로그인"/>
		</li>
	</ul>
</form>



<script type="text/javascript">
/* 	$("[name = 'loginForm']").on("submit", function(){
		let regexPtrn = this.mem_pass.pattern;
		let password = this.mem_pass.value;
		// /regex/igm
		let regexp = new RegExp(regexPtrn , "gm");
		let result = regexp.test(password);
		console.log(regexp.exec(password));
		
//		return result;
		return true;
	}) */
	
	//$("[name = 'loginForm']").validate();
	 
	
</script>
</body>
</html>

