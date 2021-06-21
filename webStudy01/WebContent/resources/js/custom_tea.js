/**
 * 
 */
// 엘리먼트 없이 가능
$.customAlert = function(message){
	alert(message);
}

$.timeFormat = function(time){
	// 1:59
	let min = Math.trunc(time / 60);
	let sec = time % 60;
	return min + " : " + sec;
}

// 엘리먼트 없이 불가능
$.fn.sessionTimer = function(obj){
	if(!obj || !obj.timeout){
		throw "타이머를 구현하기 위해 필수 파라미터가 필요함";
		return
	}
	const SPEED = 100;
	const TIMEOUT = obj.timeout;
	const AJAXURL = obj.url ? obj.url : '';
	const TIMERAREA = this;
	
	let timer = -1;
	let timerJob = null;
	let messageArea = null;
	let messageJob = null;
	
	function makeMessageArea(element){
		let messageArea = $("<div>").prop("id", "messageArea")
			.append(
					
					"세션을 연장하시겠습니까??"
					, $('<br>')
					,$('<input>').attr({
						"type" : "button",
						"id" : "yesBtn",
						"value" : "예"
					})
					,$('<input>').attr({
						"type" : "button",
						"id" : "noBtn",
						"value" : "아니오"
					})
			).on("click", "input", function(){
				let id = $(this).prop('id');
				if(id == "yesBtn"){
					init();
					$.ajax({
						url : AJAXURL,
						method : "head"
					});
				}
				$(this).parents("#messageArea").hide();
			})
			.hide();
		element.after(messageArea);
		return messageArea;
		/*<div id = "messageArea">
			세션을 연장하겠습니까?
			<input type = "button" id = "yesBtn" value = "예"/>
			<input type = "button" id = "noBtn" value = "아니오"/>
		</div>*/
	}
	
	
	function destroy(){
		if(timerJob != null) {
			clearInterval(timerJob);
			timerJob == null;
		}
		if(messageJob != null){
			clearTimeout(messageJob);
			messageJob = null;
		}
		if(messageArea != null){
			messageArea.remove();
			messageArea == null;
		}
		location.reload();
	}
	
	function init(){
		timer = TIMEOUT - 1;
		if(timerJob == null){
			timerJob = setInterval(function(){
				TIMERAREA.text($.timeFormat(timer--));
				if(timer<0){
					destroy();
				}
			},SPEED);
		}
		
		if(messageArea == null){
			messageArea = makeMessageArea(TIMERAREA);
		}
		
		if(messageJob == null){
			messageJob = setTimeout(function(){
				messageArea.show();
				messageJob = null;
			},(TIMEOUT - 60)*SPEED);
		}
	}
	
	init();

	return this;
}

