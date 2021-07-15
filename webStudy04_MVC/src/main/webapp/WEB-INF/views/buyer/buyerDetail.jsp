<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class = "table">
		<tr>
			<th>거래처ID</th>
			<td>${buyer.buyerId}</td>
		</tr>
		<tr>
			<th>거래처명</th>
			<td>${buyer.buyerName}</td>
		</tr>
		<tr>
			<th>상품분류명</th>
			<td>${buyer.lprod.lprodNm}</td>
		</tr>
		<tr>
			<th>거래은행</th>
			<td>${buyer.buyerBank}</td>
		</tr>
		<tr>
			<th>계좌번호</th>
			<td>${buyer.buyerBankno}</td>
		</tr>
		<tr>
			<th>예금주</th>
			<td>${buyer.buyerBankname}</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${buyer.buyerZip}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${buyer.buyerAdd1}</td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td>${buyer.buyerAdd2}</td>
		</tr>
		<tr>
			<th>회사번호</th>
			<td>${buyer.buyerComtel}</td>
		</tr>
		<tr>
			<th>팩스번호</th>
			<td>${buyer.buyerFax}</td>
		</tr>
		<tr>
			<th>메일</th>
			<td>${buyer.buyerMail}</td>
		</tr>
		<tr>
			<th>거래처 대표</th>
			<td>${buyer.buyerCharger}</td>
		</tr>
		<tr>
			<th>전화</th>
			<td>${buyer.buyerTelext}</td>
		</tr>

	</table>

</body>
</html>