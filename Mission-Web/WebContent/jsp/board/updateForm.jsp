<%@page import="kr.co.bit.board.dao.BoardDAO"%>
<%@page import="kr.co.bit.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%
	BoardVO board = new BoardVO();
	BoardDAO dao = new BoardDAO();
	int no =Integer.parseInt(request.getParameter("no"));
	board = dao.detailBoard(no,false);
	request.setAttribute("board", board);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/Mission-Web/js/checkForm.js" ></script>
<script>
	function doList() {
		location.href = "list.jsp";
	}
	
	function doUpdate() {
		location.href = "update.jsp";
	}
	function doWrite() {
		var w = document.writeForm;
		if(isNull(w.title, '제목을 입력하세요'))
			return false;
		
		if(w.content.value == "") {
			alert('내용을 입력하세요');
			w.content.focus();
			return false;
		}
		// 파일 확장자 체크
		if(checkExt(w.attachfile1)) {
			return false;
		}
		if(checkExt(w.attachfile2)) {
			return false;
		}
		
		return true;
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
	<hr width="80%"/>
	<h2>게시글 등록</h2>
	<hr width="80%"/>
	<br/>
	
	<form action="update.jsp" method="post" name="updateForm"
	      onsubmit="return doWrite()">
	      <input type="hidden" name="no" value="${param.no }" />
		<table width="80%" border="1">
			<tr>
				<th width="23%">제목</th>
				<td>
					<input type="text" name="title" size="50" value="${board.title }"/>
				</td>
			</tr>

			<tr>
				<th>내용</th>
				<td>
					<textarea rows="7" cols="50" name="content" style="align:left">${board.content }</textarea>
				</td>
			</tr>
		</table>
		<br/><br/>
		<input type="submit" value="  수정  " />&nbsp;&nbsp;
		<input type="button" value="  취소  " onclick="doList()" />
	</form>
	</div>
	</section>
	<footer>
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer>
</body>
</html>
