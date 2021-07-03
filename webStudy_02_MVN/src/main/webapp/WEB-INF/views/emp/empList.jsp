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
	#par{
		display: flex;
		flex-direction: row;
		width:100%;
		
	}
	#tables{
		margin-left: 100px;
	}
	
	
</style>
</head>
<body>
<div id = "par">
	<div id = "tree"></div>

	<div id= "tables">
			<table class = "table">
			
			</table>
			<div id = "btn"></div>
	</div>
</div>

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
					let tr =$("<tr>")
					tds.push(tr); 
					let th = $("<th>").html(name)
					tds.push(th);
					let input = $("<input type = 'text'>").val(resp[name])
					let td = $("<td>").append(input)
					tds.push(td); 
				}
				let modify = $("<input type = 'button' value = '수정' id = 'modify'> ");
				let deletes = $("<input type = 'button' value = '삭제' id = 'delete'>");
// 				detail.html(tds);
				$('.table').html(tds);
				$('#btn').append(modify)
				$('#btn').append(deletes)
			},
			error : function(errorResp) {

			}

		});
		
		
	})
</script>
</body>
</html>
<jsp:include page="/includee/footer.jsp"></jsp:include>