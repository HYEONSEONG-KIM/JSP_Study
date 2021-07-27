<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src = "<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
left입력을 통해 숫자를 입력받고,
값이 변경되는 순간 서버로 비동기 요청을 발생시킴
서버에서 factorial 연산을 수행한 후,
선택한 mime의 형태로 응답을 전송
plain : "2! = 2"
json : 
{
	left : 2,
	operation : !,
	expression : "2! = 2"
}
xml :
<result>
	<left>2</left>
	<expression>2! = 2</expression>
</result>

	
<form id = "factorialForm" action = "<%=request.getContextPath() %>/05/factorial">
	<input type = "radio" name = "mime" value ="json" data-type = "json" data-success = "parseJson"/>JSON
	<input type = "radio" name = "mime" value ="plain" 
	data-type = "text" checked data-success = "parsePlain"/>PLAIN
	<input type = "radio" name = "mime" value ="xml" data-type = "xml" data-success = "parseXml"/>XML
	<input type = "number" name = "left" min = "1" max = "10"/>!
</form>
	
<div id = "resultArea">
</div>

<script type="text/javascript">

	let resultArea = $('#resultArea')
	
	function parsePlain(resp){
		console.log(resp);
		resultArea.html(resp);
	}
	
	function parseJson(resp){
		console.log(resp);
		resultArea.html(resp.expression);
	}
	
	function parseXml(resp){
		resultArea.html($(resp).find("expression").text())
	}
	
	
	
	
	$("form:first").on('submit', function(event){
		event.preventDefault();
		let url = this.action;
		let data = $(this).serialize();
		let method = this.method;
		let radio = $(this).find("[name='mime']:checked");
		let dataType = $(radio).data("type");
		let success = eval($(radio).data("success"));
		console.log(success);
		
		
		console.log(data);
	 	$.ajax({
			url : url,
			data : data,
			method : method,
			dataType : dataType,
			success : success,
			error : function(errorResp) {

			} 

		});
		
		
		return false;
		
	}).find(":input").on('change', function(){
		$(this.form).submit();
		
	});
	
	
	
	<%-- var form = $('#factorialForm');
	
	$(form).submit(function(event){
		event.preventDefault();
		
		url = form.attr('action')
		
		var formData = $(form).serialize();
		var type = formData.substring(5,formData.indexOf('&')).trim();
		if(type == "plain"){
			type = "text"
		}
		
		
		$.ajax({
			url : "<%=request.getContextPath()%>" + url,
			data : formData,
			method : "post",
			dataType : type,
			success : function(resp) {
				console.log(resp)
				if(type == 'json'){
					$('#resultArea').text(resp.left + resp.operation  + resp.expression );
				}else if(type == 'plain'){
					$('#resultArea').text(resp)
				}else{
					$('#resultArea').text(resp)
				}
			},
			error : function(errorResp) {

			}

		});
	}) --%>
	
</script>


</body>
</html>












