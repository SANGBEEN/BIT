package kr.co.bit.board.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.service.BoardService;
import kr.co.bit.board.vo.BoardVO;
import kr.co.bit.framework.annotation.Controller;
import kr.co.bit.framework.annotation.ModelAndView;
import kr.co.bit.framework.annotation.RequestMapping;

@Controller
public class BoardController {

	/**
	 * 게시글 등록
	 */
	@RequestMapping(value="/board/write.do")
	public void insert(HttpServletRequest request, HttpServletResponse response)throws Exception{
		System.out.println("게시글 등록");
	}
	
	@RequestMapping("/board/list.do")
	public ModelAndView ModelAndView(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("게시글 조회");
		
		ServletContext sc = request.getServletContext();
		BoardService service = (BoardService)sc.getAttribute("boardService");
		List<BoardVO> list = service.selectAllBoard();
		
		ModelAndView mav = new ModelAndView();
		mav.setView("/jsp/board/list.jsp");
		mav.addAttribute("list", list);
		
		return mav;
	}
	
}
