<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>buyer/buyerList.jsp</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/paging.js"></script>
<script type="text/javascript" src = "<%=request.getContextPath() %>/resources/js/jquery.form.min.js"></script>
</head>
<style>
#search{
	width: 350px;
}

</style>
<body>

<table class = 'table'>
	<thead>
		<tr>
			<th>거래처ID</th>
			<th>거래처명</th>
			<th>대표명</th>
			<th>상품 분류명</th>
			<th>거래은행</th>
			<th>주소</th>
			<th>전화번호</th>
			<th>메일</th>
		</tr>
	</thead>
	
	<tbody>
		
	</tbody>

	<tfoot>
	<tr>
		<td colspan="8">
			<div id = "pagingArea">
			</div>
		</td>
	</tr>
	</tfoot>
</table>

<div id = "searchUI">
	<input type = "text" name = "searchWord" placeholder="거래처명 혹은 대표자 이름으로 검색하세요" id = "search"/>
	<input type = "button" id = "searchBtn" value = "검색">
</div>

<input type = "button" value = "거래처 등록"  id = "insertBuyer">

<form id = "searchForm">
	<input type = "hidden" name = "searchWord">
	<input type = "hidden" name = "page">
</form>





	
<!-- Modal -->
<div class="modal fade" id="buyerDetail" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">거래처 상세정보</h5>
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
	
	

</body>
<script type="text/javascript">


	function makeTds(buyer){
		
		return $("<tr>").data('id', buyer.buyerId).append(
			$("<td>").html(buyer.buyerId),
			$("<td>").html(buyer.buyerName),
			$("<td>").html(buyer.buyerCharger),
			$("<td>").html(buyer.lprod.lprodNm),
			$("<td>").html(buyer.buyerBank),
			$("<td>").html(buyer.buyerAdd1),
			$("<td>").html(buyer.buyerComtel),
			$("<td>").html(buyer.buyerMail)
		);
		
	}

	let tbody = $('tbody');
	let pagingArea = $("#pagingArea");
	
	
	let searchForm = $('#searchForm').paging({
			pagingArea : "#pagingArea",
	  		pageLink : ".pageLink",
	  		searchUI : "#searchUI",
	  		btnSelector : "#searchBtn",
	  		pageKey : "page",
	  		pageParam : "page"
	}).ajaxForm({
		
		dataType : "json",
		success : function(pagingVO){
			let buyerList = pagingVO.dataList;
			tbody.empty();
			pagingArea.empty()
			let trs = [];
			
			if(buyerList){
				$(buyerList).each(function(idx, buyer){
					trs.push(makeTds(buyer));
				})
				
			}else{
				trs.push($("<tr>").append(
					$("<td>").attr("colspan",8).html("조건에 맞는 결과가 없음")
				))
			}
			tbody.append(trs);		
			pagingArea.html(pagingVO.pagingHTML);
			
		}
	}).submit();
	
	//----------------------------
	$(function(){
	let exampleModal = $('#buyerDetail').modal({
		show : false
	}).on('shown.bs.modal', function (event) {
		
		let tr = event.relatedTarget;
		let buyerId = $(tr).data('id');
		data = {
			'buyerId' : buyerId
		}
		
		$.ajax({
			url : '${pageContext.request.contextPath}/buyer/buyerDetail.do',
			data : data,
			dataType : "html",
			success : function(resp) {
				$('.modal-body').html(resp)
			},
			error : function(errorResp) {

			}

		});
		
	});
	
	
	let trTags = $('tbody').on('click','tr', function(){
		let buyerId = $(this).data('id');
		exampleModal.modal('show',this);
		
	}).css("cursor","pointer")
	
	
	})
	
	
	$('#insertBuyer').on('click',function(){
		location.href = "${pageContext.request.contextPath}/buyer/buyerInsert.do";
	})


</script>

<jsp:include page="/includee/footer.jsp"></jsp:include>
</html>