<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<title>Insert title here</title>
<style>
	table, td, th{
		border : 1px solid lightgray;
		border-collapse: collapse;
		border-spacing: 0px;
	}
	td, th{
		width: 200px;
		height: 50px;
	}
</style>
</head>
<body>
	<%
		String deleteMessage = (String)session.getAttribute("deleteMessage");	
		
		if(StringUtils.isNoneBlank(deleteMessage)){
			%>
			<script type="text/javascript">
				alert("<%=deleteMessage%>");
			</script>
			<% 
			session.removeAttribute("deleteMessage");
		}
	
	
		MemberVO member = (MemberVO)request.getAttribute("member");
		if(member == null){
			out.print(request.getAttribute("message"));
		}else{
		%>
			<table class="table table-striped">

		<tr>
			<th>회원ID</th>
			<td><%=member.getMemId()%></td>
		</tr>
		<tr>
			<th>회원PASS</th>
			<td><%=member.getMemPass()%></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=member.getMemName()%></td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td><%=member.getMemRegno1()%></td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td><%=member.getMemRegno2()%></td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td><%=member.getMemBir()%></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><%=member.getMemZip()%></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><%=member.getMemAdd1()%></td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td><%=member.getMemAdd2()%></td>
		</tr>
		<tr>
			<th>집전화</th>
			<td><%=member.getMemHometel()%></td>
		</tr>
		<tr>
			<th>ㅠㅠ</th>
			<td><%=member.getMemComtel()%></td>
		</tr>
		<tr>
			<th>휴대전화</th>
			<td><%=member.getMemHp()%></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=member.getMemMail()%></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><%=member.getMemJob()%></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><%=member.getMemLike()%></td>
		</tr>
		<tr>
			<th>비밀번호 질문</th>
			<td><%=member.getMemMemorial()%></td>
		</tr>
		<tr>
			<th>해답</th>
			<td><%=member.getMemMemorialday()%></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><%=member.getMemMileage()%></td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td><%=member.getMemDelete()%></td>
		</tr>

		<%
			MemberVO authMember = (MemberVO)session.getAttribute("authMember");
			boolean rendering = member.equals(authMember);
			if(rendering){
		%>
		<tr>
			<td colspan="2">
				<input type = "button" value = "수정" id = "modify"/>
				<input type = "button" value = "탈퇴" id = "delete"/>
				
				<form method="post" action="<%=request.getContextPath()%>/member/memberDelete.do">
					<input type = "hidden" name = "memPass"/>
				</form>
			</td>
		</tr>
		
		<%
			}
		%>	
			</table>
		
		<% 	
		}
	%>
	
</body>
<script type="text/javascript">

	$('#modify').on('click',function(){
		location.href = "<%=request.getContextPath()%>/member/memberUpdate.do"
	})
	
	$('#delete').on('click',function(){
		
		let pass = prompt("패스워드 입력").trim();
		if(pass == ""){
			alert("잘못된 입력값 입니다");
			return;
		}
		$('input[name="memPass"]').val(pass)
		
		deleteForm.submit();
	})
	
	
	let deleteForm = $('form').on('submit',function(){
		
	})
	
</script>
</html>
<jsp:include page="/includee/footer.jsp"></jsp:include>
















