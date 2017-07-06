<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$().ready(function(){
		$("#id").keyup(function(){
			console.log("keyup");
			var a = $("#id").val();
			$.ajax({	
				//아이디체크
				url:"idCheck.do",
				type:"GET",
				data:{'id': a},
				success : callback
			});
		});
	});
	function callback(data){
		var d = JSON.parse(data);
		console.log(d);
		console.log(d.result);
		if(d.result==true){
			$("#check").text('이미존재하는아이디');
		}else
			$("#check").text('사용가능'); 

	}
	function doList() {
		location.href = "<%=request.getContextPath()%>/board/list.do";
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
		<br />
		<hr width="80%"/>
		<h2>회원 가입</h2>
		<hr width="80%"/>
		<br/>
		<form action="signUp.do" method="post">
			아이디<input type="text" name="id" id="id" required="required"/><br/>
			<span id="check" ></span>
			<br />
			이름<input type="text" name="name" required="required"/><br />
			비밀번호<input type="password" name="pass" required="required"/><br />
			email<input type="text" name="email_id" />@
			<input type="text" name="email_domain" /><br />
			핸드폰번호 <input type="tel" name="tel1" size="3px" maxlength="3" pattern="\d{3}"/>
			- <input type="tel" name="tel2" size="4px" maxlength="4" pattern="\d{3,4}" />	
			- <input type="tel" name="tel3" size="4px" maxlength="4" pattern="\d{4}" />	<br />
			주소<input type="text" size="10px" name="basic_addr"  /><br />
			상세주소<input type="text" size="10px" name="detail_addr" /><br />
			<input type="submit" value="제출" />
			<input type="button" value="취소" onclick="doList()"/>
		</form>

	</div>
	</section>
	
	<footer>
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer>
</body>
</html>