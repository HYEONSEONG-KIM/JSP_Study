<%@page import="kr.or.ddit.vo.ZipVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<link rel = "stylesheet" href = "//cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
<script type="text/javascript" src = "//cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#table_id').DataTable();
		
	})
</script>

</head>
<body>
<%
	List<ZipVO> list = (List)request.getAttribute("zipList");
	String pattern = "<td>%s</td><font></font>"	;
%>
<table id="table_id" class="display">
    <thead>
        <tr>
            <th>zipcode</th>
            <th>sido</th>
            <th>gugun</th>
            <th>dong</th>
        </tr>
    </thead>
    
   
    
    
   
</table>
<script type="text/javascript">

	$(function(){
		$('#table_id').DataTable( {
			serverSide : true,
			
			draw : 1,
			
		
	    
	} );
	})

	
</script>
</body>
</html>