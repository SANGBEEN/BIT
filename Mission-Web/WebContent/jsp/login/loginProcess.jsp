<%@page import="kr.co.bit.member.dao.MemberDAO"%>
<%@page import="kr.co.bit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	MemberVO member = new MemberVO();
	MemberDAO dao = new MemberDAO();
	member = dao.Login(id, password);
	String msg = null;
	String url = null;
	if(member == null){
		msg = "아이디 또는 패스워드가 잘못되었습니다.";
		url = "/Mission-Web/jsp/login/login.jsp";
	}else{
		session.setAttribute("member", member);
		url="/Mission-Web";
		switch(member.getType()){
		case "S":
			msg="관리자님 환영합니다.";
			break;
		case "U":
			msg = member.getName()+"님 환영합니다.";
			break;
		default:
			
		}
	}
	pageContext.setAttribute("msg", msg);
	pageContext.setAttribute("url", url);
%>
<script>
alert('${msg}');
location.href='${url}';
<%-- 	alert("<%= msg %>");
	location.href="<%= url%>"; --%>
</script>