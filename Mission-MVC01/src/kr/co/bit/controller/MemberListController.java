package kr.co.bit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.member.dao.MemberDAO;
import kr.co.bit.member.vo.MemberVO;

public class MemberListController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO m = (MemberVO)session.getAttribute("member");
		List<MemberVO> list = new ArrayList<>();
		MemberDAO dao = new MemberDAO();
		if(m.getType().equals("S")){
			list = dao.selectAllMember();	
			request.setAttribute("list", list);
		}
		return "/jsp/member/list.jsp";
	}
	
}
