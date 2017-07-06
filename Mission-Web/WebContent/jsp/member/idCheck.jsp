<%@page import="kr.co.bit.member.dao.MemberDAO"%>
<%@page import="kr.co.bit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	MemberVO member = new MemberVO();
	MemberDAO dao = new MemberDAO();
	
	boolean result = dao.selectMemberById(id);
	
	//pageContext.setAttribute("result", result);
%>
{"result":"<%=result %>"}