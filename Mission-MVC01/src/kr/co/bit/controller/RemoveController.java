package kr.co.bit.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.vo.BoardFileVO;
import kr.co.bit.board.vo.BoardVO;

public class RemoveController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));
		BoardDAO dao = new BoardDAO();
		BoardVO board = new BoardVO();
		List<BoardFileVO> filelist = new ArrayList<>();
		filelist = dao.selectFileByNo(no);
		for(BoardFileVO file : filelist){
			String path = request.getSession().getServletContext().getRealPath("/upload/"+file.getFileSaveName());
			File f = new File(path);
			System.out.println(f);
			if(f.exists())f.delete();
		}
		
		dao.deleteBoard(no);
		return "/jsp/board/remove.jsp";
	}
	

}
