<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			url : "<%=request.getContextPath()%>/CapcharImage.do",
			dataType:"json",
			success : function(data) {
				path = "<%=request.getContextPath()%>/captchaImage/" + data.fileName + ".jpg";
				console.log(data.key);
				$("#key").val(data.key);
				$("#getimage").html("<img src='" + path+"'>");
				console.log(data.key)
				console.log(data.fileName)
				
			}
		});
		$("#btn").on("click",function(){
			var form01Data = $("#form").serialize();
			console.log(form01Data);
			$.ajax({
				url : "<%=request.getContextPath()%>/CapcharCheck.do",
				data : form01Data,
				dataType:"json",
				success : function(data) {
					if(data == "true"){
						alert("성공")
					}else{
						alert("실패")
					}
				}
			});
		});
	});
</script>




<div class="container">

  <h2>회원 가입</h2>
  <form class="form-horizontal" id = "form">
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">ID:</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" id="ID" placeholder="Enter email" name="email">
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Password:</label>
      <div class="col-sm-5">          
        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
      </div>
    </div>
    
    <!-- 이미지 div -->
    <div id="getimage">
	</div>
    <input type="hidden" id="key" name="key">
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">입력</label>
      <div class="col-sm-5">          
        <input type="text" class="form-control" id="val" placeholder="Enter password" name="val">
      </div>
    </div>
    
    
  
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <input type="button" class="btn btn-default" value = "전송" id = "btn">
      </div>
    </div>
  </form>
</div>
</div>


</body>
</html>