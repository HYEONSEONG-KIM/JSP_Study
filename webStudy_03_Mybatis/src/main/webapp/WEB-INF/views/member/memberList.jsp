<%@page import="kr.or.ddit.vo.pagingVO"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>

<style>
	table, td, th{
		border : 1px solid lightgray;
		border-collapse: collapse;
		border-spacing: 0px;
	}
	td, th{
		width: 200px;
		height: 50px;
	}
</style>
</head>
<body>
<h1>회원 목록 조회</h1>

	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
		   <th>지역</th>
		   <th>휴대폰</th>
		   <th>이메일</th>
		   <th>마일리지</th>
		    
		</tr>
		<tbody>
			<c:set var="memberList" value="${paging.dataList}"></c:set>
		<c:choose>
			<c:when test="${empty memberList}">
				<tr>
					<td colspan="6">조건에 맞는 회원이 없습니다</td>
				</tr>
			</c:when>
			
			<c:otherwise>
				<c:forEach items ="${ memberList}" var="member" >
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
		<c:if test="${empty memberList}">
			<tr>
				<td colspan="6"></td>
			</tr>
		</c:if>

		</tbody>
		<tfoot>
			<tr>
				<td colspan="6">
					${requestScope.paging.pagingHTML}
					
					<div id = "searchUI">
						<select name = "searchType">
							<option value>전체 </option>
							<option value = "name">이름</option>
							<option value = "address">지역</option>
							<option value = "hp">휴대폰</option>
						</select>
						<input type= "text" name = "searchWord">
						<button type = "button" id = "searchBtn">검색</button>
					</div>
				</td>
			</tr>
		</tfoot>
	
	</table>	
	
	<form id = "searchForm">
		<input type = "text" name = "searchType"/>
		<input type = "text" name = "searchWord"/>
		<input type = "text" name = "page"/>
	</form>
	
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
	
	
	
	
	
	
	
	
	
<script type="text/javascript">
	$(function(){
		$("[name='searchType']").val("${paging.simpleSearch.searchType}");
		$("[name='searchWord']").val("${paging.simpleSearch.searchWrod}");
		$(".pageLink").on("click",function(event){
			event.preventDefault();
			let page = $(this).data("page");
			searchForm.find('[name="page"]').val(page);
			searchForm.submit();
			return false;
		}).css("cursor","pointer");
		
			let searchForm = $('#searchForm');
		
			let searchUI = $("#searchUI").on('click','#searchBtn',function(){
				let inputs = searchUI.find(":input[name]");
				$(inputs).each(function(idx, input){
					let name = this.name;
					let value = $(this).val();
					searchForm.find("[name='"+name+"']").val(value)
				})
				searchForm.submit();
			});
		
		
		
		
			let exampleModal = $("#exampleModal").modal({
				show : false
			}).on('show.bs.modal', function(event){
				console.log(event.relatedTarget)
				let trTag = event.relatedTarget;
				if(!trTag){
					return false;
				}
				let mem_id = trTag.id;
				 $.ajax({
					url : "${pageContext.request.contextPath}/member/memberView.do",
					data : {
						'who' : mem_id
					},
					dataType : "html",
					success : function(resp) {
						exampleModal.find(".modal-body").html(resp);
					},
					error : function(errorResp) {

					}

				}); 
				
			}).on('hidden.bs.modal',function(){
				exampleModal.find(".modal-body").empty();
			});
		
			$("tbody").on("click","tr[id]", function(){
				let mem_id = this.id;
				// json구조 사용하지 말것
				let url = "${pageContext.request.contextPath}/member/memberView.do";
				let data = {
						"who" : mem_id
				}
				
				exampleModal.modal('show', this);
				
			}).css("cursor", "pointer");
		
	})
</script>
<jsp:include page="/includee/footer.jsp"></jsp:include>
</body>
</html>









