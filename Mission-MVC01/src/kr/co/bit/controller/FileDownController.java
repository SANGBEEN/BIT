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
		 
		    // ���� ���ε�� ���
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
		        // ������ �о� ��Ʈ���� ���
		        try{
		            file = new File(path, fileSaveName);
		            in = new FileInputStream(file);
		        }catch(FileNotFoundException fe){
		            skip = true;
		        }

		        client = request.getHeader("User-Agent");
		 		
		        // ���� �ٿ�ε� ��� ����
		        response.reset() ;
		        response.setContentType("application/octet-stream");
		        response.setHeader("Content-Description", "JSP Generated Data");
		 

		        if(!skip){

		            // IE
		            if(client.indexOf("MSIE") != -1){
		                response.setHeader ("Content-Disposition", "attachment; filename="+new String(fileOriName.getBytes("KSC5601"),"ISO8859_1"));
		 
		            }else{
		                // �ѱ� ���ϸ� ó��
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
		            //out.println("<script language='javascript'>alert('������ ã�� �� �����ϴ�');history.back();</script>");
		 
		        }
		         
		        in.close();
		        os.close();
		 
		    }catch(Exception e){
		      e.printStackTrace();
		    }
		return null;
	}
	

}
