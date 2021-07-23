<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
		
		<c:choose>
			<c:when test="${empty btsMap}">
				<option value>==비어있음==</option>
			</c:when>
			
			<c:otherwise>
				<c:forEach var = "btsMember" items="${btsMap}">
					<option value = '${btsMember.key}'>${btsMember.value[0]}</option>
				</c:forEach>
			
			
			</c:otherwise>
		
		</c:choose>

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