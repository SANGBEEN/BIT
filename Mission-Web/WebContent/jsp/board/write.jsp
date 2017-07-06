<%@page import="kr.co.bit.board.vo.BoardFileVO"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@page import="kr.co.bit.util.BitFileNamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="kr.co.bit.member.vo.MemberVO"%>
<%@page import="kr.co.bit.board.vo.BoardVO"%>
<%@page import="kr.co.bit.board.dao.BoardDAO"%>
<%@page import="kr.co.bit.util.JDBCClose"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.bit.util.ConnectionFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");
	String saveFolder = "C:\\henry\\eclipse-work\\Mission-Web\\WebContent\\upload";

	MultipartRequest multi = 
			new MultipartRequest(request, saveFolder,1024*1024*3, "utf-8",new BitFileNamePolicy());
	
	MemberVO member = new MemberVO();
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

%>    
    
<script>
	alert('게시글을 저장하였습니다');
	location.href="list.jsp";
</script>