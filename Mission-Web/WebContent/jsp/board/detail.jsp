<%@page import="kr.co.bit.board.vo.BoardFileVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.bit.board.vo.BoardVO"%>
<%@page import="kr.co.bit.board.dao.BoardDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.bit.util.ConnectionFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	/*
		1.상세보기할 게시글 번호 추출
		2.디비에서 게시물 번호의 게시물 3조회
		3.화면에 출력
	*/
	int no = Integer.parseInt(request.getParameter("no"));
	boolean flag = Boolean.parseBoolean(request.getParameter("flag"));
	BoardDAO dao = new BoardDAO();
	BoardVO board = new BoardVO();
	board = dao.detailBoard(no,flag);
	List<BoardFileVO> fileList = dao.selectFileByNo(no);
	
	pageContext.setAttribute("board", board);
	pageContext.setAttribute("fileList", fileList);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>

function doAction(type){
	switch(type){
	case 'U':
		location.href = "/Mission-Web/jsp/board/updateForm.jsp?no="+${param.no};
		break;
	case 'D':
		if(confirm("${param.no}번 게시물을 삭제하시겠습니까?"))
			location.href = "remove.sjp?no=${param.no}";
		break;
	case 'L':
		location.href = "list.jsp";
		break;
	}
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
		<hr width="80%" />
		<h2>상세 페이지</h2>
		<hr width="80%" />
	</div>
	<table width="80%" border="1">
		<tr>
			<th width="25%">번호</th>
			<td>${param.no }</td>
		</tr>
		<tr>
			<th width="25%">제목</th>
			<td><c:out value="${board.title }"></c:out> </td>
		</tr>
			<tr>
			<th>작성자</th>
			<td>${ board.writer }</td>
		</tr>
		<tr>
			<th width="25%">내용</th>
			<td>${board.content }</td>
		</tr>
		<tr>
			<th width="25%">조회수</th>
			<td>${board.viewCnt }</td>
		</tr>
		<tr>
			<th width="25%">날짜</th>
			<td>${board.regDate }</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:forEach items="${ fileList }" var="file">
					<%-- <a href="/Mission-Web/upload/${ file.fileSaveName }"> --%>
					 <a href="filedown.jsp?no=${file.boardNo }&fileSaveName=${file.fileSaveName}&fileOriName=${file.fileOriName}">
					${ file.fileOriName }
					</a>
					(${ file.fileSize } bytes)<br/>
				</c:forEach>
			</td>
		</tr>
	</table>
		<input type="button" value="목록" onclick="doAction('L')" />
		<c:if test="${(board.writer==sessionScope.member.id) or (sessionScope.member.type == 'S') }">
			<input type="button" value="삭제" onclick="doAction('D')" />
			<input type="button" value="수정" onclick="doAction('U')" />
		</c:if>
	</section>
	<footer>
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer>
</body>
</html>