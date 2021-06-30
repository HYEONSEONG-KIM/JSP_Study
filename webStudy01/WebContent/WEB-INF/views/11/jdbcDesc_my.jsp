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
				<input type = "text" name= "search" id = "search"/>
				<input type = "submit" value = "검색"/>
			</td>
		</tr>
	</tfoot>
</table>
<script type="text/javascript">
	function makeTdFromData(propVO,search){
	
		
		
		console.log(search)
		let tds = [];
		var flag = false;
		if(search != null){
			for(let propName in propVO){
				str = propVO[propName]
				if(str != null){
				if(str.indexOf(search) != -1){
					flag = true;
					if(flag){
						break;
					}
				}
				}
			}
		}else{
			
			flag = true;
		}
	
		
		
		if(flag){
			for(let propName in propVO){
				
				console.log(propName)
				let td = $("<td>").html(propVO[propName])
				tds.push(td); 
			}
			return tds;
		}else{
			return
		} 
	}
	
	let tbody = $("table>tbody");
	
	function ajaxFunc(search){
		$.ajax({
			url : "<%=request.getContextPath()%>/11/jdbcDesc.do",
			dataType : "json",
			success : function(resp) {
				
				value = ""
				tbody.empty();
				let trs = [];
				$.each(resp,function(i,v){
					trs.push($("<tr>").append(makeTdFromData(v)));
				})
					localStorage.setItem('DBProperty', JSON.stringify(resp));
				
				tbody.append(trs);
			},
			error : function(errorResp) {
			}
		});
	}
	ajaxFunc();
	
	let searchInput = $('#search');
	
	 $("[type = 'submit']").on('click',function(){
		info = JSON.parse(localStorage.getItem("DBProperty"));
		val = searchInput.val();
		
	 	tbody.empty(); 
	 	let trs = [];
		$.each(info,function(i,v){
			trs.push($("<tr>").append(makeTdFromData(v,val)));
		})
		tbody.append(trs);
	}) 
	/* $("[name='search']").on('keyup',function(){
		value = $(this).val();
		ajaxFunc(value);
	}) */
</script>
</body>
</html>













