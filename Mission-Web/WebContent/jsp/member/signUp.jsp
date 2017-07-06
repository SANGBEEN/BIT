<%@page import="java.util.Set"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.bit.util.*"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 
 	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String pass= request.getParameter("pass");
	String email_id= request.getParameterMap().containsKey("email_id") ? request.getParameter("email_id") : "";
	String email_domain= request.getParameterMap().containsKey("email_domain") ? request.getParameter("email_domain") : "";	
	String tel1= request.getParameterMap().containsKey("tel1") ? request.getParameter("tel1") : "";
	String tel2= request.getParameterMap().containsKey("tel2") ? request.getParameter("tel2") : "";
	String tel3= request.getParameterMap().containsKey("tel3") ? request.getParameter("tel3") : "";
	String basic_addr  = request.getParameterMap().containsKey("basic_addr")  ? request.getParameter("basic_addr"):"";
	String detail_addr = request.getParameterMap().containsKey("detail_addr") ? request.getParameter("detail_addr"):"";	
	
	System.out.println(tel1);
	
	Connection conn = new ConnectionFactory().getConnection();
	StringBuilder sql = new StringBuilder();
	sql.append(" insert into t_member(id,name,password,email_id,email_domain,tel1,tel2,tel3,basic_addr,detail_addr,type,reg_date)");
	sql.append(" values(?,?,?,?,?,?,?,?,?,?,'U',sysdate)");
	PreparedStatement stmt = conn.prepareStatement(sql.toString()); 
	
	stmt.setString(1, id);
	stmt.setString(2, name);
	stmt.setString(3, pass);
	stmt.setString(4, email_id);
	stmt.setString(5, email_domain);
	stmt.setString(6, tel1);
	stmt.setString(7, tel2);
	stmt.setString(8, tel3);
	stmt.setString(9, basic_addr);
	stmt.setString(10,detail_addr);
	
	stmt.executeUpdate();
	JDBCClose.close(conn, stmt);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>

</script>
</head>
<body>
<script>
	alert('회원가입성공');
	location.href="../board/list.jsp";
</script>

</body>
</html>