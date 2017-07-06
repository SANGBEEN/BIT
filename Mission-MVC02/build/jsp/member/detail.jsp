<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath()%>/css/layout.css" />
<link rel="stylesheet" href="<%= request.getContextPath()%>/css/board.css" />
<script>
	function doAction(type){
	switch(type){
	case 'U':
		location.href = "<%=request.getContextPath()%>/member/updateForm.do";
		break;
	case '/':
		location.href = "<%=request.getContextPath()%>";
		break;

	}
}
</script>
</head>
<body>
	<header>
		<jsp:include page="/jsp/include/topMenu.jsp" />
	</header>
	<section>
		<div align="center">
			<hr width="80%" />
			<h2>마이페이지</h2>
			<hr width="80%" />
		</div>
		<table width="80%" border="1">
		<tr>
			<th width="25%">이름</th>
			<td>${sessionScope.member.name}</td>
		</tr>
		<tr>
			<th width="25%">id</th>
			<td><c:out value="${sessionScope.member.id}"></c:out> </td>
		</tr>
		<tr>
			<th width="25%">주소</th>
			<td>${sessionScope.member.basicAddr}  ${sessionScope.member.detailAddr}</td>
		</tr>
		<tr>
			<th width="25%">전화번호</th>
			<td>${sessionScope.member.tel1}-${sessionScope.member.tel2}-${sessionScope.member.tel3}</td>
		</tr>
		<tr>
			<th width="25%">가입날짜</th>
			<td>${sessionScope.member.regDate}</td>
		</tr>
		<tr>
			<th width="25%">email</th>
			<td>${sessionScope.member.emailId}@${sessionScope.member.emailDomain }</td>
		</tr>
		<tr>
			<th width="25%">post</th>
			<td>${sessionScope.member.post}</td>
		</tr>
	</table>
	
	<input type="button" value="수정" onclick="doAction('D')" />
	<input type="button" value="처음으로" onclick="doAction('/')" />

	</section>
	<footer>
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer>
</body>
</html>