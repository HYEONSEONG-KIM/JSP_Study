<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/messageView</title>
<script type="text/javascript" src = "<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<button type = "button" class = "msgBtn" data-lang = "ko">한글 메세지 가져오기</button>
<button type = "button" class = "msgBtn" data-lang = "en">영문 메세지 가져오기</button>
<h4></h4>
<script type="text/javascript">
	var h4 = $('h4');
	$(".msgBtn").on('click',function(){
		
		let lang = $(this).data("lang");
		let data = {};
		if(lang){
			data.lang = lang;
		}
		
		$.ajax({
			url : "<%=request.getContextPath()%>/05/messageServiceWithLocale",
			data : data,
			method : "post",
			dataType : "json",
			success : function(resp) {
				h4.empty();
				h4.append(resp.bow);
				
			},
			error : function(errorResp) {

			}

		});
	})
</script>
</body>
</html>