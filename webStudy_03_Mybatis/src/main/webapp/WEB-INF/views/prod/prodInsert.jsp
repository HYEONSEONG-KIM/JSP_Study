<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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

<form id = "insertForm" method="post">
	<div class="col-sm-6">
	<table class = "table">
				<tr>
					<th>상품 분류명
					<th>
					<td>
						<select name = "prodLgu">
							<c:choose>
								<c:when test="${empty prodLguList}">
									<option value>목록이 없습니다</option>
								</c:when>
								
								<c:otherwise>
									<c:forEach items="${prodLguList}" var="lguMap">
										<option value='${lguMap.get("PROD_LGU")}'>${lguMap.get("LPROD_NM")}</option>
									</c:forEach>
								</c:otherwise>
							
							</c:choose>
						
						
						</select>
					
					</td>
				</tr>
				
				
				<tr>
					
					<th>거래처명
					
						<th>
					
						<td>
						<select name="prodBuyer">
							<c:choose>
								<c:when test="${empty buyerList}">
									<option value>항목이 없습니다</option>
								</c:when>
	
								<c:otherwise>
									<c:forEach items="${buyerList}" var="buyer">
										<option value="${buyer.buyerId}">${buyer.buyerName}</option>
									</c:forEach>
	
								</c:otherwise>
							</c:choose>
					</select>
						</td>
				</tr>

				<tr>
					<th>상품 이름
					<th>
					<td><input type="text" name="prodName" required
						value="${prod.prodName}" /><label id="prodName-error" class="error"
						for="prodName">${errors}</label></td>
				</tr>
			
				
				
			
				<tr>
					<th>구매가격
					<th>
					<td><input type="text" name="prodCost" required
						value="${prod.prodCost}" /><label id="prodCost-error" class="error"
						for="prodCost">${errors}</label></td>
				</tr>
				<tr>
					<th>가격
					<th>
					<td><input type="text" name="prodPrice" required
						value="${prod.prodPrice}" /><label id="prodPrice-error" class="error"
						for="prodPrice">${errors}</label></td>
				</tr>
				<tr>
					<th>판매가격
					<th>
					<td><input type="text" name="prodSale" required
						value="${prod.prodSale}" /><label id="prodSale-error" class="error"
						for="prodSale">${errors}</label></td>
				</tr>
				<tr>
					<th>간단정보
					<th>
					<td><textarea  cols="30" rows = "5" type="text" name="prodOutline" required
						value="${prod.prodOutline}" ></textarea><label id="prodOutline-error" class="error"
						for="prodOutline">${errors}</label></td>
				</tr>
				<tr>
					<th>상세정보
					<th>
					<td><textarea  cols="30" rows = "5" type="text" name="prodDetail" value="${prod.prodDetail}" ></textarea>
					<label
						id="prodDetail-error" class="error" for="prodDetail">${errors}</label></td>
				</tr>
				<tr>
					<th>이미지
					<th>
					<td><input type="file" name="prodImg" required value="${prod.prodImg}" /><label
						id="prodImg-error" class="error" for="prodImg">${errors}</label></td>
				</tr>
				<tr>
					<th>총재고
					<th>
					<td><input type="text" name="prodTotalstock" required
						value="${prod.prodTotalstock}" /><label id="prodTotalstock-error" class="error"
						for="prodTotalstock">${errors}</label></td>
				</tr>
				<tr>
					<th>입고일
					<th>
					<td><input type="date" name="prodInsdate" value="${prod.prodInsdate}" /><label
						id="prodInsdate-error" class="error" for="prodInsdate">${errors}</label></td>
				</tr>
				<tr>
					<th>상품재고
					<th>
					<td><input type="text" name="prodProperstock" required
						value="${prod.prodProperstock}" /><label id="prodProperstock-error" class="error"
						for="prodProperstock">${errors}</label></td>
				</tr>
				<tr>
					<th>크기
					<th>
					<td><input type="text" name="prodSize" value="${prod.prodSize}" /><label
						id="prodSize-error" class="error" for="prodSize">${errors}</label></td>
				</tr>
				<tr>
					<th>색상
					<th>
					<td><input type="text" name="prodColor" value="${prod.prodColor}" /><label
						id="prodColor-error" class="error" for="prodColor">${errors}</label></td>
				</tr>
				<tr>
					<th>주의사항
					<th>
					<td><input type="text" name="prodDelivery" value="${prod.prodDelivery}" /><label
						id="prodDelivery-error" class="error" for="prodDelivery">${errors}</label></td>
				</tr>
				<tr>
					<th>단위
					<th>
					<td><input type="text" name="prodUnit" value="${prod.prodUnit}" /><label
						id="prodUnit-error" class="error" for="prodUnit">${errors}</label></td>
				</tr>
				<tr>
					<th>???
					<th>
					<td><input type="text" name="prodQtyin" value="${prod.prodQtyin}" /><label
						id="prodQtyin-error" class="error" for="prodQtyin">${errors}</label></td>
				</tr>
				<tr>
					<th>수량
					<th>
					<td><input type="text" name="prodQtysale" value="${prod.prodQtysale}" /><label
						id="prodQtysale-error" class="error" for="prodQtysale">${errors}</label></td>
				</tr>
				<tr>
					<th>마일리지
					<th>
					<td><input type="text" name="prodMileage" value="${prod.prodMileage}" /><label
						id="prodMileage-error" class="error" for="prodMileage">${errors}</label></td>
				</tr>
				
				<tr>
					<td colspan="2">
						<input type = "submit" value ="저장"/>
					</td>
				</tr>



				
			
			
		</table>
		</div>
</form>

</body>
<script type="text/javascript">
	
	$('#insertForm').validate({
		message :{
			
			required: "필수 항목입니다."
		} 
		
	});

</script>
<jsp:include page="/includee/footer.jsp"></jsp:include>
</html>