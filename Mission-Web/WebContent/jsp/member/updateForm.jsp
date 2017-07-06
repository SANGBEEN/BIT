<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/Mission-Web/css/layout.css" />
<link rel="stylesheet" href="/Mission-Web/css/board.css" />
</head>
<body>
	<header>
		<jsp:include page="/jsp/include/topMenu.jsp" />
	</header>
	<section>
		<div align="center">	
			<br />
			<hr width="80%"/>
			<h2>회원 정보 수정</h2>
			<hr width="80%"/>
		</div>
		<form action="signUp.jsp" method="post">
		<table width="80%" border="1">
			<tr>
				<th width="25%">이름</th>
				<td>이름<input type="text" name="name" required="required" value="${sessionScope.member.name }"/></td>
			</tr>
			<tr>
				<th width="25%">id</th>
				<td>id<input type="text" name="id" required="required" value="${sessionScope.member.id }"/></td>
			</tr>
			<tr>
				<th width="25%">비밀번호</th>
				<td>비밀번호<input type="password" name="password" required="required"/></td>
			</tr>
			<tr>
				<th width="25%">email</th>
				<td>email<input type="text" name="email_id" value="${sessionScope.member.emailId }" />@
				<input type="text" name="email_domain" value="${sessionScope.member.emailDomain }"/></td>
			</tr>
			<tr>
				<th width="25%">핸드폰</th>
				<td>핸드폰<input type="text" name="tel1" value="${sessionScope.member.tel1 }"/>-<input type="text" name="tel2" value="${sessionScope.member.tel2 }"/>-<input type="text" name="tel3" value="${sessionScope.member.tel3 }"/></td>
			</tr>
			<tr>
				<th width="25%">주소</th>
				<td>주소<input type="text" size="10px" name="basic_addr" value="${sessionScope.member.basicAddr }" /></td>
			</tr>
			<tr>
				<th width="25%">상세주소</th>
				<td>상세주소<input type="text" name="detail_addr" value="${sessionScope.member.detailAddr }"/></td>
			</tr>
			
		</table>
		<input type="submit" value="수정" />
		</form>

	</section>
	
	<footer>
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer>
</body>
</html>