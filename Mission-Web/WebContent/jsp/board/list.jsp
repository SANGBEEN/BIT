<%@page import="kr.co.bit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.bit.board.dao.BoardDAO"%>
<%@page import="kr.co.bit.util.JDBCClose"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="kr.co.bit.util.ConnectionFactory"%>
<%@page import="java.sql.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	BoardDAO dao = new BoardDAO();
	List<BoardVO> list = dao.selectAllBoard();
	pageContext.setAttribute("list", list);
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<script>
	function goWriteForm() {
	//	location.href = "/Mission-Web/jsp/board/writeForm.jsp"
		location.href = "writeForm.jsp";
	}
	function goSignUpForm(){
		location.href = "../member/signupForm.jsp";
	}
	function goMemberList(){
		location.href="../member/list.jsp";
	}
	function goLoginForm(){
		location.href="../member/loginForm.jsp";
	}
	function doAction(no){
		<c:choose>
			<c:when test="${not empty sessionScope.member}">
				location.href="detail.jsp?no="+no+"&flag=true";
			</c:when>
			<c:otherwise>
				if(confirm('로그인 후 사용하실수 있습니다.\n 로그인 페이지로 이동하시겠습니까?')){
					location.href="../login/login.jsp";
					
				}else
					location.href="/Mission-Web"
			</c:otherwise>
		</c:choose>
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
	<br/>
	<hr width="80%"/>
		<h2>게시판 목록</h2>
	<hr width="80%"/>
	<br/>
	
	<table border="1" width="80%" >
		<tr>
			<th width="7%">번호</th>
			<th>제목</th>
			<th width="16%">작성자</th>
			<th width="20%">등록일</th>
		</tr>
		<c:forEach var="board" items="${list }">
			<tr>
				<td>${board.no }</td>
				<td>
				<%-- 	<a href="detail.jsp?no=${board.no }" > --%>
						<a href="javascript:doAction('${board.no}')">
							<c:out value="${board.title } "></c:out> 
						</a>
					</a>
				</td>
				<td>${board.writer }</td>
				<td>${board.regDate }</td>
			</tr>			
		</c:forEach>		
	</table>
	<br/><br/>
	<c:if test="${not empty sessionScope.member }">
		<input type="button" value="새글등록" onclick="goWriteForm()"/>
	</c:if>
	<!--  <input type="button" value="회원가입" onclick="goSignUpForm()" />
	 <input type="button" value="회원목록" onclick="goMemberList()" />
	 <input type="button" value="로그인" onclick="goLoginForm()"/> -->
	</div>
	</section>
	<footer>
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer>
</body>
</html>









