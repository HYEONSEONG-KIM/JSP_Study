<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="Refresh" content="120"> -->
<title>06/sessionTimer.jsp</title>
<script type="text/javascript" src = "<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src = "<%=request.getContextPath() %>/resources/js/custom.js"></script>
<script type="text/javascript">
	

	$(function(){
			let sessionTime = <%=session.getMaxInactiveInterval() %>;
			message = $('#messageArea');
			yesBtn = $('#yesBtn');
			noBtn = $('#noBtn');
			message.hide()
			
			
			let element = $("#timerArea").sessionTimer({
				timeout : sessionTime ,
				url : "<%=request.getContextPath()%>/sessionExtend",
				yesBtn : yesBtn,
				noBtn : noBtn,
				message : message
			});
		
	});
	
</script>
</head>
<body>
<h4>세션 타이머</h4>

<%=session.getId()%> : <%=session.getMaxInactiveInterval() %>
<h4 id = "timerArea"></h4>
1. 1초마다 출력되는 시간을 디스카운트
2. 1분 남은 시점에 메세지를 출력
 세션 연장 여부 확인
3. 세션 연장을 선택한 경우, 
	1) 타이머 리셋
	2) 세션 연장을 위한 새로운 요청 발생(비동기 - /sessionExtend, body가 없는 응답-head)
 <hr>
	<div id = "messageArea">
		세션을 연장하겠습니까?
		<input type = "button" id = "yesBtn" value = "예"/>
		<input type = "button" id = "noBtn" value = "아니오"/>
	</div>
	
 <%-- <script type="text/javascript">
	
	let basicSecond = 61;<%=session.getMaxInactiveInterval() -1%>;
	timer = $("#timerArea")
	message = $('#messageArea')
	message.hide()
	function sessionExtend(){
		$.ajax({
			url : "<%=request.getContextPath()%>/sessionExtend",
			method : "head",
			success : function(resp) {

			},
			error : function(errorResp) {

			}

		});
	}
	
	
	setInterval(function(){
		if(basicSecond < 60){
			message.show()
		}else{
			message.hide()
		}
		
		$('#yesBtn').on('click',function(){
			sessionExtend();
			basicSecond = 120;
		})
		$('#noBtn').on('click',function(){
			message.hide()
		})
		
		let min = parseInt(basicSecond / 60);
		let second = basicSecond - min*60
		secondStr = second
		if(second < 10){
			secondStr = '0' + second
		}
		timer.empty()	
		timer.append(min + ":" + secondStr);
		--basicSecond;
		
		if(basicSecond < 1){
			basicSecond = 120;
		}
	}, 1000);
	 
	

</script>  --%>
</body>
</html>











