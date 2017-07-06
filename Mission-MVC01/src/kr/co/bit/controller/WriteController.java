package kr.co.bit.controller;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.vo.BoardFileVO;
import kr.co.bit.board.vo.BoardVO;
import kr.co.bit.member.vo.MemberVO;
import kr.co.bit.util.BitFileNamePolicy;

public class WriteController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String saveFolder = "C:\\henry\\eclipse-work\\Mission-MVC01\\WebContent\\upload";

		MultipartRequest multi = 
				new MultipartRequest(request, saveFolder,1024*1024*3, "utf-8",new BitFileNamePolicy());
		
		MemberVO member = new MemberVO();
		HttpSession session = request.getSession();
		member = (MemberVO)session.getAttribute("member");

		String writer = multi.getParameter("writer");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		
		BoardDAO dao = new BoardDAO();
	    BoardVO board = new BoardVO();
	    
	    int no = dao.selectNo();
	    
	    board.setNo(no);
	    board.setTitle(title);
	    board.setWriter(writer);
	    board.setContent(content);
	    System.out.println(board);
	    dao.insertBoard(board);
	    
	    //첨부파일 저장
	    Enumeration files = multi.getFileNames();
		while(files.hasMoreElements()) {
			String fileName = (String)files.nextElement();
			
			File f = multi.getFile(fileName);
			if(f != null) {
				
				String fileOriName = multi.getOriginalFileName(fileName);
				String fileSaveName = multi.getFilesystemName(fileName);
				int fileSize = (int)f.length();
				
				BoardFileVO fileVO = new BoardFileVO();
				fileVO.setBoardNo(no);
				fileVO.setFileOriName(fileOriName);
				fileVO.setFileSaveName(fileSaveName);
				fileVO.setFileSize(fileSize);
				
				dao.insertFile(fileVO);
			}
		}
		return "/jsp/board/write.jsp";
	}
	

}
