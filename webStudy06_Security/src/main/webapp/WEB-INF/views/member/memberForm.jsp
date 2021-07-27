<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>

<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.min.js"></script>


<form:form commandName="member" id="memberForm" method="post" enctype="multipart/form-data">
	<table class="table table-bordered">
		<tr>
			<th><spring:message code="member.memId"/></th>
			<td>
				<form:input path="memId" cssClass="form-control" />
				<form:errors path="memId" cssClass="error" element = "label" id = "memId-error" for = "memId"/>
			</td>
		</tr>
		<tr>
			<th>비밀 번호</th>
			<td>
				<input type = "password" name = "memPass" class = "form-control" >
				<%-- <form:input path="memPass" cssClass="form-control" /> --%>
				<form:errors path="memPass" cssClass="error" element = "label" id = "memPass-error" for = "memPass"/>
				
			</td>
		</tr>
		<tr>
			<th>회원 명</th>
			<td>
				<form:input path="memName" cssClass="form-control" />
				<form:errors path="memName" cssClass="error" element = "label" id = "memName-error" for = "memName"/>
			
				
			</td>
		</tr>
		<tr>
			<th>회원이미지</th>
			<td>
				<input type="file" name="memImage" accept="image/*" />
				<label id="memImage-error" class="error" for="memImage">
					${errors['memImage']}
				</label>
			</td>
		</tr>
		<tr>
			<th>주민등록번호1</th>
			<td>
				<form:input path="memRegno1" cssClass="form-control" />
				<form:errors path="memRegno1" cssClass="error" element = "label" id = "memRegno1-error" for = "memRegno1"/>
			
			</td>
		</tr>
		<tr>
			<th>주민등록번호2</th>
			<td>
				<form:input path="memRegno2" cssClass="form-control" />
				<form:errors path="memRegno2" cssClass="error" element = "label" id = "memRegno2-error" for = "memRegno2"/>
			
			</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>
			
				<form:input path="memBir" cssClass="form-control" />
				<form:errors path="memBir" cssClass="error" element = "label" id = "memBir-error" for = "memBir"/>
				
		
			</td>
		</tr>
		<tr>
			<th>우편 번호</th>
			<td>
				<div class="input-group">
					<input class="form-control" type="text" name="memZip" id="memZip" value="${member.memZip}" />
					<button id="zipBtn" type="button" class="btn btn-primary">우편번호 검색</button>
					<label id="memZip-error" class="error" for="memZip">
						${errors['memZip']}
					</label>
				</div>
			</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>
				<input class="form-control" type="text" name="memAdd1" id="memAdd1" value="${member.memAdd1}" />
				<label id="memAdd1-error" class="error" for="memAdd1">
					${errors['memAdd1']}
				</label>
			</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>
				<input class="form-control" type="text" name="memAdd2" id="memAdd2" value="${member.memAdd2}" />
				<label id="memAdd2-error" class="error" for="memAdd2">
					${errors['memAdd2']}
				</label>
			</td>
		</tr>
		<tr>
			<th>집 전화 번호</th>
			<td>
				<form:input path="memHometel" cssClass="form-control" />
				<form:errors path="memHometel" cssClass="error" element = "label" id = "memHometel-error" for = "memHometel"/>
				
			</td>
		</tr>
		<tr>
			<th>회사 전화 번호</th>
			<td>
				<form:input path="memComtel" cssClass="form-control" />
				<form:errors path="memComtel" cssClass="error" element = "label" id = "memComtel-error" for = "memComtel"/>
				
			</td>
		</tr>
		<tr>
			<th>이동 전화 번호</th>
			<td>
				<form:input path="memHp" cssClass="form-control" />
				<form:errors path="memHp" cssClass="error" element = "label" id = "memHp-error" for = "memHp"/>
				
			</td>
		</tr>
		<tr>
			<th>이메일 주소</th>
			<td>
				<form:input path="memMail" cssClass="form-control" />
				<form:errors path="memMail" cssClass="error" element = "label" id = "memMail-error" for = "memMail"/>
			
			</td>
		</tr>
		<tr>
			<th>직업</th>
			<td>
				<form:input path="memJob" cssClass="form-control" />
				<form:errors path="memJob" cssClass="error" element = "label" id = "memJob-error" for = "memJob"/>
			
			</td>
		</tr>
		<tr>
			<th>취미</th>
			<td>
				<form:input path="memLike" cssClass="form-control" />
				<form:errors path="memLike" cssClass="error" element = "label" id = "memLike-error" for = "memLike"/>
				
			</td>
		</tr>
		<tr>
			<th>기념일 명</th>
			<td>
				<form:input path="memMemorial" cssClass="form-control" />
				<form:errors path="memMemorial" cssClass="error" element = "label" id = "memMemorial-error" for = "memMemorial"/>
				
			</td>
		</tr>
		<tr>
			<th>기념일 날짜</th>
			<td>
				<form:input path="memMemorialday" cssClass="form-control" />
				<form:errors path="memMemorialday" cssClass="error" element = "label" id = "memMemorialday-error" for = "memMemorialday"/>
			
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="저장" class="btn btn-primary"/> 
				<input type="reset" value="취소" class="btn btn-secondary"/>
			</td>
		</tr>
	</table>
</form:form>
<script src="${pageContext.request.contextPath }/resources/js/searchZip.js"></script>
<script type="text/javascript">
	$(function(){
		/* $("#zipBtn").searchZip({
			zipCodeTag : "#memZip",
			add1Tag : "#memAdd1",
			add2Tag : "#memAdd2",
			searchURL : "${pageContext.request.contextPath }/zip/searchZip.do"
		});
		 */
		let validateOptions = {};
		<c:if test="${command eq 'INSERT'}">
			validateOptions.rules={
				memId:{
					remote:"${pageContext.request.contextPath }/member/idCheck.do"
				}
			}
			validateOptions.messages={
				memId:{
					remote:"아이디 중복"
				}
			}
		</c:if>
		$("#memberForm").validate(validateOptions);
	});
</script>













