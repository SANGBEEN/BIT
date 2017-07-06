package kr.co.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import kr.co.bit.member.dao.MemberDAO;
import kr.co.bit.member.vo.MemberVO;

public class LoginProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		MemberVO member = new MemberVO();
		MemberDAO dao = new MemberDAO();
		member = dao.Login(id, password);
		HttpSession session = request.getSession();
		String msg = null;
		String url = null;
		if(member == null){
			msg = "���̵� �Ǵ� �н����尡 �߸��Ǿ����ϴ�.";
			url = request.getContextPath()+"/login/login.do";
		}else{
			session.setAttribute("member", member);
			url= request.getContextPath();
			switch(member.getType()){
			case "S":
				msg="�����ڴ� ȯ���մϴ�.";
				break;
			case "U":
				msg = member.getName()+"�� ȯ���մϴ�.";
				break;
			default:
				
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/jsp/login/loginProcess.jsp";
	}
	

}
