package kr.co.bit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 request.setCharacterEncoding("UTF-8");
		 
		    // 파일 업로드된 경로
		    String path = request.getSession().getServletContext().getRealPath("/upload");
		 
			int no = Integer.parseInt(request.getParameter("no"));
			String fileOriName = request.getParameter("fileOriName");
			String fileSaveName = request.getParameter("fileSaveName");
		   	 
		    InputStream in = null;
		    OutputStream os = null;
		    File file = null;
		    boolean skip = false;
		    String client = "";
		    
		    try{
		        // 파일을 읽어 스트림에 담기
		        try{
		            file = new File(path, fileSaveName);
		            in = new FileInputStream(file);
		        }catch(FileNotFoundException fe){
		            skip = true;
		        }

		        client = request.getHeader("User-Agent");
		 		
		        // 파일 다운로드 헤더 지정
		        response.reset() ;
		        response.setContentType("application/octet-stream");
		        response.setHeader("Content-Description", "JSP Generated Data");
		 

		        if(!skip){

		            // IE
		            if(client.indexOf("MSIE") != -1){
		                response.setHeader ("Content-Disposition", "attachment; filename="+new String(fileOriName.getBytes("KSC5601"),"ISO8859_1"));
		 
		            }else{
		                // 한글 파일명 처리
		                fileOriName = new String(fileOriName.getBytes("utf-8"),"iso-8859-1");
		 
		                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileOriName + "\"");
		            } 
		             
		            response.setHeader ("Content-Length", ""+file.length() );
		 
		 
		       
		            os = response.getOutputStream();
		            byte b[] = new byte[(int)file.length()];
		            int leng = 0;
		             
		            while( (leng = in.read(b)) > 0 ){
		                os.write(b,0,leng);
		            }
		 
		        }else{
		            response.setContentType("text/html;charset=UTF-8");
		            //out.println("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>");
		 
		        }
		         
		        in.close();
		        os.close();
		 
		    }catch(Exception e){
		      e.printStackTrace();
		    }
		return null;
	}
	

}
