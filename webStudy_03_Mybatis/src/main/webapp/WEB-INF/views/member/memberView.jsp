<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.Set"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<c:if test="${not empty deleteMessage }">
	<script type="text/javascript">
		alert("${deleteMessage}");
	</script>
	
	<c:remove var="deleteMessage" scope="session"/>

</c:if>

			<table class="table table-striped">

		<tr>
			<th>회원ID</th>
			<td>${member.memId } </td>
		</tr>
		<tr>
			<th>회원PASS</th>
			<td>${member.memPass} ></td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${member.memName}</td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td>${member.memRegno1}</td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td>${member.memRegno2}</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>${member.memBir}</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${member.memZip}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td><${member.memAdd1}</td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td>${member.memAdd2}</td>
		</tr>
		<tr>
			<th>집전화</th>
			<td>${member.memHometel}</td>
		</tr>
		<tr>
			<th>ㅠㅠ</th>
			<td>${member.memComtel}</td>
		</tr>
		<tr>
			<th>휴대전화</th>
			<td>${member.memHp}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${member.memMail}</td>
		</tr>
		<tr>
			<th>직업</th>
			<td>${member.memJob}</td>
		</tr>
		<tr>
			<th>취미</th>
			<td>${member.memLike}</td>
		</tr>
		<tr>
			<th>비밀번호 질문</th>
			<td>${member.memMemorial}</td>
		</tr>
		<tr>
			<th>해답</th>
			<td>${member.memMemorialday}</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${member.memMileage}</td>
		</tr>
		

		<tr>
			<th>구매기록</th>
			<td>
				<table>
					<thead>
						<tr>
							<th>상품분류</th>
							<th>거래처명</th>
							<th>거래처소재지</th>
							<th>상품명</th>
							<th>구매가</th>
							<th>판매가</th>
							<th>마일리지</th>
						</tr>
					</thead>
					<tbody>
								<c:set var="prodList" value="${member.prodList }" ></c:set>
						<c:choose>
							<c:when test="${empty prodList }">
								<tr>
									<td colspan="7">구매 기록 없음</td>
								</tr>
							</c:when>
							
							
							<c:otherwise>
								<%--자동으로 page 스코프에 담김 --%>
								<c:forEach items="${prodList}" var="prod" >
									<tr>
										<td>${prod.lprodNm}</td>
										<td>${prod.buyer.buyerName }</td>
										<td>${prod.buyer.buyerAdd1 }</td>
										<td>${prod.prodName }</td>
										<td>${prod.prodCost}</td>
										<td>${prod.prodPrice}</td>
										<td>${prod.prodMileage}</td>
									</tr>
								</c:forEach>
							
							</c:otherwise>
						</c:choose>
						
					</tbody>				
				</table>
			
			
			</td>
		</tr>
		
		
		<c:if test="${sessionScope.authMember eq member}">
			<tr>
				<td colspan="2">
					<input type = "button" value = "수정" id = "modify"/>
					<input type = "button" value = "탈퇴" id = "delete"/>
					
					<form method="post" action="<%=request.getContextPath()%>/member/memberDelete.do">
						<input type = "hidden" name = "memPass"/>
					</form>
				</td>
			</tr>
		</c:if>
		
	
		
		
			</table>
		
	
	
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
















