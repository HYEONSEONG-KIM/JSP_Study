<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src = "<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src = "<%=request.getContextPath() %>/resources/validate/additional-methods.min.js"></script>
<script type="text/javascript" src = "<%=request.getContextPath() %>/resources/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src = "<%=request.getContextPath() %>/resources/validate/messages_ko.js"></script>

</head>
<body>
<%
	String id = (String)request.getAttribute("id");
%>
<form id = "loginForm" action = "<%=request.getContextPath()%>/login/loginCheck.do" method = "post">
	<ul>
		<li>
		<%
			String pattern = "아이디 : <input type = 'text' id = 'mem_id' name = 'mem_id' value = '%s' required/>";
			if(id != null && !id.isEmpty()){
				out.print(String.format(pattern,id));
			}else{
				out.print(String.format(pattern,""));
			}
		%>
			
		</li>
		
		<li>
			비밀번호 : <input type = "text" id= "mem_pass" name = "mem_pass" required/>
			<input type = "submit" value = "로그인"/>
		</li>
	</ul>
</form>

<script type="text/javascript">
 	$(function(){
	    $("loginForm").validate();
	    $.extend( $.validator.messages, {
	    	required: "필수 항목입니다.",
	    });

	    
	})
	 

	
	
	
	
	
</script>

</body>
</html>

<!-- 
1. 클라이언트에서 검증 완료
 -->