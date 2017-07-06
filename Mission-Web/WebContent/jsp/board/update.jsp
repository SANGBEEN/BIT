<%@page import="kr.co.bit.board.vo.BoardVO"%>
<%@page import="kr.co.bit.board.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	
	int no =Integer.parseInt(request.getParameter("no"));
	String title=request.getParameter("title");
	String content = request.getParameter("content");
	
	BoardVO board = new BoardVO();
	BoardDAO dao = new BoardDAO();

	board.setNo(no);
	board.setTitle(title);
	board.setContent(content);
	dao.updateBoard(board);
	
	pageContext.setAttribute("board", board);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script>
	alert("수정완료");
	location.href="detail.jsp?no="+${board.no}+"&flag=false";
</script>
<body>
	
</body>
</html>