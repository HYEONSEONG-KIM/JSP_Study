<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
<style type="text/css">
	table{
		display : block;
		width: 1000px;
		height: auto;
	}
</style>
</head>
<body>
<%
	MemberVO member = (MemberVO)request.getAttribute("member");
	
	if(member == null){
		out.print("정보가 존재하지 않습니다");
	}else{
	%>		
		<table class="table table-striped">

		<tr>
			<th>회원ID</th>
			<td><%=member.getMem_id()%></td>
		</tr>
		<tr>
			<th>회원PASS</th>
			<td><%=member.getMem_pass()%></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=member.getMem_name()%></td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td><%=member.getMem_regno1()%></td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td><%=member.getMem_regno2()%></td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td><%=member.getMem_bir()%></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><%=member.getMem_zip()%></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><%=member.getMem_add1()%></td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td><%=member.getMem_add2()%></td>
		</tr>
		<tr>
			<th>집전화</th>
			<td><%=member.getMem_hometel()%></td>
		</tr>
		<tr>
			<th>ㅠㅠ</th>
			<td><%=member.getMem_comtel()%></td>
		</tr>
		<tr>
			<th>휴대전화</th>
			<td><%=member.getMem_hp()%></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=member.getMem_mail()%></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><%=member.getMem_job()%></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><%=member.getMem_like()%></td>
		</tr>
		<tr>
			<th>비밀번호 질문</th>
			<td><%=member.getMem_memorial()%></td>
		</tr>
		<tr>
			<th>해답</th>
			<td><%=member.getMem_memorialday()%></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><%=member.getMem_mileage()%></td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td><%=member.getMem_delete()%></td>
		</tr>
	</table>
		
<%	
	}
%>

</body>
</html>
<jsp:include page="/includee/footer.jsp"></jsp:include>