<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<title>Insert title here</title>
</head>
<body>
	<table class = "table">
		<thead>
			<tr>
				<th>no</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>

		<tbody>
			<c:set var="boardList"  value="${paging.dataList}"></c:set>
			<c:choose>
				<c:when test="${empty boardList}">
					<tr>
						<th colspan="5">
							게시글이 존재하지 않습니다
						</th>
					</tr>
				</c:when>
				
				<c:otherwise>
					<c:forEach var="board" items="${boardList}">
						<tr>
							<td>${board.rnum}</td>
							<td>${board.boWriter}</td>
							<td>${board.boTitle}</td>
							<td>${board.boDate}</td>
							<td>${board.boHit}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>	
		
		<tfoot>
				<tr>
					<td colspan="5">
						<div id = "pagingArea">
							${paging.pagingHTML}
						</div>
					</td>
				</tr>
		</tfoot>
	</table>


</body>
<jsp:include page="/includee/footer.jsp"></jsp:include>
</html>