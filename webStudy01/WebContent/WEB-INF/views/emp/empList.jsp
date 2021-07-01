<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/includee/preScript.jsp"/>
<link rel = "stylesheet" href= "<%=request.getContextPath() %>/resources/js/fancytree/skin-win8/ui.fancytree.min.css">
<script type="text/javascript" src = "<%=request.getContextPath() %>/resources/js/fancytree/jquery.fancytree-all-deps.min.js"></script>
<style>
	table, td, th {
		border : 1px solid black;
	}
	td, th{
		width: 100px;
		height: 50px;
	}
</style>
</head>
<body>
<div id = "tree">

</div>


<table>
	<tr>
		<th>사원 번호</th>
		<th>이름</th>
		<th>직업</th>
		<th>mgr</th>
		<th>입사일자</th>
		<th>sal</th>
		<th>comm</th>
		<th>부서 번호</th>
		<th>부서이름</th>
	</tr>
	<tr id = "detailContent">
	</tr>
</table>
<script type="text/javascript">
	$('#tree').fancytree({
		source:{
			url : location.pathname,
			cache : true
		},
		 tooltip: function(event, data) {
			    return data.node.key ;
			  },
		lazyLoad: function(event, data){
		      var node = data.node;
		      console.log(node.key)
		      data.result = {
		    	url: location.pathname,
		        data: {mgr:node.key},
		        cache: false
		      };
		  }
	});
	
	$('#tree').on('click', '.fancytree-title', function(){
		let detail = $('#detailContent');
		detail.empty();
		empno = $(this).attr('title')
		let data = {
			"empno" : empno
		}
		
		$.ajax({
			url : "<%=request.getContextPath()%>/employee/empSelect.do",
			data : data,
			dataType : "json",
			success : function(resp) {
				let tds = [];
				for(let name in resp){
					let td = $("<td>").html(resp[name])
					tds.push(td); 
					console.log(resp[name])
				}
				detail.html(tds);
			},
			error : function(errorResp) {

			}

		});
		
		
	})
</script>
</body>
</html>