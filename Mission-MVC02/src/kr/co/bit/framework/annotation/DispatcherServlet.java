package kr.co.bit.framework.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet(
		urlPatterns = { "*.do" }, 
		initParams = { 
				@WebInitParam(name = "controllers", 
						value = "kr.co.bit.board.controller.BoardController|kr.co.bit.login.controller.LoginController")
		})
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapping mappings = null;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		String ctrlNames = config.getInitParameter("controllers");
		//System.out.println(ctrlNames);
		
		try {
			mappings = new HandlerMapping(ctrlNames);
			
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI().substring(request.getContextPath().length());
		CtrlAndMethod cam = mappings.getCtrlAndMethod(uri);
		String view="";
		try {
			
			if(cam==null){
				throw new Exception("요청한 uri가 맞지않음");
			}
			
			Object target = cam.getTarget();
			Method method = cam.getMethod();
			
			ModelAndView mav = (ModelAndView)method.invoke(target, request, response);
			view = mav.getView();
			
			//request 공유영역에 객체 등록
			if(mav.getModel()!=null){
				Map<String,Object> model = mav.getModel();
				
				Set<String> keys = model.keySet();
				for(String key : keys){
					request.setAttribute(key, model.get(key));
				}
			}
			
//			Set<Entry<String,Object>> entrySet = model.entrySet();
//			for(Entry<String, Object> entry: entrySet){
//				request.setAttribute(entry.getKey(), entry.getValue());
//			}
				
			
		} catch (Exception e) {
			request.setAttribute("exception", e);
			view = "/error/error.jsp";
		}
		if(view.startsWith("redirect")){
			view = view.substring("redirect:".length());
			response.sendRedirect(view);
		}else{
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
			
		
		
		
	}

}
