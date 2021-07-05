<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
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
<h1>회원 목록 조회</h1>
<%
	List<MemberVO> memberList = (List)request.getAttribute("memberList");
	String tds = "<td>%s</td>";
%>
	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
		   <th>지역</th>
		   <th>휴대폰</th>
		   <th>이메일</th>
		   <th>마일리지</th>
		    
		</tr>
		<tbody>
		<%
		if(memberList == null){
			out.print("회원이 없습니다");
		}else{
			for(int i = 0; i <memberList.size(); i++){
				
				out.print("<tr id = '" + memberList.get(i).getMemId() +"' data-toggle='modal' data-target='#myModal'>");
				out.print(String.format(tds, memberList.get(i).getMemId()));
				out.print(String.format(tds, memberList.get(i).getMemName()));
				out.print(String.format(tds, memberList.get(i).getMemAdd1()));
				out.print(String.format(tds, memberList.get(i).getMemHp()));
				out.print(String.format(tds, memberList.get(i).getMemMail()));
				out.print(String.format(tds, memberList.get(i).getMemMileage()));
				out.print("</tr>");
			}
		}
		%>
		<tr id="13123123123">
			<td>
				test
			</td>
		</tr>
		</tbody>
	
	</table>	
	
	
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
	
	
	
	
	
	
	
	
	
<script type="text/javascript">
	$(function(){
		
			let exampleModal = $("#exampleModal").modal({
				show : false
			}).on('show.bs.modal', function(event){
				console.log(event.relatedTarget)
				let trTag = event.relatedTarget;
				if(!trTag){
					return false;
				}
				let mem_id = trTag.id;
				 $.ajax({
					url : "<%=request.getContextPath()%>/member/memberView.do",
					data : {
						'who' : mem_id
					},
					dataType : "html",
					success : function(resp) {
						exampleModal.find(".modal-body").html(resp);
					},
					error : function(errorResp) {

					}

				}); 
				
			}).on('hidden.bs.modal',function(){
				exampleModal.find(".modal-body").empty();
			});
		
			$("tbody").on("click","tr[id]", function(){
				let mem_id = this.id;
				// json구조 사용하지 말것
				let url = "<%=request.getContextPath()%>/member/memberView.do";
				let data = {
						"who" : mem_id
				}
				
				exampleModal.modal('show', this);
				
			}).css("cursor", "pointer");
		
	})
</script>
<jsp:include page="/includee/footer.jsp"></jsp:include>
</body>
</html>









