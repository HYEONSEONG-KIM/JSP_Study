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
<form method = "post" action = "<%=request.getContextPath()%>/bts">
	<select name = "btsMember">
		<option value>선택하세요</option>
		<option value = "bui">뷔</option>
		<option value = "jhop">제이홉</option>
		<option value = "jimin">지민</option>
		<option value = "jin">진</option>
		<option value = "jingkuk">정국</option>
		<option value = "rm">rm</option>
		<option value = "suga">슈가</option>
	</select>
</form>

<script type="text/javascript">
	
	$('form select').on('change',function(){
		val = this.value
		if(val == ""){
			return;
		}else{
			$(this).parent().submit();
		}
		
		
	})
	
</script>
</body>
</html>