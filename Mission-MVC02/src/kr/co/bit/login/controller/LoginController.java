package kr.co.bit.login.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.framework.annotation.Controller;
import kr.co.bit.framework.annotation.ModelAndView;
import kr.co.bit.framework.annotation.RequestMapping;
import kr.co.bit.login.service.LoginService;
import kr.co.bit.member.vo.MemberVO;

@Controller
public class LoginController {
	/**
	 * 로그인폼
	 */
	@RequestMapping("/login/login.do")
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("로그인폼");
		ModelAndView mav = new ModelAndView();

		mav.setView("/jsp/login/login.jsp");
		
		return mav;
	}
	
	/**
	 * 로그인
	 */
	@RequestMapping("/login/loginProcess.do")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("로그인");
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String check = request.getParameter("saveId");
		
		Cookie cookie = new Cookie("saveId", URLEncoder.encode(id,"utf-8"));
		if(check!=null){
			cookie.setMaxAge(60*2);
		}else{
			cookie.setMaxAge(0);
		}
		response.addCookie(cookie);
		
		ServletContext sc = request.getServletContext();
		LoginService service = (LoginService)sc.getAttribute("loginService");
		
		MemberVO member= service.Login(id, password);
		
		ModelAndView mav = new ModelAndView();
		
		if(member!=null){
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			mav.setView("/jsp/login/loginProcess.jsp");
		}else{
			mav.setView("redirect:"+request.getContextPath()+"/board/list.do");
		}
		
		return mav;
	}
	
	/**
	 * 로그아웃
	 */
	@RequestMapping("/login/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){

		HttpSession session = request.getSession();
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setView("redirect:"+request.getContextPath());
		
		return mav;
		
	}
}
