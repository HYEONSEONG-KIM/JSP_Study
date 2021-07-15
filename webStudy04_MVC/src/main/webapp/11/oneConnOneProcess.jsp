<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/oneConnOneProcess.jsp</title>
</head>
<body>
	<%
		long start = System.currentTimeMillis();
		try(
			Connection conn = ConnectionFactory.getConnection();	
			Statement stmt = conn.createStatement();
		){
			String sql = "SELECT ENAME FROM EMP WHERE MGR IS NULL";
			ResultSet rs = stmt.executeQuery(sql);	
			if(rs.next()){
				out.println(rs.getString("ENAME"));
			}
		}
		long end = System.currentTimeMillis();
	%>
	소요시간 : <%=end - start %>ms
</body>
</html>