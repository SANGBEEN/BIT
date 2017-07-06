<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/Mission-MVC01/js/checkForm.js" ></script>
<title>Insert title here</title>
<script>
	function doList() {
		location.href = "<%=request.getContextPath()%>/board/list.do";
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
<link rel="stylesheet" href="<%= request.getContextPath()%>/css/layout.css" />
<link rel="stylesheet" href="<%= request.getContextPath()%>/css/board.css" />
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
	
	<form action="<%= request.getContextPath()%>/board/write.do" method="post" name="writeForm"
	      onsubmit="return doWrite()" enctype="multipart/form-data">
		<table width="80%" border="1">
			<tr>
				<th width="23%">제목</th>
				<td>
					<input type="text" name="title" size="50" />
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${sessionScope.member.id }<input type="hidden" name="writer" value="${sessionScope.member.id }" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="7" cols="50" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td>
					<input type="file" name="attachfile1" size="40"/><br/>
					<input type="file" name="attachfile2" size="40"/><br/>
				</td>
			</tr>
		</table>
		<br/><br/>
		<input type="submit" value="  등록  " onclick="doWrite()" />&nbsp;&nbsp;
		<input type="button" value="  목록  " onclick="doList()" />
	</form>
	</div>
	</section>
	<footer>
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer>
</body>
</html>
