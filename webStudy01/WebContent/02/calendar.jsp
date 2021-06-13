<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	// Calender 라이브러리를 사용하기위한 객체 생성
	Calendar cal = Calendar.getInstance();
	// 오늘 날짜를 기준으로 년도
	int nowYear = cal.get(Calendar.YEAR);
	// 오늘 날짜를 기준으로 월
	int nowMonth = cal.get(Calendar.MONDAY)+1;
	// 오늘 일자
	int nowDay = cal.get(Calendar.DAY_OF_MONTH);
	
	// 해당 페이지에 요청을 보냈을때 년도와 월 파라미터 받기
	String userYear = request.getParameter("year");
	String userMonth = request.getParameter("month");
	
	// 달력에 표시할 년도와 월
	int year = nowYear;
	int month = nowMonth;
	
	// 사용자가 원하는 년도와 월을 파라미터로 받아왔을때
	if(userYear != null){
		year = Integer.parseInt(userYear);
	}
	if(userMonth != null){
		month = Integer.parseInt(userMonth);
	}
	
	// 전월 표시를 위한 변수
	int preYear = year;
	int preMonth = month -1;
	
	if(preMonth < 1){
		preYear = year -1;
		preMonth = 12;
	}
	
	// 다음달 표시를 위한 변수
	int nextYear = year;
	int nextMonth = month + 1;
	
	if(nextMonth > 12){
		nextYear = year + 1;
		nextMonth = 1;
	}
	
	// 달력에 표시하고 자는 년 월 표시를 위해 calendar객체에 셋팅
	cal.set(year,month-1, 1);
	
	// 요일을 표시(1-일요일, 7-토요일)
	int week = cal.get(Calendar.DAY_OF_WEEK);
	int startDay = 1;
	int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	System.out.println(week);

%>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.container{
		width: 700px;
		margin-top: 100px;
	}
	#nowDay{
		background: lightgray;
		font-weight: bold;
	}
	.no6{
		color: blue;
	}
	.no0{
		color: red;
	}
</style>
</head>
<body>
<div class="container">
	<table class="table table-bordered">
		<tr>
			<td colspan="7">
				<a href = "calendar.jsp?year=<%=nowYear%>&month=<%=nowMonth%>">
				TODAY
				</a>
				<a href = "calendar.jsp?year=<%=preYear%>&month=<%=preMonth%>">
				◁
				</a>
				 <button type="button" class="btn btn" data-toggle="modal" data-target="#myModal">
				 <b><%=year%>년&nbsp;&nbsp;<%=month%>월</b></button>
				
				
				<a href = "calendar.jsp?year=<%=nextYear%>&month=<%=nextMonth%>">
				▷
				</a>
			</td>
		</tr>
	
		<tr>
			<td>일</td>
			<td>월</td>
			<td>화</td>
			<td>수</td>
			<td>목</td>
			<td>금</td>
			<td>토</td>
		</tr>
		<tr>
		<% 	
			int newLine = 0;
			for(int i = 1; i < week; i++){
		%>
			<td>&nbsp;</td>
		<%
			newLine++;
			}
			
			for(int i = startDay; i <= endDay; i++){
				if(year == nowYear && month == nowMonth && i == nowDay){
		%>		
					<td id = "nowDay" class = "no<%=newLine%>"><%=i%></td>	
				<%}else{
		%>
					<td class = "no<%=newLine%>"><%=i%></td>
				
				<%
				}
				%>
				
			
		<%	
				newLine++;
				if(newLine==7 && i != endDay){
					newLine = 0;%>
					</tr><tr>	
		<%
				}
			
			}
			while(newLine > 0 && newLine < 7){
				newLine++;
			%>
				<td>&nbsp;</td>
			<%	
			}
		%>
		</tr>
		
	</table>
</div>

 

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">원하는 연월을 선택하세요</h4>
        </div>
        <div class="modal-body" style = "height:60px;">
        	<form action = "calendar.jsp">
        <div class = "col-sm-4">
           <select class="form-control" id="year" name = "year">
           	<%
           		for(int i = 1970; i <= 2100; i++){
           			if(i == year){
           	%>
           				<option value = <%=i%> selected="selected"><%=i%></option>
           	<%			
           			}
           	%>
           			<option value = <%=i%>><%=i%></option>
           	<% 		
           		}
           	%>
      		</select>
      		</div>
      		<div class = "col-sm-3">
      		<select class="form-control" id="month" name = "month">
      			<%
      				for(int i = 1; i <=12; i++){
      					if(i == month){
      			%>
      						<option value = <%=i%> selected="selected"><%=i%></option>
      			<% 			
      					}
      			%>
      					<option value = <%=i%>><%=i%></option>
      			<%		
      				}
      			%>
      		</select>
          </div>
          <div class = "col-sm-2">
			<input type = "submit" value = "확인" >
			</div>
      	</form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
</body>
</html>






           





