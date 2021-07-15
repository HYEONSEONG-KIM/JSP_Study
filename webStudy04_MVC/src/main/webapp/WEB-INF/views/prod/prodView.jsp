
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table class = 'table'>
	
		<tr>
			<th>상품 분류 번호</th>
			<td>${prod.prodId}</td>
		</tr>
		<tr>
			<th>상품 이름</th>
			<td>${prod.prodName}</td>
		</tr>
		<tr>
			<th>상품 분류명</th>
			<td>${prod.lprodNm}</td>
		</tr>
		<tr>
			<th>거래처 명</th>
			<td>${prod.buyer.buyerName}</td>
		</tr>
		<tr>
			<th>구입 가격</th>
			<td>${prod.prodCost}</td>
		</tr>
		<tr>
			<th>판매 가격</th>
			<td>${prod.prodPrice}</td>
		</tr>
		
		<tr>
			<th>간단 정보</th>
			<td>${prod.prodOutline}</td>
		</tr>
		<tr>
			<th>상세 정보</th>
			<td>${prod.prodDetail}</td>
		</tr>
		
		<tr>
			<th>이미지</th>
			<td>
				<img src = "${cPath}${prodImagesUrl}/${prod.prodImg}"/>
			</td>
		</tr>
		
	
		<tr>
			<th>입고일</th>
			<td>${prod.prodInsdate}</td>
		</tr>
		
		<tr>
			<th>상품 크기</th>
			<td>${prod.prodSize}</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>${prod.prodColor}</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:url value="/prod/prodUpdate.do" var="updateURL">
					<c:param name="what" value="${prod.prodId}"></c:param>
				</c:url>
				<a href = "${updateURL}">상품 수정</a>
			</td>
		
		</tr>
		
		<tr>
			<th>구매자 정보</th>
			<td>
				<table class = 'table'>
					<thead>
						<tr>
							<th>아이디</th>
							<th>이름</th>
							<th>휴대폰</th>
							<th>메일</th>
							<th>마일리지</th>
						</tr>
					</thead>
					<tbody>
					<c:set var="memberList" value="${prod.memberList}"></c:set>
					
					<c:choose>
						<c:when test="${empty memberList}">
							<tr>
								<td colspan="5">해당 상품을 구입한 멤버가 없습니다</td>
							</tr>
						</c:when>
						
						
						<c:otherwise>
							<c:forEach var="mem" items="${memberList}" >
								<tr>
									<td>${mem.memId}</td>
									<td>${mem.memName}</td>
									<td>${mem.memHp}</td>
									<td>${mem.memMail}</td>
									<td>${mem.memMileage}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					
					</c:choose>
						981 290 519
					</tbody>
				
				</table>
			</td>
		</tr>
	</table>


</body>
</html>
<jsp:include page="/includee/footer.jsp"></jsp:include>