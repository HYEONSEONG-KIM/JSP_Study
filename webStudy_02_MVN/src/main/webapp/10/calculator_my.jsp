<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form action ="<%=request.getContextPath() %>/calculate.do" method = "post">
	<input type = "radio" name = "mime" value = "plain" data-type = "text"  data-success = "toPlain" checked/>PLAIN
	<input type = "radio" name = "mime" value = "json" data-type = "json" data-success = "toJson"/>JSON
	<input class = "form-control" type = "number" name = "left"/>
	<div id = "operator"></div>
	<input class = "form-control" type = "number" name = "right"/>
	<input type = "submit" value = "="/>
	<span id = "resultArea"></span>
</form>

<script>
	
	let selector = "<select name ='operator'>"
	$.ajax({
		url : "<%=request.getContextPath()%>/calculate.do",
		dataType : "json",
		success : function(resp) {
			
			$.each(resp, function(i,v){
				console.log(v)
				selector += "<option value = '" + v.value +"'>" + v.value + "</option>"
			})
			selector += "</select>";
			
			$('#operator').html(selector);
			
		},
		error : function(errorResp) {

		}

	});
	
	function toPlain(resp){
		$('#resultArea').html(resp);
	}
	
	function toJson(resp){
		$('#resultArea').html(resp.expression);
	}
	
	
	$("form:first").on('submit',function(event){
		event.preventDefault();
		let url = this.action;
		let data = $(this).serialize();
		let method = this.method;
		let radio = $(this).find("[name='mime']:checked");
		let dataType = $(radio).data("type");
		let success = eval($(radio).data("success"));
		
		console.log(url);
		console.log(data);
		console.log(method);
		console.log(dataType);
		console.log(success);
		
		$.ajax({
			url : url,
			data : data,
			method : method,
			dataType : dataType,
			success : success,
			error : function(errorResp) {

			}

		});
		
	})
	
	

</script>
