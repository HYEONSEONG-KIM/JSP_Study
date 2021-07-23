<%@page import="java.util.Objects"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.min.js"></script>
<link rel = "stylesheet" href = "//cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
<script type="text/javascript" src = "//cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>


<title>Insert title here</title>
<style type="text/css">
th {
	width: 100px;
}

td {
	width: 300px;
}
</style>
</head>
<body>
	
	<c:if test="${not empty message}">
		<script type="text/javascript">
			alert("${message}");
		</script>
	</c:if>
	
	<form id="memberForm" method="post" enctype="multipart/form-data" >
		<div class="col-sm-6">
			<table class="table table-striped">
				<tr>
				<tr>
					<th>회원ID
					<th>
					<td><input type="text" name="memId" required
						value="${member.getMemId()}" /><label id="memId-error"
						class="error" for="memId">${errors["memId"]}</label></td>
				</tr>
				<tr>
					<th>회원PASS
					<th>
					<td><input type="text" name="memPass" required
						value="${member.getMemPass()}" /><label id="memPass-error"
						class="error" for="memPass">${errors.get("memPass")}</label></td>
				</tr>
				<tr>
					<th>이름
					<th>
					<td><input type="text" name="memName" required
						value="${member.getMemName()}" /><label id="memName-error"
						class="error" for="memName">${errors.get("memName")}</label></td>
				</tr>
				
				<tr>
					<th>회원이미지</th>
					<td>
						<input type = "file" name = "memImage"/>
						<label id="memImage-error"
						class="error" for="memImage">${errors.get("memImage")}</label></td>
					</td>
				</tr>
				
				<tr>
					<th>주민번호1
					<th>
					<td><input type="text" name="memRegno1"
						value="${member.getMemRegno1()}" /><label id="memRegno1-error"
						class="error" for="memRegno1">${errors.get("memRegno1")}</label></td>
				</tr>
				<tr>
					<th>주민번호2
					<th>
					<td><input type="text" name="memRegno2"
						value="${member.getMemRegno2()}" /><label id="memRegno2-error"
						class="error" for="memRegno2">${errors.get("memRegno2")}</label></td>
				</tr>
				<tr>
					<th>생년월일
					<th>
					<td><input type="date" name="memBir"
						value="${member.getMemBir()}" /><label id="memBir-error"
						class="error" for="memBir">${errors.get("memBir")}</label></td>
				</tr>
				<tr>
					<th>우편번호
					<th>
					<td><input type="text" name="memZip" required
						value="${member.getMemZip()}" />
					<input type = "button" value = "우편번호 검색"/>	
						<label id="memZip-error"
						class="error" for="memZip">${errors.get("memZip")}</label></td>
				</tr>
				<tr>
					<th>주소
					<th>
					<td><input type="text" name="memAdd1" required
						value="${member.getMemAdd1()}" /><label id="memAdd1-error"
						class="error" for="memAdd1">${errors.get("memAdd1")}</label></td>
				</tr>
				<tr>
					<th>상세주소
					<th>
					<td><input type="text" name="memAdd2" required
						value="${member.getMemAdd2()}" /><label id="memAdd2-error"
						class="error" for="memAdd2">${errors.get("memAdd2")}</label></td>
				</tr>
				<tr>
					<th>집전화
					<th>
					<td><input type="text" name="memHometel"
						value="${member.getMemHometel()}" /><label id="memHometel-error"
						class="error" for="memHometel">${errors.get("memHometel")}</label></td>
				</tr>
				<tr>
					<th>회사전화
					<th>
					<td><input type="text" name="memComtel"
						value="${member.getMemComtel()}" /><label id="memComtel-error"
						class="error" for="memComtel">${errors.get("memComtel")}</label></td>
				</tr>
				<tr>
					<th>휴대전화
					<th>
					<td><input type="text" name="memHp"
						value="${member.getMemHp()}" /><label id="memHp-error"
						class="error" for="memHp">${errors.get("memHp")}</label></td>
				</tr>
				<tr>
					<th>이메일
					<th>
					<td><input type="text" name="memMail" required
						value="${member.getMemMail()}" /><label id="memMail-error"
						class="error" for="memMail">${errors.get("memMail")}</label></td>
				</tr>
				<tr>
					<th>직업
					<th>
					<td><input type="text" name="memJob"
						value="${member.getMemJob()}" /><label id="memJob-error"
						class="error" for="memJob">${errors.get("memJob")}</label></td>
				</tr>
				<tr>
					<th>취미
					<th>
					<td><input type="text" name="memLike"
						value="${member.getMemLike()}" /><label id="memLike-error"
						class="error" for="memLike">${errors.get("memLike")}</label></td>
				</tr>
				<tr>
					<th>기념일명
					<th>
					<td><input type="text" name="memMemorial"
						value="${member.getMemMemorial()}" /><label
						id="memMemorial-error" class="error" for="memMemorial">${errors.get("memMemorial")}</label></td>
				</tr>
				<tr>
					<th>기념일
					<th>
					<td><input type="date" name="memMemorialday"
						value="${member.getMemMemorialday()}" /><label
						id="memMemorialday-error" class="error" for="memMemorialday">${errors.get("memMemorialday")}</label></td>
				</tr>
				<tr>
					<th>마일리지
					<th>
					<td><input type="text" name="memMileage"
						value="${member.getMemMileage()}" /><label id="memMileage-error"
						class="error" for="memMileage">${errors.get("memMileage")}</label></td>
				</tr>
				<tr>
					<th>탈퇴여부
					<th>
					<td><input type="text" name="memDelete"
						value="${member.getMemDelete()}" /><label id="memDelete-error"
						class="error" for="memDelete">${errors["memDelete"]}</label></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="저장" required /></td>
				</tr>

			</table>
		</div>
	</form>
</body>
<script type="text/javascript">
	<%-- $(function(){
		$('#memberForm').validate({
				rules:{
						memId : {
							required: true,
						 	remote : "<%=request.getContextPath()%>/member/IdCheck.do"
						}
				}
		});
		
		
	}) --%>
</script>
</html>
<jsp:include page="/includee/footer.jsp"></jsp:include>














