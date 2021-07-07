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
<table id="table_id" class="display"><font></font>
    <thead><font></font>
        <tr><font></font>
            <th>우편번호</th><font></font>
            <th>시도</th><font></font>
            <th>구군</th><font></font>
            <th>동</th><font></font>
            <th>번지</th><font></font>
        </tr><font></font>
    </thead><font></font>
    
    
    <tbody><font></font>
    
    
    	<% for(ZipVO vo : list){
    		out.print("<tr><font></font>");
    		out.print(String.format(pattern,vo.getZipcode()));
    		out.print(String.format(pattern,vo.getSido()));
    		out.print(String.format(pattern,vo.getGugun()));
    		out.print(String.format(pattern,vo.getDong()));
    		out.print(String.format(pattern,vo.getBunji()));
    		out.print("</tr><font></font>");
    		
    	}
    	%>
       <!--  <tr><font></font>
            <td>Row 1 Data 1</td><font></font>
            <td>Row 1 Data 2</td><font></font>
        </tr><font></font>
        <tr><font></font>
            <td>Row 2 Data 1</td><font></font>
            <td>Row 2 Data 2</td><font></font>
        </tr><font></font> -->
    </tbody><font></font>
</table><font></font>
</body>
</html>