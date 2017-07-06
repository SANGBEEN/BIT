<%@page import="java.io.File"%>
<%@page import="kr.co.bit.board.vo.BoardFileVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.bit.board.vo.BoardVO"%>
<%@page import="kr.co.bit.board.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
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
	
%>
<script>
	alert("삭제되었습니다.");
	location.href="list.jsp";
</script>