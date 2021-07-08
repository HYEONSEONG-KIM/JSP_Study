<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	ProdVO prod = (ProdVO)request.getAttribute("prod");
	List<MemberVO> member = prod.getMemberList();
%>
<table class = 'table'>

		<tr>
			<th>상품 분류 번호</th>
			<td><%=prod.getProdId()%></td>
		</tr>
		<tr>
			<th>상품 이름</th>
			<td><%=prod.getProdName()%></td>
		</tr>
		<tr>
			<th>상품 분류명</th>
			<td><%=prod.getLprodNm()%></td>
		</tr>
		<tr>
			<th>거래처 명</th>
			<td><%=prod.getBuyer().getBuyerName()%></td>
		</tr>
		<tr>
			<th>구입 가격</th>
			<td><%=prod.getProdCost()%></td>
		</tr>
		<tr>
			<th>판매 가격</th>
			<td><%=prod.getProdPrice()%></td>
		</tr>
		
		<tr>
			<th>간단 정보</th>
			<td><%=prod.getProdOutline()%></td>
		</tr>
		<tr>
			<th>상세 정보</th>
			<td><%=prod.getProdDetail()%></td>
		</tr>
		
	
		<tr>
			<th>입고일</th>
			<td><%=prod.getProdInsdate()%></td>
		</tr>
		
		<tr>
			<th>상품 크기</th>
			<td><%=prod.getProdSize()%></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><%=prod.getProdColor()%></td>
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
						<%
							for(MemberVO mem : member){
						%>	
								<tr>
									<td><%=mem.getMemId() %></td>
									<td><%=mem.getMemName() %></td>
									<td><%=mem.getMemHp() %></td>
									<td><%=mem.getMemMail() %></td>
									<td><%=mem.getMemMileage() %></td>
								</tr>
						
						<% 		
							}
						
						%>
					</tbody>
				
				</table>
			</td>
		</tr>
	</table>


</body>
</html>
<jsp:include page="/includee/footer.jsp"></jsp:include>