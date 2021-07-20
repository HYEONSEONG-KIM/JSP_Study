<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/board/boardSelect</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<script type="text/javascript" src = "<%=request.getContextPath() %>/resources/js/jquery.form.min.js"></script>
</head>
<c:if test="${not empty message}">
	<script type="text/javascript">
		alert("${message}");
	</script>
	<c:remove var="message" scope="session"/>
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
	<form id = "incrementForm" action="${cPath}/board/increment.do">
	
		<input type = "hidden" name = "bono" id = "bono" value =${freeboard.boNo}>
		<input type = "hidden" name = "type" id = "type">
		
	</form>
	<form name = "deleteForm" action="${cPath}/board/boardDelete.do" method="post">
		<input type = "hidden" name = "boNo" value = "${freeboard.boNo}" required>
		<input type = "hidden" name = "boPass" required>
		
	</form>

</body>

<script type="text/javascript">

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