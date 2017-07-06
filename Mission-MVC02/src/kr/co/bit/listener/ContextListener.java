package kr.co.bit.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.catalina.tribes.MembershipService;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.service.BoardService;
import kr.co.bit.login.service.LoginService;
import kr.co.bit.member.dao.MemberDAO;

@WebListener
public class ContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("리스너종료");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("리스너시작");
		
		ServletContext sc = event.getServletContext();
		
		BoardDAO boardDao = new BoardDAO();
		MemberDAO memberDao = new MemberDAO();
		
		BoardService boardService = new BoardService(boardDao);
		LoginService loginService = new LoginService(memberDao);
		
		sc.setAttribute("boardService", boardService);
		sc.setAttribute("loginService", loginService);
	}

	
}
