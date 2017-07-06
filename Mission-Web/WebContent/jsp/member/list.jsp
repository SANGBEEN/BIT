<%@page import="org.apache.catalina.ant.SessionsTask"%>
<%@page import="kr.co.bit.member.dao.MemberDAO"%>
<%@page import="kr.co.bit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.bit.util.ConnectionFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	
	MemberVO m = (MemberVO)session.getAttribute("member");
	List<MemberVO> list = new ArrayList<>();
	MemberDAO dao = new MemberDAO();
	if(m.getType().equals("S")){
		list = dao.selectAllMember();	
		pageContext.setAttribute("list", list);
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>

function doList() {
	location.href = "../board/list.jsp";
	
}

</script>
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
 	<%-- 	<% 
			while(rs.next()) {
		%>
		<tr>
			<td><%= rs.getString("id") %></td>
			<td><%= rs.getString("name")%></td>
			<td><%= rs.getString("email_id") %></td>
			<td><%= rs.getString("tel1")%>-<%=rs.getString("tel2")%>-<%=rs.getString("tel3")%></td>
			<td><%= rs.getString("basic_addr") %></td>
		</tr>
		<%
			}
		%>  --%>
	</table>
	<br/><br/>
	<input type="button" value="뒤로" onclick="doList()"/>
	</div>
	</section>
	<footer>
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer>

</body>
</html>