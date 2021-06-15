<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String yearStr = request.getParameter("year");
	String monthStr = request.getParameter("month");
	String language = request.getParameter("language");
	
	Locale locale = request.getLocale();
	if(language != null && !language.isEmpty()){
		// 내가 선택한 지역의 언어로 설정
		locale = Locale.forLanguageTag(language);
	}
	
	// 언어를 설정된 지역의 언어로 
	DateFormatSymbols dfs = DateFormatSymbols.getInstance(locale);
	Calendar cal = getInstance();
	Calendar calServer = getInstance();
	
	int nowYear = cal.get(YEAR);
	int nowMonth = cal.get(MONTH);
	int nowDay = cal.get(DAY_OF_MONTH);
	
	
	if(yearStr != null && yearStr.matches("\\d{4}")){
		cal.set(YEAR, Integer.parseInt(yearStr));
	}
	if(monthStr != null && monthStr.matches("\\d{1,2}")){
		cal.set(MONTH, Integer.parseInt(monthStr));
	}
	
	int year = cal.get(YEAR);
	int month = cal.get(MONTH);
	int day = cal.get(DAY_OF_MONTH);
	
	cal.set(DAY_OF_MONTH, 1);
	int offset = cal.get(DAY_OF_WEEK) - 1;
	int lastDate = cal.getActualMaximum(DAY_OF_MONTH);
	
	cal.add(MONTH, -1);
	int beforeYear = cal.get(YEAR);
	int befoerMonth = cal.get(MONTH);
	
	cal.add(MONTH, 2);
	int nextYear = cal.get(YEAR);
	int nextMonth = cal.get(MONTH);
	cal.add(MONTH, -1);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/calendarTea.jsp</title>
<style type="text/css">
	.sunday{
		color : red;
	}
	.saturday{
		color : blue;
	}
	.current{
	font-weight : bold;
		color : green;
	}

</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

</head>
<body>
<h4>현재 서버의 시각 : <%=String.format(locale, "%tc", calServer) %></h4>
<h4>
<a href = "#" class ="moveA" data-year = "<%=beforeYear%>" data-month = "<%=befoerMonth%>">이전달</a>
<%=String.format(locale,"%1$tY, %1$tB", cal) %>
<a href = "#" class ="moveA" data-year = "<%=nextYear%>" data-month = <%=nextMonth %>>다음달</a>
</h4>
<form id = calendarForm>
	<input type = "number" name = "year" placeholder="<%=year %>" value = "<%=year%>"/>
	<select name = "month">
		<option value>월선택</option>
		<%
			String optionPtrn = "<option %s value = '%s'>%s</option>";
			String[] months = dfs.getMonths();
			for(int tmp = 0; tmp < 12; tmp++){
				String selected = (tmp) == month ? "selected" : "";
				out.println(
					String.format(optionPtrn,selected,tmp, months[tmp])		
				);
				
			}
		%>
	</select>
	
	<select name = "language">
		<%
			Locale[] locales = Locale.getAvailableLocales();
			
			for(Locale tmpLoc : locales){
				String tag = tmpLoc.toLanguageTag();
				String name = tmpLoc.getDisplayName();
				String selected = tmpLoc.equals(locale)?"selected":"";
				if(name.isEmpty()) continue;
					
				out.println(
					String.format(optionPtrn, selected, tag, name)
				);
				
			}
		%>
	</select>
</form>
<table>
	<thead>
		<tr>
			<%
				
				String[] weekDays = dfs.getWeekdays();
				String thPtrn = "<th>%s</th>";
				for(int idx = SUNDAY; idx <= SATURDAY; idx++){
					out.println(
						String.format(thPtrn, weekDays[idx])		
					);
				}
			%>
		</tr>
	</thead>
	<tbody>
		<%
			
			String pattern = "<td class = '%s'>%s</td>";
			int number = 1;
			for(int row = 1; row <= 6; row++){
				int lineCheck = 0;
				out.println("<tr>");
				for(int col = SUNDAY; col <= SATURDAY; col++){
					lineCheck++;
					int dateNumber = number++ - offset;
					String printNumber = dateNumber >= 1 && dateNumber <= lastDate ? dateNumber+"" : "&nbsp;";
					String className = lineCheck == 1 ? "sunday" : lineCheck == 7 ? "saturday" : "";
					if(dateNumber == nowDay && year == nowYear && month == nowMonth){
						className = "current";
					}
					out.println(String.format(pattern,className,printNumber));
				}
				out.println("</tr>");
			}
		%>
	</tbody>
</table>
<script type="text/javascript">

	
		let calForm = $("#calendarForm").on("change", ":input" ,function(){
			console.log(this.form);
			console.log(calForm[0]);
			//html객체 
			this.form.submit();
			// jQuery객체
			//calForm.submit();	
		}).on("submit",function(){
			console.log("===========================");
			return true;
		});
		
		$(".moveA").on("click",function(event){
			event.preventDefault();
			let year = $(this).data("year");
			let month = $(this).data("month");
			calForm.find("input[name='year']").val(year);
			$(calForm.get(0).month).val(month);
			calForm.submit();
			return false;
		});
		
	
</script>
</body>
</html>