<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
	table{
			background: #fcfcfc;
			border-collapse: collapse;
		}
		th, td{
			border : 1px solid black;
		}
</style>
<meta charset="UTF-8">
<title>11/jdbcDesc.jsp</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<h4>JDBC(Java DataBase Connectivity)</h4>
<pre>
	: 데이터 베이스 프로그래밍 단계
	1. 벤더가 제공하는 드라이버를 찾고, 빌드패스에 추가
	2. 드라이버 클래스 로딩
	3. Connection 생성
	4. 쿼리 객체 생성
		- Statement(쿼리의 컴파일이 안되어있음)
		- preparedStatement(쿼리의 컴파일 되어있음)
		- CallableStatement
	5. 쿼리 실행 : DML
		- ResultSet execuetequery() : SELECT
		- int executeUpdate() : INSERT, DELETE, UPDATE
	6. 질의 결과 사용
	7. 자원 해제
</pre>
<table>
	<thead>
		<tr>
			<th>PROPERTY_NAME</th>
			<th>PROPERTY_VALUE</th>
			<th>DESCRIPTION</th>
		</tr>
	</thead>
	<tbody>
	
	</tbody>
	<tfoot>
		<tr>
			<td colspan = "3">
			<div id = "pagingArea">
				
			</div>
				<div id = "searchUI">
					<input type = "text" name= "search"/>
					<input type = "button" value = "검색" id = "searchBtn"/>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form id = "searchForm">
	<input type = "text" name ="search"/>
	<input type = "text" name ="page"/>
</form>
<script type="text/javascript">
	$(document).ajaxError(function(event, xhr, options, error){
		console.log(event);
		console.log(xhr);
		console.log(options);
		console.log(error);
		
	}).ajaxComplete(function(event, xhr, options){
		searchForm.find("[name='page']").val("");
		searchForm.get(0).reset();
	});
	

	function makeTdFromData(propVO){
		let tds = [];
		
		for(let propName in propVO){
				
			console.log(propName)
			let td = $("<td>").html(propVO[propName])
			tds.push(td); 
		}
		
		return tds;
	}
	
	let tbody = $("table>tbody");
	
	let searchUI = $("#searchUI").on('click','#searchBtn',function(){
		let inputs = searchUI.find(":input[name]");
		$(inputs).each(function(idx, input){
			let name = this.name;
			let value = $(this).val();
			searchForm.find("[name='"+name+"']").val(value)
		})
		searchForm.submit();
	});

	let pagingArea = $("#pagingArea").on("click", ".pageLink", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		searchForm.find("[name = 'page']").val(page);
		searchForm.submit();
	});
	
	let searchForm = $("#searchForm").on("submit",function(event){
		event.preventDefault();
		let url = this.action;
		let formData = new FormData(this);
/* 		let data = {};
		for( let key of formData.key()){
			data[key] = formData.get(key);
		} */
		let data = $(this).serialize();
		$.ajax({
			data : data,
			dataType : "json",
			success : function(resp) {
				let dataList = resp.dataList;
				
				value = ""
				tbody.empty();
				pagingArea.empty();
				let trs = [];
				$.each(dataList,function(i,v){
					trs.push($("<tr>").append(makeTdFromData(v)));
				})
				
				tbody.append(trs);
				pagingArea.html(resp.pagingHTML);
			}
		});
		return false;
	}).submit();
	
	
	

</script>
</body>
</html>













