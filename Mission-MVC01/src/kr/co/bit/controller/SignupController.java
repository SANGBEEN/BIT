package kr.co.bit.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.member.dao.MemberDAO;
import kr.co.bit.member.vo.MemberVO;
import kr.co.bit.util.ConnectionFactory;
import kr.co.bit.util.JDBCClose;

public class SignupController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setName(name);
		member.setPassword(pass);
		member.setEmailId(email_id);
		member.setEmailDomain(email_domain);
		member.setTel1(tel1);
		member.setTel2(tel2);
		member.setTel3(tel3);
		member.setBasicAddr(basic_addr);
		member.setDetailAddr(detail_addr);
		
		MemberDAO dao = new MemberDAO();
		dao.signUp(member);
		
		
		
		return "/jsp/member/signUp.jsp";
	}
	

}
