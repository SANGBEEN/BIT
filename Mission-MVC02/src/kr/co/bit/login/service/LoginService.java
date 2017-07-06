package kr.co.bit.login.service;

import kr.co.bit.member.dao.MemberDAO;
import kr.co.bit.member.vo.MemberVO;

public class LoginService {
	private MemberDAO dao;

	public LoginService(MemberDAO dao) {
		this.dao = dao;
	}
	public MemberVO Login(String id, String password){
		return dao.Login(id, password);
	}
	
}
