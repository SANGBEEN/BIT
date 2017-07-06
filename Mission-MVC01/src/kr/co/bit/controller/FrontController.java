package kr.co.bit.controller;

import java.io.IOException;

import javax.net.ssl.HandshakeCompletedListener;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	
	private HandlerMapping mappings = null;
	
	public void init(ServletConfig config) throws ServletException{
		String configName = config.getInitParameter("configName");
		mappings = new HandlerMapping(configName);
	}
	public void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String context = request.getContextPath();
		uri = uri.substring(context.length());
		System.out.println("��û uri : " + uri);
		
		try {
//			String callPage = "";
//			Controller controller = null;
//			switch (uri) {
//			case "/list.do":
//				controller = new ListController();
//				break;
//			case "/write.do":
//				controller = new WriteFormController();
//				break;
//			}
			//HandlerMapping mappings = new HandlerMapping();
			Controller controller = mappings.getController(uri);
			String callPage = controller.handleRequest(request, response);
			if(callPage!=null){
				RequestDispatcher dispatcher = request.getRequestDispatcher(callPage);
				dispatcher.forward(request, response);
			}
			
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}
}











