<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/board/boardSelect</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<script type="text/javascript" src = "${cPath}/resources/js/jquery.form.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.min.js"></script>
<style type="text/css">
	#replyForm td, #replyForm th{
		padding : 6px;
	}	

</style>
</head>
<c:if test="${not empty message}">
	<script type="text/javascript">
		alert("${message}");
	</script>
	
</c:if>
<body>
<table class = "table">
		<tr>
			<th>글 번호</th>
			<td>${freeboard.boNo}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${freeboard.boTitle}</td>
		</tr>
		<tr>
			<th>작성자명</th>
			<td>${freeboard.boWriter}</td>
		</tr>
		<tr>
			<th>IP주소</th>
			<td>${freeboard.boIp}</td>
		</tr>
		<tr>
			<th>메일</th>
			<td>${freeboard.boMail}</td>
		</tr>
		
		<tr>
			<th>내용</th>
			<td>${freeboard.boContent}</td>
		</tr>
		<tr>
			<th>파일갯수/파일 사이즈</th>
			
			<td>
			<c:set var="attatchList" value="${freeboard.attatchList}"></c:set>
				<c:choose>
					<c:when test="${empty attatchList }">
						파일이 존재하지 않습니다
					</c:when>
					
					<c:otherwise>
						<c:forEach var="attatch" items="${attatchList}">
							<c:set var="i" value="${i + 1}"></c:set>
							<c:if test="${i ne 1}">
								,
							</c:if>
							<c:url value="/board/download.do" var="downloadURL">
								<c:param name="what" value="${attatch.attNo}"></c:param>
							</c:url>
							<a href="${downloadURL}">${attatch.attFilename}</a>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</td>
		
		</tr>
		<tr>
			<th>작성일</th>
			<td>${freeboard.boDate}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${freeboard.boHit}</td>
		</tr>
		<tr>
			<th>신고수</th>
			<td><span id="REPORT">${freeboard.boRep}</span>
				<input type = "button" value ="신고하기" class = "incrementBtn" data-type = "REPORT">
			</td>
		</tr>
		<tr>
			<th>주천수</th>
			<td>
			<span id = "RECOMMEND">${freeboard.boRec}</span>
			<input type = "button" value ="추천하기" class = "incrementBtn" data-type = "RECOMMEND">
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<c:url value = "/board/boardInsert.do" var = "insertURL">
				<c:param name = "boParent" value = "${freeboard.boNo }"></c:param>
			</c:url>
				<input type = "button" value = "답글쓰기" class = "controlBtn" id ="reply"
					data-gopage="${insertURL}">
				
			<c:url value = "/board/boardUpdate.do" var = "updateURL">
				<c:param name = "boNo" value = "${freeboard.boNo }"></c:param>
			</c:url>	
				<input type = "button" value = "수정하기" class = "controlBtn"
					data-gopage="${updateURL}">
					
				<input type = "button" value = "삭제하기"  id = "deleteBtn">
			</td>
		
		</tr>
		

		
	</table>
	<div id= "replyArea">
	</div>
	<div id = "replyPagingArea">
	</div>
	

	
	
	
	<form id = "replyrForm">
		
		<table>
		<tr>
			<th>작성자</th>
			<td>
				<input type = "text" name = "repWriter" required>
			</td>
			<th>비밀번호</th>
			<td>
				<input type = "text" name = "repPass" required/>
			</td>
		</tr>
		
		<tr>
			<th>메일</th>
			<td colspan="3">
				<input type = "text" name = "repMail"/>
			</td>
		</tr>
		
		<tr>
			<th>댓글 내용</th>
			<td colspan="3">
				<textarea rows="5" cols="80" name = "repContent" required></textarea><br>
			</td>
		</tr>
			<td colspan="2">
				<input type = "submit" value = "댓글 등록"/>
			</td>
		</table>
		<input type = "hidden"  name = "boNo" value = "${freeboard.boNo }"/>
	</form>
	
	
	<form>
		<input type = "hidden" name = "page">
		<input type = "hidden"  name = "boNo" value = "${freeboard.boNo }"/>
	</form>
	
	
	
	<form id = "incrementForm" action="${cPath}/board/increment.do">
	
		<input type = "hidden" name = "bono" id = "bono" value =${freeboard.boNo}>
		<input type = "hidden" name = "type" id = "type">
		
	</form>
	
	<form name = "deleteForm" action="${cPath}/board/boardDelete.do" method="post">
		<input type = "hidden" name = "boNo" value = "${freeboard.boNo}" required>
		<input type = "hidden" name = "boPass" required>
		
	</form>
	
	<form id = "replyView" action="${cPath}/reply/">
		
	
	</form>
	

	<div style="display:none" class = "replyList">
		<table>
			<tr>
				<th>작성자</th>
				<td class = "replyWriter"></td>
				<th>작성일</th>
				<td class = "replyDate"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3" class = "replyContent"></td>
			</tr>
			<tr>
				<td colspan="4">
					<input type = "button" class = "btn btn-primary replyModify" value = "수정"/>
					<input type = "button" class = "btn btn-danger replyRemove" value = "삭제"/>
				</td>
			</tr>
		</table>
		<hr>
	</div>

</body>

<script type="text/javascript">
	
	const REPLYFORM = $('.replyList');
	let replyArea = $('#replyArea');
	let replyPagingArea = $('#replyPagingArea')
	
	
	function replyList(page){
		
		let url = "${cPath}/reply/";
		let boNo = ${freeboard.boNo};
		let data = {
				'boNo' : boNo,
				'page' : page
		}
		 $.ajax({
			url : url,
			data : data,
			dataType : "json",
			success : function(resp) {
				
				replyPagingArea.empty();
				
				let reply = []
				let tables = []
				$(resp.dataList).each(function(idx, value){
					let newReplyForm = REPLYFORM.clone();
					newReplyForm.attr('data-repNo', value.repNo);
					newReplyForm.find('.replyWriter').text(value.repWriter);
					newReplyForm.find('.replyDate').text(value.repDate);
					newReplyForm.find('.replyContent').text(value.repContent);

					newReplyForm.show();
					tables.push(newReplyForm);
				})
					replyArea.append(tables);
					replyPagingArea.append(resp.pagingHTMLPlus);
				}
			});
	}
	
	replyList();
	
	replyPagingArea.on('click','.nextReply', function(){
		let replyPage = $(this).data('page');
		replyList(replyPage);
		
	})
	
	
	let replyrForm = $('#replyrForm');
	let validateReply = replyrForm.validate();
	
	replyrForm.ajaxForm({
		method : 'post',
		dataType : 'json',
		url : '${cPath}/reply/',
		success : function(resp){
			if(resp = "OK"){
				replyList();
			}else{
				alert("댓글 등록 실패...")	
			}
		}
	});

	replyArea.on('click', '.replyRemove' ,function(){
 			let repNo = $(this).parents('div').eq(0).data('repno');
 			let repPass = prompt("댓글 비밀번호 입력");
 			let data = {
 					'repNo' : repNo,
 					'repPass' : repPass
 			}
//  			let json = JSON.stringify(data);
 			console.log(data)
 			$.ajax({
				url : "${cPath}/reply/" + repNo + "/" + repPass,
				method : "delete",
				dataType : "json",
				success : function(resp) {
					if(resp == "OK"){
						replyList();
					}else if(resp == "INVALIDPASSWORD"){
						alert("비밀번호 오류")
					}else{
						alert("댓글 삭제 실패...")
					}
				}

			});
		
 	}) 


 	
 	
	$('#deleteBtn').on('click',function(){
		let inputPass = prompt("비밀번호 입력");
		document.deleteForm.boPass.value = inputPass;
		document.deleteForm.submit();
		
	})

	let incrementForm = $('#incrementForm')
					.ajaxForm({
						method : 'post',
						dataType : 'json',
						success : function(resp){
							if(resp.result != "OK")
								return false;
							let id = '#' + resp.type;
							let before = $(id).text();
							let after = parseInt(before) + 1;
							$(id).empty().text(after);
						}
					
					});

	$('.incrementBtn').on('click',function(){
		let type = $(this).data('type');
		$('#type').val(type);
		 incrementForm.submit(); 
	})
	
	$('.controlBtn').on('click',function(){
		let url = $(this).data('gopage');
		if(url != null)
			location.href = url;
	})
	
	
	
/* 
	$('#deleteBtn').on('click',function(){
		
		let inputPass = prompt("비밀번호 입력");
		let url = "${cPath}/board/boardDelete.do";
		let data = {
				'boPass' : inputPass,
				'boNo' : '${freeboard.boNo}'
		} 
		
		$.ajax({
			url : url,
			data : data,
			method : "post",
			dataType : "json",
			success : function(resp) {
				console.log(resp)
				if(resp.result == "OK"){
					alert("삭제되었습니다")
					location.href = "${cPath}/board/boardList.do";
				}else if(resp.result == "INVALIDPASSWORD"){
					alert("비밀번호가 일치하지 않습니다")
				}else{
					alert("서버오류...잠시 후 다시 시도해주세요")
				}
			}

		});
	}) */

	/* $(function(){
		$('#recommend').on('click', function(){
			let boNo = $(this).data('bono');
			let url = "${cPath}/board/recommend.do";
			let data = {
				'boNo' : boNo
			}
			let boRec = $('#boRec');
		 	$.ajax({
				url : url,
				data : data,
				dataType : "json",
				success : function(resp) {
					if(resp == boRec.text()){
						alert("추천업 실패...")
					}else{
						boRec.empty();
						boRec.text(resp);
					}
				}
			}); 
			
		})
	}) */

</script>
<jsp:include page="/includee/footer.jsp"></jsp:include>
</html>