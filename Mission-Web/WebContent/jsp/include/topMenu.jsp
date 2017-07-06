<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	function addFavorite() {
		try {
			window.external
		    	  .AddFavorite('http://localhost:8000/Mission-Web', '첫번째 나의 웹');
		} catch(e) {
			alert('현재 브라우저에서는 사용할 수 없습니다.\n크롬에서는 ctrl+d를 사용해주세요');
		}
	}
</script>    
    
<table border="1" width="100%">
	<tr>
		<td rowspan="2" style="width:100px; height: 40px;">
			<a href="/Mission-Web">
			<img src="/Mission-Web/images/logo.gif" width="100%" height="100%"/>
			</a>
		</td>
		<td align="right">
			<!-- <a href="javascript:window.external.AddFavorite('http://localhost:8000/Mission-Web', '첫번째 나의 웹')">즐겨찾기</a> -->
			<a href="#" onclick="addFavorite()">즐겨찾기</a>
			<c:if test="${not empty member }">
				[${sessionScope.member.id }님으로 로그인중]
			</c:if>
		</td>
	</tr>
	<tr>
		<td>
			<nav>
			<c:if test="${sessionScope.member.type == 'S' }">
				<a href="/Mission-Web/jsp/member/list.jsp">회원관리</a>||
			</c:if>
			<a href="/Mission-Web/jsp/board/list.jsp">게시판</a>||
			<c:choose>
				<c:when test="${empty sessionScope.member }">
					<a href="/Mission-Web/jsp/member/signupForm.jsp">회원가입</a>||
					<a href="/Mission-Web/jsp/login/login.jsp">로그인</a>||
				</c:when>
				<c:otherwise>
					<a href="/Mission-Web/jsp/member/detail.jsp">마이페이지</a>||
					<a href="/Mission-Web/jsp/login/logout.jsp">로그아웃</a>||
				</c:otherwise>
			</c:choose>
			</nav>
			
		</td>
	</tr>
</table>






