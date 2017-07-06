package kr.co.bit.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class HandlerMapping {
	private Map<String, Controller> mappings = null;
	
	public HandlerMapping(String configName){
		mappings = new HashMap<>();
		
		Properties prop = new Properties();

		try {
//			InputStream  inStream = new FileInputStream("C:\\henry\\eclipse-work\\Mission-MVC01\\bean.properties");
			InputStream  inStream = new FileInputStream(configName);

			prop.load(inStream);
			
			Set<Object> keys = prop.keySet();
			
			for(Object key : keys){
				String className = prop.getProperty(key.toString());
				Class<?> clz = Class.forName(className);
				
				mappings.put(key.toString(), (Controller)clz.newInstance());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		mappings.put("/board/list.do", new ListController());
//		mappings.put("/board/detail.do", new DetailController());
//		mappings.put("/board/writeForm.do", new WriteFormController());
//		mappings.put("/board/write.do", new WriteController());
//		mappings.put("/board/remove.do", new RemoveController());
//		mappings.put("/login/login.do", new LoginController());
//		mappings.put("/login/loginProcess.do", new LoginProcessController());
//		mappings.put("/login/logout.do", new LogoutController());
	
	}
	
	public Controller getController(String uri){
		
		return mappings.get(uri);
	}
}
