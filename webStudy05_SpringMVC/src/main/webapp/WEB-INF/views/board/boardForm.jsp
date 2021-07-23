<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<script type="text/javascript"
	src = "${cPath}/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.min.js"></script>
</head>
<body>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}")
	</script>
	
</c:if>

	<form:form commandName="freeboard" id="boardForm" method="post" enctype="multipart/form-data">
		<input type = "hidden" name = "boNo" value = "${freeboard.boNo}">
		<input type = "text" name = "boParent" value = "${freeboard.boParent }" placeholder="답글 작성시 상위글번호">
		<table class='table col-sm-9'>

			<tr>
				<th class="col-sm-2">제목</th>
				<td><input type="text" name="boTitle" required
					value="${freeboard.boTitle}" class="form-control col-sm-6"/>
					
					<form:errors path="boTitle" element="label" id="boTitle-error" cssClass="error" for="boTitle"></form:errors>
					</td>
			</tr>

			<tr>
				<th>작성자명</th>
				<td><input type="text" name="boWriter" required
					value="${freeboard.boWriter}" class="form-control col-sm-6"/>
					<form:errors path="boWriter" element="label" id="boWriter-error" cssClass="error" for="boWriter"></form:errors>
					</td>
			</tr>

			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="boPass" required
					 class="form-control col-sm-6"/>
					 <form:errors path="boPass" element="label" id="boPass-error" cssClass="error" for="boPass"></form:errors>
				</td>
			</tr>

			<tr>
				<th>메일</th>
				<td><input type="text" name="boMail"
					value="${freeboard.boMail}" class="form-control col-sm-6"/>
					<form:errors path="boMail" element="label" id="boMail-error" cssClass="error" for="boMail"></form:errors>
					</td>
			</tr>

			<tr>
				<th>IP주소
				</th>
				<td><input type="text" name="boIp" required
					value="${pageContext.request.remoteAddr}" class="form-control col-sm-6"  readonly/>
					<form:errors path="boIp" element="label" id="boIp-error" cssClass="error" for="boIp"></form:errors>
					</td>
			</tr>

			<tr>
				<th>첨부파일</th>
				<td>
					<div class="input-group">
						<input type="file" name="boFiles" /> <span
							class="plusBtn btn btn-primary">+</span>
					</div>
				</td>
			</tr>
			
			<c:set var="attatchList" value="${ freeboard.attatchList}"></c:set>
			<c:if test="${not empty attatchList }">
			<tr>
				<th>기존 파일</th>
				<td>
					<c:forEach var="attatch" items="${ attatchList}">
						<span data-attno = "${attatch.attNo}">${attatch.attFilename }
							<input type = "button" value = "-" class = "delBtn btn btn-warning">
						</span>
						
					</c:forEach>
				</td>
			</tr>
			</c:if>
			
			<tr>
				<th>내용</th>
				<td><textarea id = "boContent" name="boContent" cols="50" rows="10"
						value="${freeboard.boContent}" class="form-control"></textarea> 
						<form:errors path="boContent" element="label" id="boContent-error" cssClass="error" for="boContent"></form:errors>
						</td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="저장"
					class="btn btn-success" /> <input type="reset" value="취소"
					class="btn btn-info" /> <input type="button" value="목록으로"
					id="boardList" class="btn btn-secondary" /></td>
			</tr>

		</table>
		<div id = "deleteFile">
		
		</div>
	</form:form>

	<div class="input-group" style="display: none;" id="attatchSource">
		<input type="file" name="boFiles" /> <span
			class="plusBtn btn btn-primary">+</span> <span
			class="minusBtn btn btn-danger">-</span>
	</div>
</body>
<script type="text/javascript">
	
	/* const ATTATCHSOURCE = $('.attatchSource');

	ATTATCHSOURCE.on('click', '.addFile',function(){
		let inputTag = ATTATCHSOURCE.clone();
		let delBtn = inputTag.append('<input type= "button" value = "-" class = "delFile"/>');
		$('#fileArea').append(inputTag);
		
	}) */
		
		CKEDITOR.replace('boContent',{
			filebrowserImageUploadUrl : "${cPath}/board/uploadImage.do?type=images"
			
		});
	
	   const ATTATCHSOURCE = $("#attatchSource");
	
	   $("table").on("click", ".plusBtn", function(){
	      let newTag = ATTATCHSOURCE.clone();
	      let divTag = $(this).parents(".input-group:first");
	      divTag.after(newTag);
	      newTag.show();
	   });
	   $("table").on("click", ".minusBtn", function(){
	      let divTag = $(this).parents(".input-group:first");
	      divTag.remove();
	   });
	   
	 
	   $('table').on('click','.delBtn',function(){
		   let delFile = $(this).parent();
		   let attNo = delFile.data('attno');
		   let delFileInput = $('<input type = "text" name = "delAttrNos">').val(attNo);
		   $('#deleteFile').append(delFileInput);
		   delFile.hide();
	   })
	
	
	$('#boardForm').validate();
	$('#boardList').on('click', function() {
		location.href = "${cPath}/board/boardList.do";
	})
</script>
<jsp:include page="/includee/footer.jsp"></jsp:include>
</html>







