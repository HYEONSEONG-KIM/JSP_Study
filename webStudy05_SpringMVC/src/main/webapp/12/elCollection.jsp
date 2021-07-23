<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> EL 에서의 집합 객체 사용</h4>
<pre>
	<%
		String[] array = new String[]{"array1", "array2"};
		List<String> list = Arrays.asList(array);
		Set<String> set = Collections.singleton("setValue");
		Map<String,Object> map = new HashMap<>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key 3", "value3");
		map.put("key-4", "value4");
		
		pageContext.setAttribute("array", array);
		pageContext.setAttribute("list", list);
		pageContext.setAttribute("set", set);
		pageContext.setAttribute("map", map);
	
	%>
	${array[1]}, <%=array[1] %> ${array[4]} ,<%-- <%=array[4] %> --%>
	${list.get(0)} / ${list[0]}/ ${list[4]} /\${list.get(4)} => list여도 2.1방식 사용 => list[]
	${set}
	${map.get("key1")}/ ${map.key1}
	\${map.key 3 } => 토큰이 쪼개져 연산자로 인식 -> ${map["key 3"] }
	${map.key-4 } => - 연산자로 인식 -> ${map["key-4"] }
</pre>

</body>
</html>