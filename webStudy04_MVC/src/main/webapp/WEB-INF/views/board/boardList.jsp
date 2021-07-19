<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/board/boardList.jsp</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<script type="text/javascript" src = "${cPath}/resources/js/paging.js"></script>
<style>

.input-group input{
	margin-left : 10px;
}

span{
	margin-right: 5px;
}
#paging{
text-align: center;
}

</style>
</head>
<body>
	<div id = "searchUI">
	<div class="input-group mb-3">
		<select name = "searchType" class="form-select col-sm-3">
			<option value>검색 항목을 선택하세요</option>
			<option value = "title">제목</option>
			<option value = "writer">작성자</option>
			<option value = "content">내용</option>
		</select>
		
		<input type = "text" name = "searchWrod" class="form-control col-sm-3">
		<input type = "date" name ="startDate"/>
		<input type = "date" name ="endDate"/>
		
		<input type = "button" id = "searchBtn" value = "검색" class="btn btn-primary"/>
		</div>
	</div>
	
	<table class = "table">
		<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>신고수</th>
			<th>추천수</th>
			</tr>
		</thead>
		
		<tbody>
			<c:set var ="boardList" value="${paging.dataList}"></c:set>
			<c:choose>
				<c:when test="${empty boardList}">
					<tr>
						<td colspan="7">
							게시판이 비어있습니다
						</td>
					</tr>
				</c:when>
				
				<c:otherwise>
					<c:forEach items="${boardList}" var="board">
						<c:set var = "cnt" value = "${cnt + 1}"></c:set>
						<tr id = "${board.boNo}">
							<td>${cnt}</td>
							<td>${board.boTitle}</td>
							<td>${board.boWriter}</td>
							<td>${board.boDate}</td>
							<td>${board.boHit}</td>
							<td>${board.boRep}</td>
							<td>${board.boRec}</td>
						</tr>
					</c:forEach>
				
				</c:otherwise>
			
			</c:choose>
		</tbody>
	
		<tfoot>
			
			<tr>
				<td colspan="6" id = "paging">
					<div id = "pagingArea">
						${paging.pagingHTML }
					</div>
				</td>
				<td>
					<input type = "button" value = "글쓰기" id = "boardInsert" class="btn btn-success"/>
				</td>
			</tr>
			
		</tfoot>
	</table>
	<form id = "searchForm">
		<input type = "hidden" name = "searchType"/>
		<input type = "hidden" name = "searchWrod"/>
		<input type = "hidden" name ="startDate"/>
		<input type = "hidden" name ="endDate"/>
		<input type = "hidden" name = "page"/>
		
	</form>

</body>
<script type="text/javascript">
	$(function(){
		
		$("[name='searchType']").val("${paging.simpleSearch.searchType}");
		$("[name='searchWrod']").val("${paging.simpleSearch.searchWrod}");
		
		$("#searchForm").paging();
		
		$('tbody').on('click', 'tr[id]', function(){
			let boNo = $(this).attr('id');
			location.href = "${cPath}/board/boardSelect.do?boNo=" + boNo;
			
		}).css("cursor", "pointer");
		
		$('#boardInsert').on('click', function(){
			location.href = "${cPath}/board/boardInsert.do";
		})
	})

</script>
<jsp:include page="/includee/footer.jsp"></jsp:include>
</html>