package kr.co.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.vo.BoardVO;

public class UpdateController implements Controller{
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int no =Integer.parseInt(request.getParameter("no"));
		String title=request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardVO board = new BoardVO();
		BoardDAO dao = new BoardDAO();

		board.setNo(no);
		board.setTitle(title);
		board.setContent(content);
		dao.updateBoard(board);
		
		request.setAttribute("board", board);
		return "/jsp/board/update.jsp";
		
	}
}
