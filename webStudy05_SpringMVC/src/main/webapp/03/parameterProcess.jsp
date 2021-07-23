<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/parameterProcess.jsp</title>
</head>
<body>
	<h4>요청 파라미터 처리</h4>
	
	<form action = "<%=request.getContextPath()%>/03/parameterProcess" method="get">
<pre>
일반 텍스트 : <input type = "text" name = "param1"/>
숫자 : <input type = "number" name = "param2"/>
라디오 버튼 : <input type = "radio" name = "param3" value = "A"/>A<input type = "radio" name = "param3" value = "B"/>B
체크 박스 :
<input type = "checkbox" name ="param4" value = "C1"/>C1
<input type = "checkbox" name ="param4" value = "C2"/>C2
<input type = "checkbox" name ="param4" value = "C3"/>C3

셀렉트(단일) : 
<select name = "param5">
	<option value>prompt text</option>
	<option value = "O1">Option1</option>
	<option value = "O2">Option2</option>
</select>
셀렉트(다중) :
<select name = "param6" multiple size = "3">
	<option>OPTIONA</option>
	<option>OPTIONB</option>
	<option>OPTIONC</option>
</select>
<textarea rows="5" cols="100" name = "param7"></textarea>
<button type = "button">걍버튼</button>
<input type = "submit" value = "전송"/>
<button type= "reset">취소</button>
</pre>
	</form>
</body>
</html>