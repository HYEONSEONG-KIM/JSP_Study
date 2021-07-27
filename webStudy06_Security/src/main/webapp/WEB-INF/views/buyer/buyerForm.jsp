<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.min.js"></script>
</head>
<body>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}")
	</script>

</c:if>


<form id = "buyerForm" method = "post" enctype="multipart/form-data">
<div class="col-sm-6">
	<table class="table">
		
		<tr>
			<th>거래처명
			<th>
			<td><input type="text" name="buyerName" required
				value="${buyer.buyerName}" /><label id="buyerName-error"
				class="error" for="buyerName">${errors}</label></td>
		</tr>
		
		<tr>
			<th>담당자
			<th>
			<td><input type="text" name="buyerCharger"
				value="${buyer.buyerCharger}" /><label id="buyerCharger-error"
				class="error" for="buyerCharger">${errors}</label></td>
		</tr>
		
		<tr>
			<th>상품분류
			<th>
			<td>
				<select name = "buyerLgu">
						<c:choose>
								<c:when test="${empty lprodList}">
									<option value>목록이 없습니다</option>
								</c:when>
								
								<c:otherwise>
									<c:forEach items="${lprodList}" var="lguMap">
										<option value='${lguMap.get("LPROD_GU")}'>${lguMap.get("LPROD_NM")}</option>
									</c:forEach>
								</c:otherwise>
					
					</c:choose>
					
				
				</select>
				<label id="buyerLgu-error"
				class="error" for="buyerLgu">${errors}</label></td>
		</tr>
		
		<tr>
			<th>사업자 등록증</th>
			<td>
				<input type = "file" name = "buyerImage">
				<label id="buyerImage-error"
				class="error" for="buyerImage">${errors.buyerImage}</label>
			</td>
		</tr>
		
		<tr>
			<th>거래은행
			<th>
			<td><input type="text" name="buyerBank"
				value="${buyer.buyerBank}" /><label id="buyerBank-error"
				class="error" for="buyerBank">${errors}</label></td>
		</tr>
		<tr>
			<th>계좌번호
			<th>
			<td><input type="text" name="buyerBankno"
				value="${buyer.buyerBankno}" /><label id="buyerBankno-error"
				class="error" for="buyerBankno">${errors}</label></td>
		</tr>
		<tr>
			<th>예금주
			<th>
			<td><input type="text" name="buyerBankname"
				value="${buyer.buyerBankname}" /><label id="buyerBankname-error"
				class="error" for="buyerBankname">${errors}</label></td>
		</tr>
		<tr>
			<th>우편번호
			<th>
			<td><input type="text" name="buyerZip" value="${buyer.buyerZip}" /><label
				id="buyerZip-error" class="error" for="buyerZip">${errors}</label></td>
		</tr>
		<tr>
			<th>주소
			<th>
			<td><input type="text" name="buyerAdd1"
				value="${buyer.buyerAdd1}" /><label id="buyerAdd1-error"
				class="error" for="buyerAdd1">${errors}</label></td>
		</tr>
		<tr>
			<th>상세주소
			<th>
			<td><input type="text" name="buyerAdd2"
				value="${buyer.buyerAdd2}" /><label id="buyerAdd2-error"
				class="error" for="buyerAdd2">${errors}</label></td>
		</tr>
		<tr>
			<th>회사번호
			<th>
			<td><input type="text" name="buyerComtel" required
				value="${buyer.buyerComtel}" /><label id="buyerComtel-error"
				class="error" for="buyerComtel">${errors}</label></td>
		</tr>
		<tr>
			<th>팩스번호
			<th>
			<td><input type="text" name="buyerFax" required
				value="${buyer.buyerFax}" /><label id="buyerFax-error"
				class="error" for="buyerFax">${errors}</label></td>
		</tr>
		<tr>
			<th>메일
			<th>
			<td><input type="text" name="buyerMail" required
				value="${buyer.buyerMail}" /><label id="buyerMail-error"
				class="error" for="buyerMail">${errors}</label></td>
		</tr>
	
		<tr>
			<th>전화
			<th>
			<td><input type="text" name="buyerTelext"
				value="${buyer.buyerTelext}" /><label id="buyerTelext-error"
				class="error" for="buyerTelext">${errors}</label></td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type = "submit" value = "저장">
			</td>
		</tr>

	</table>
</div>
</form>
</body>
<script type="text/javascript">

	$('#buyerForm').validate();

</script>


<jsp:include page="/includee/footer.jsp"></jsp:include>
</html>














