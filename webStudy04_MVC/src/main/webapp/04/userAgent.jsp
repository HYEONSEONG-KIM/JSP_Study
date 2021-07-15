<%@page import="kr.or.ddit.enumtype.BrowserType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/userAgent.jsp</title>
<script type="text/javascript" src = "<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		const PATTERN = "당신의 브라우저는 %s입니다. os의 종류는 %o 입니다";
		let resultArea = $('#resultArea');
		$("a:first").on('click',function(event){
			event.preventDefault();
			$.ajax({
				url : "<%=request.getContextPath()%>/04/getBrowserName",
				dataType : "json", 	// request header(Accept) / response header(content-Type)
									// text : text/plain, html : text/html, json : application/json, script : text/jacascript
				success : function(resp) {
					let message = null;
					let os = null;
					let browser = null;
					
					if(typeof resp == "string"){
						message = resp.split(",");
						
						os = message[0];
						browser = message[1];
					}else{
						os = resp.os;
						browser = resp.browser;
					}
					
					resultArea.empty();
					resultArea.append(
						$("<p>").html(PATTERN.replace("%s", browser).replace("%o",os))
					);
				},
				error : function(errorResp) {
					console.log(errorResp);
				}

			});
			return false;
		});
		
		
	});
</script>
</head>
<body>
<a href = "#">브라우저의 이름 받아오기(비동기)</a>
<div id = resultArea></div>
<!-- 사용자의 브라우저를 식별하고, 각 브라우저에 맞는 메세지를 출력 -->
<!-- "당신의 브라우저는  o o o 입니다" 형식으로 포맷팅 메세지를 사용 -->
<%--
/* 	Enumeration<String> header =  request.getHeaderNames();
	while(header.hasMoreElements()){
	
	String headerName = header.nextElement();
	System.out.println(headerName);
	} */
	
	String pattern = "<p>당신의 브라우저는 %s 입니다</p>";
	String userAgent = request.getHeader("user-agent");
	out.println(userAgent);
	
	/* // LinkedHashMap => 엔트리와 엔트리의 연결 => 순서가 있는 것 처럼 구현
	Map<String,String> browserMap = new LinkedHashMap<>();
	browserMap.put("MISE", "익스플로러 구버전");
	browserMap.put("TRIDENT", "익스플로러 최신버전");
	browserMap.put("OPERA", "오페라");
	browserMap.put("FIREFOX", "파이어폭스");
	browserMap.put("EDG", "엣지");
	browserMap.put("CHROME", "크롬");
	browserMap.put("SAFARI", "사파리");
	browserMap.put("OTHER", "기타"); */
/* 	for(Entry<String,String> entry : browserMap.entrySet()){
		if(userAgent.indexOf(entry.getKey()) >= 0){
			brower = entry.getValue();
			break;
		}
	} */
	
	String brower = BrowserType.paeseUserAgent(userAgent);
	out.println(
		String.format(pattern, brower)		
	);
	
	 
	
	
--%>
</body>
</html>