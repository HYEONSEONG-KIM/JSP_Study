<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message }");
	</script>
	<c:remove var="message" scope="session"/>
</c:if>
</head>
<body>
	<table class="table table-bordered">
		<tr>
			<th>회원 ID</th>
			<td>${member.memId}</td>
		</tr>
		<tr>
			<th>회원 명</th>
			<td>${member.memName}
				<%-- <c:if test="${not empty member.memImg }">
					<img src="data:image/*;base64,${member.base64Img }"/> 
				</c:if>
				<c:if test="${not empty member.memPath }">
					<img src="${cPath }${memImagesUrl }/${member.memPath }" />
				</c:if> --%>
			</td>
		</tr>
		<tr>
			<th>주민등록번호1</th>
			<td>${member.memRegno1}</td>
		</tr>
		<tr>
			<th>주민등록번호2</th>
			<td>${member.memRegno2}</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>${member.memBir}</td>
		</tr>
		<tr>
			<th>우편 번호</th>
			<td>${member.memZip}</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>${member.memAdd1}</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>${member.memAdd2}</td>
		</tr>
		<tr>
			<th>집 전화 번호</th>
			<td>${member.memHometel}</td>
		</tr>
		<tr>
			<th>회사 전화 번호</th>
			<td>${member.memComtel}</td>
		</tr>
		<tr>
			<th>이동 전화 번호</th>
			<td>${member.memHp}</td>
		</tr>
		<tr>
			<th>이메일 주소</th>
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
			<th>기념일 명</th>
			<td>${member.memMemorial}</td>
		</tr>
		<tr>
			<th>기념일 날짜</th>
			<td>${member.memMemorialday}</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${member.memMileage}</td>
		</tr>
		<tr>
			<th>탈퇴 여부</th>
			<td>${member.memDelete?'탈퇴':''}</td>
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
						<c:set var="prodList" value="${member.prodList }" />
						<c:choose>
							<c:when test="${empty prodList }">
								<tr>
									<td colspan="7">구매 기록 없음.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${prodList }" var="prod">
									<tr class="controlBtn" data-gopage="${pageContext.request.contextPath }/prod/prodView.do?what=${prod.prodId }">
										<td>${prod.lprodNm}</td>
										<td>${prod.buyer.buyerName}</td>
										<td>${prod.buyer.buyerAdd1}</td>
										<td>
												${prod.prodName}
										</td>
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
					<input type="button" value="수정" class="controlBtn btn btn-primary"
						data-gopage="${pageContext.request.contextPath }/member/memberUpdate.do"
					/>
					<input type="button" value="탈퇴" class="controlBtn btn btn-danger"
						id="deleteBtn"
					/>
					<form id="deleteForm" method="post" action="${pageContext.request.contextPath }/member/memberDelete.do">
						<input type="hidden" name="memPass" />
					</form>
					<script>
						let deleteForm = $("#deleteForm");
						$("#deleteBtn").on("click", function(event){
							let memPass = prompt("비밀번호!");
							if(memPass){
								deleteForm.get(0).memPass.value = memPass;
								deleteForm.submit();
							}
						});
						$(".controlBtn").on("click", function(){
							let gopage = $(this).data("gopage");
							if(gopage){
								location.href = gopage;
							}
						});		
					</script>
	 			</td>
			</tr>
		</c:if>
	</table>
	<script type="text/javascript">
		$(".controlBtn").on("click", function(){
			let gopage = $(this).data("gopage");
			if(gopage){
				location.href = gopage;
			}
		}).css("cursor", "pointer");
	</script>
	<jsp:include page="/includee/footer.jsp" />	
</body>
</html>








