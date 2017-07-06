package kr.co.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.member.dao.MemberDAO;
import kr.co.bit.member.vo.MemberVO;

public class IdCheckController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		MemberVO member = new MemberVO();
		MemberDAO dao = new MemberDAO();
		
		boolean result = dao.selectMemberById(id);
		System.out.println(result);
		request.setAttribute("result", result);
		return "/jsp/member/idCheck.jsp";
	}
	

}
