<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>

<table class="table table-striped">
	<thead class="thead-dark">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>지역</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="memberList" value="${pagingVO.dataList }" />
		<c:choose>
			<c:when test="${empty memberList }">
				<tr>
					<td colspan="6">조건에 맞는 회원이 없음.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${memberList }" var="member">
					<tr id="${member.memId}">
						<td>${member.memId}</td>
						<td>${member.memName}</td>
						<td>${member.memAdd1}</td>
						<td>${member.memHp}</td>
						<td>${member.memMail}</td>
						<td>${member.memMileage}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<div id="pagingArea" class="d-flex justify-content-center">
					${pagingVO.pagingHTML }
				</div>
				<div id="searchUI" class="form-inline mt-3 d-flex justify-content-center pb-2" style="border: 5px solid green;">
					<h4 class="col-12 text-center">Search UI</h4>
					<select name="searchType" class="form-control mr-2">
						<option value>전체</option>
						<option value="name">이름</option>
						<option value="address">지역</option>
						<option value="hp">휴대폰</option>
					</select>
					<input class="form-control mr-2" type="text" name="searchWord" onchange="$(this).siblings('#searchBtn').click();"/>
					<button type="button" id="searchBtn"  class="btn btn-success">검색</button>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm"  style="border: 5px solid red;">
	<h4>Hidden Form</h4>
	<input type="text" name="searchType" placeholder="search type"/>
	<input type="text" name="searchWord" placeholder="search word"/>
	<input type="text" name="page" placeholder="page number"/>
</form>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">상세 정보</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<script src="${pageContext.request.contextPath }/resources/js/paging.js"></script>
<script type="text/javascript">
	$(function(){
		$("[name='searchType']").val("${pagingVO.simpleSearch.searchType}");
		$("[name='searchWord']").val("${pagingVO.simpleSearch.searchWord}");
		
		$("#searchForm").paging();
		
		let exampleModal = $("#exampleModal").modal({
			show:false
		}).on('show.bs.modal', function(event){
			console.log(event.relatedTarget);
			let trTag = event.relatedTarget;
			if(!trTag) return false;
			let mem_id = trTag.id;
			$.ajax({
				url : "${pageContext.request.contextPath }/member/memberView.do",
				data : {
					who:mem_id
				},
				dataType : "html",
				success : function(resp) {
					exampleModal.find(".modal-body").html(resp);
				}
			});
		}).on('hidden.bs.modal', function(){
			exampleModal.find(".modal-body").empty();
		});
		$("tbody").on("click", "tr[id]" ,function(){
			let mem_id = this.id;
<%-- 			location.href = "<%=request.getContextPath() %>/member/memberView.do?who="+mem_id; --%>
			exampleModal.modal('show', this);
		}).css("cursor", "pointer");
	});
</script>
<jsp:include page="/includee/footer.jsp" />
</body>
</html>






