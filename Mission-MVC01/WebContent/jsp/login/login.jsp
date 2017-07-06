<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/Mission-MVC01/css/layout.css" />
<link rel="stylesheet" href="/Mission-MVC01/css/board.css" />
<script src="/Mission-Web/js/checkForm.js"></script>
<script>
/* 	function isNull(obj,msg){
		if(obj.value==""){
			alert(msg);
			obj.focus();
			return true;
		}
		return false;
	} */
	function chkForm(){
		var form = document.lform;
		if(isNull(form.id,'아이디를 입력하세요')){
			return false;
		}
		if(isNull(form.password,'패스워드를 입력하세요')){
			return false;
		}

		return true;
	}
</script>
</head>
<body>
	<header>
		<jsp:include page="/jsp/include/topMenu.jsp" />
	</header>
	<section>
	<div align="center">
		<hr />
		<h2>로그인</h2>
		<hr />
		<br />
		
		<form action="<%=request.getContextPath() %>/login/loginProcess.do" method="post" name = "lform" onsubmit="return chkForm()">
			<table width="40%">
			<tr>
				<th>ID</th>
				<td><input type="text" name="id" size="20" /></td>
			</tr>
			<tr>
				<th>PASSWORD</th>
				<td><input type="password" name="password" size="20" /></td>
			</tr>
			
			</table>
			<br />
			<input type="submit" value="로그인" />
		</form>
	</div>
	</section>
	<footer>
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer>
	
</body>
</html>