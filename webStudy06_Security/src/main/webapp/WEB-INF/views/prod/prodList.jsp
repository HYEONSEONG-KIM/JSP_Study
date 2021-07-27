<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="kr.or.ddit.vo.pagingVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src = "${cPath}/resources/js/paging.js"></script>
<script type="text/javascript" src = "${cPath}/resources/js/jquery.form.min.js"></script>



<h3>SearchUI</h3>
	<div id = "searchUI">
		<label>분류 : </label>
		 <select name = "prodLgu">
		 <option value>--분류--</option>
			<c:choose>
				<c:when test="${empty prodLguList}">
					<option value>항목이 없습니다</option>
				</c:when>
			
				<c:otherwise>
					<c:forEach items="${prodLguList}" var="lguMap">
						<option value='${lguMap.get("LPROD_GU")}'>${lguMap.get("LPROD_NM")}</option>
					</c:forEach>
					
				</c:otherwise>
			</c:choose> 
			
			
		</select>
		 
		<label>거레처 : </label> 
		<select name = "prodBuyer">
		<option value>--거래처--</option>
			
				<c:forEach items="${buyerList}" var="buyer">
					<option class = ${buyer.buyerLgu } value = "${buyer.buyerId}">${buyer.buyerName}</option>
				</c:forEach>
			
		
		</select>
		
		<label>상품명 : </label> <input type = "text" name = "prodName">
		<input type = "button" value = "검색" id = "searchBtn"/>
	</div>
	
	<table class = "table">
		<thead>
		<tr>
			<th>상품명</th>
			<th>상품분류</th>
			<th>거래처</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>마일리지</th>
		</tr>
		</thead>
		<tbody>		
	<%-- 	<%
			if(prodList.size() == 0){
			%>
			
				<tr>
					<td colspan="6">
						조건에 맞는 검색 결과가 없습니다
					</td>
				</tr>
			<%
			
			}else{
				for(ProdVO vo : prodList){
		%>
				<tr>
					<td><%=vo.getProdName() %></td>
					<td><%=vo.getLprodNm() %></td>
					<td><%=vo.getBuyer().getBuyerName() %></td>
					<td><%=vo.getProdCost() %></td>
					<td><%=vo.getProdSale() %></td>
					<td><%=vo.getProdMileage() %></td>
				
				</tr>
		<%
				}
			}
		%> --%>
		</tbody>
		<tfoot>
		<tr>
			<td colspan="6" >
				<div id = "pagingArea">
				<%-- 	<%=paging.getPagingHTML()%> --%>
				</div>
			</td>
		</tr>
		
		</tfoot>
		
	</table>
				<input type = "button" value = "등록" id = "prodInsert"/>
	
<h3>Hidden From</h3>
<form id = "searchForm">
	<input type = "text" name = "prodLgu" >
	<input type = "text" name = "prodBuyer" >
	<input type = "text" name = "prodName" >
	<input type = "text" name = "page">
</form>
<script type="text/javascript" src = "${pageContext.request.contextPath }/resources/js/prod/prodForm.js"></script>
<script type="text/javascript">


	let buyerTag = $("select[name='prodBuyer']").val("${paging.detailSearch.prodBuyer}")
	$('select[name="prodLgu"]').othersSelect({
		buyerTag : buyerTag
	}).val("${paging.detailSearch.prodLgu}").change();
	
	

	
	$(function(){
		
		$(document).ajaxComplete(function(event, xhr, options){
			console.log(searchForm.get(0))
			searchForm.find('input[name="page"]').val("");
		}).ajaxError(function(event, xhr, options, error){
			console.log(event)
			console.log(xhr)
			console.log(options)
			console.log(error)
		})
		
	})
	
	
	function makeTrTag(prod){
		return $("<tr>").append(
				$("<td>").html(prod.prodName),
				$("<td>").html(prod.lprodNm),
				$("<td>").html(prod.buyer.buyerName),	
				$("<td>").html(prod.prodCost),
				$("<td>").html(prod.prodPrice),	
				$("<td>").html(prod.prodMileage)		
		).data("prod", prod);
	}
	
	
	 	let tbody = $("table>tbody").on('click', "tr" ,function(){
	 		let prod = $(this).data("prod");
	 		if(!prod){
	 			return false;
	 		}
	 		let prodId = prod.prodId;
	 		location.href = "${cPath}/prod/prodView.do?what=" + prodId;
	 	}).css('cursor', 'pointer');
	 	
	  	let pagingArea = $('#pagingArea');
	
	
	let searchForm = $("#searchForm").paging( {
				
		  		pagingArea : "#pagingArea",
		  		pageLink : ".pageLink",
		  		searchUI : "#searchUI",
		  		btnSelector : "#searchBtn",
		  		pageKey : "page",
		  		pageParam : "page"
		  		
		  
		  }).ajaxForm({
			  dataType : "json",
			  success : function(pagingVO){
				  tbody.empty();
				  pagingArea.empty();
				  let prodList = pagingVO.dataList;
				  let trTags = [];
				  
				  if(prodList){
					  
					$(prodList).each(function(idx, prod){
						trTags.push(makeTrTag(prod));
					})
					  pagingArea.html(pagingVO.pagingHTML);
					  
					  
				  }else{
					  trTags.push(
						$("<tr>").html(
							$("<td>").attr("colspan", 6).html("조건에 맞는 결과가 없음")	
						)	  
					  );
				  }
				  
				  tbody.append(trTags);
			  }
		  }).submit();
		  
	
		  
		  $('#prodInsert').on('click', function(){
			location.href = "${pageContext.request.contextPath }/prod/prodInsert.do";  
		  })
		  
		  
		  
		  
		  

</script>
















