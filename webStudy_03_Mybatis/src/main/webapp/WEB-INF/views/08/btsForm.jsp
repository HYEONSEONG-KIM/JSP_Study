<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src = "<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<%
	Map<String,String[]> btsMap =  (Map<String,String[]>)application.getAttribute("btsMap");
%>
<form method = "post">
	<select name = "btsMember" onchange = "this.form.submit();">
		<option value>멤버선택</option>
		<%
			for(Entry<String,String[]> entry : btsMap.entrySet()){
				String code = entry.getKey();
				String[] info = entry.getValue();
			%>
			<option value = "<%=code%>"><%=info[0] %></option>
			<%
			}
		
		%>
	</select>
</form>

<!-- <script type="text/javascript">
	
	$('form select').on('change',function(){
		val = this.value
		if(val == ""){
			return;
		}else{
			$(this).parent().submit();
		}
		
		
	})
	
</script> -->
</body>
</html>