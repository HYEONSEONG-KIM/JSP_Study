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
	DB로 부터 row데이터 조회후 모든 property value에 조회 날짜를 추가
	
	<%

	%>
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
				<input type = "text" name= "search"/>
				<input type = "submit" value = "검색"/>
			</td>
		</tr>
	</tfoot>
</table>
<script type="text/javascript">
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
	
	function ajaxFunc(){
		$.ajax({
			url : "<%=request.getContextPath()%>/11/jdbcDesc.do",
			dataType : "json",
			success : function(resp) {
				
				value = ""
				tbody.empty();
				let trs = [];
				$.each(resp,function(i,v){
					trs.push($("<tr>").append(makeTdFromData(v)));
				/* 	value += "<tr>"
					value += "<td>"+ v.property_name + "</td>"
					value += "<td>"+ v.property_value + "</td>"
					value += "<td>"+ v.description +"</td>"
					value += "</tr>" */
				})
				
				tbody.append(trs);
			},
			error : function(errorResp) {
			}
		});
	}
	ajaxFunc();
	
</script>
</body>
</html>













