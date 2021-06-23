<%@page import="java.nio.file.StandardCopyOption"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Path"%>
<%@page import="java.net.URL"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/applicationDesc.jsp</title>
</head>
<body>
<h4>ServletContext application</h4>
<pre>
	: Servlet container(WAS)와 해당 컨테이너 내에서 운영되는 어플리케이션(context)에 대한 정보를 가진 객체
	1. 컨텍스트 초기화 파라미터 확보
	<%=application.getInitParameter("contentsPath") %>
	
	2. 로그 기록
	<%
		application.log("명시적으로 기록할 로그 데이터");
	%>
	3. 서버나 컨텍스트에 대한 정보 확인
	<%=application.getServerInfo() %>, <%=application.getMajorVersion() %>.<%=application.getMinorVersion() %>
	4. 웹 리소스 확보(****)
	<%
		/* String readPath = application.getRealPath("/resources/images/333.jpg");
		String writePath = application.getRealPath("/08/333.jpg");
		System.out.println(writePath);
		File readImage = new File(readPath);
		File writeImage = new File(writePath);
		System.out.println();
		
		try(
			FileInputStream fis = new FileInputStream(readImage);
			FileOutputStream fos = new FileOutputStream(writeImage);
		){
			byte[] buffer = new byte[1024];
			int pointer = -1;
			while((pointer = fis.read(buffer)) != -1){
			fos.write(buffer, 0, pointer);
		}
			fos.flush();
		}	  */
	 	String imageURL = "/resources/images/333.jpg";
		String destURLStr = "/08/333.jpg";
		
		URL destURL =  application.getResource(destURLStr);
		Path target;
		if(destURL == null){
			String destRP = application.getRealPath(destURLStr);
			target =  Paths.get(destRP);
		}else{
			target = Paths.get(destURL.toURI());
		}
		InputStream is = application.getResourceAsStream(imageURL);
		Files.copy(is, target,StandardCopyOption.REPLACE_EXISTING); 
	
	%>
	/resources/images/333.jpg
	/08/333.png
</pre>
	
	<img src = "<%=application.getContextPath() %>/08/333.jpg">
</body>
</html>