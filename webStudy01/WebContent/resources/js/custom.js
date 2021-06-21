/**
 * 
 */
// 엘리먼트 없이 가능
$.customAlert = function(message){
	alert(message);
}

// 엘리먼트 없이 불가능
$.fn.sessionTimer = function(obj){
	let a = this;
	const TIMEOUT = obj.timeout;
	const AJAXURL = obj.url;
	const YES = obj.yesBtn;
	const NO = obj.noBtn;
	const MSG = obj.message;
	flag = true;
	console.log(YES);
	
	let setTime = TIMEOUT;
	
	setInterval(function(){
		min = parseInt(setTime / 60);
		sec = setTime - (min*60);
		
		if(sec < 10){
			sec = "0" + sec;
		}
		
		a.html(min + " : " + sec);
		
		if(setTime > 60 || !flag){
			MSG.hide();
		}else if(setTime <= 60 && flag){
			MSG.show();
		}
		
		
		// 비동기(url)
		YES.on('click',function(){
			
			$.ajax({
				url : AJAXURL,
				method : "head",
				success : function(resp) {
		
				},
				error : function(errorResp) {
		
				}
		
			});
			setTime = TIMEOUT;
			
		})
		
		NO.on('click',function(){
			flag = false;
			
		})
		
		
		setTime--;
		
		if(setTime ==0){
			setTime = TIMEOUT;
			flag = true;
		}
		
	}, 1000);
	return this;
}




/*let basicSecond = 61;<%=session.getMaxInactiveInterval() -1%>;
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
}*/


/*setInterval(function(){
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
 
*/