<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	function addFavorite() {
		try {
			window.external
		    	  .AddFavorite('http://localhost:8000/Mission-MVC01', '두번째 나의 웹');
		} catch(e) {
			alert('현재 브라우저에서는 사용할 수 없습니다.\n크롬에서는 ctrl+d를 사용해주세요');
		}
	}
</script>    
    
<table border="1" width="100%">
	<tr>
		<td rowspan="2" style="width:100px; height: 40px;">
			<a href="<%=request.getContextPath() %>/index.jsp">
			<img src="<%=request.getContextPath() %>/images/logo.gif" width="100%" height="100%"/>
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
			<a href="${ pageContext.request.contextPath }/board/list.do">게시판 | </a>
			<c:choose>
				<c:when test="${empty member }">
					<a href="${ pageContext.request.contextPath }/login/login.do">로그인 |</a>
				</c:when>
		
				<c:otherwise>
				<a href="${ pageContext.request.contextPath }/login/logout.do">로그아웃</a>
				</c:otherwise>
			</c:choose>
			<%-- <c:if test="${sessionScope.member.type == 'S' }">
				<a href="<%=request.getContextPath() %>/member/list.do">회원관리</a>||
			</c:if>
			<a href="<%=request.getContextPath() %>/board/list.do">게시판</a>||
			<c:choose>
				<c:when test="${empty sessionScope.member }">
					<a href="<%=request.getContextPath() %>/member/signupForm.do">회원가입</a>||
					<a href="<%=request.getContextPath() %>/login/login.do">로그인</a>||
				</c:when>
				<c:otherwise>
					<a href="<%=request.getContextPath() %>/member/detail.do">마이페이지</a>||
					<a href="<%=request.getContextPath() %>/login/logout.do">로그아웃</a>||
				</c:otherwise>
			</c:choose> --%>
			</nav>
			
		</td>
	</tr>
</table>






