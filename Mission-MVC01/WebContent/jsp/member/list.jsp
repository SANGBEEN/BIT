<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath()%>/css/layout.css" />
<link rel="stylesheet" href="<%= request.getContextPath()%>/css/board.css" />
</head>
<body>
	<header>
		<jsp:include page="/jsp/include/topMenu.jsp" />
	</header>
	<section>
	<div align="center">
	<br />
	<hr width="80%"/>
		<h2>회원 목록</h2>
	<hr width="80%"/>
	<br/>
	<table border="1" width="80%" >
		<tr>
			<th width="7%">id</th>
			<th width="10%">이름</th>
			<th width="16%">이메일</th>
			<th width="15%">전화번호</th>
			<th width="20%">주소</th>
		</tr>
		<c:forEach var="member" items="${list }">
			<tr>
				<td>${member.id }</td>
				<td>${member.name }</td>
				<td>${member.emailId }</td>
				<td>${member.tel1+member.tel2+member.tel3 }</td>
				<td>${member.basicAddr }</td>
			</tr>
		</c:forEach>
	</table>
	<br/><br/>
	<input type="button" value="뒤로" onclick="javascript:location.href='/Mission-MVC01/index.jsp'"/>
	</div>
	</section>
	<footer>
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer>

</body>
</html>