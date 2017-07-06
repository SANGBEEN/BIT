package kr.co.bit.framework.annotation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {
	
	public Map<String, CtrlAndMethod> mappings = null;
	
	public HandlerMapping(String ctrlNames)throws Exception{
		mappings = new HashMap<>();
		String[] ctrls = ctrlNames.split("\\|");
		
		for(String ctrl : ctrls){
			Class<?> clz = Class.forName(ctrl.trim());
			Object target = clz.newInstance();
			//System.out.println("target : "+target);
			
			Method[] methods = clz.getMethods();
			for(Method method : methods){
				RequestMapping reqAnno = method.getAnnotation(RequestMapping.class);
				if(reqAnno!=null){
					String uri = reqAnno.value();
					CtrlAndMethod cam = new CtrlAndMethod(target, method);
					mappings.put(uri, cam);
				}
			}
		}
		
		
		
		
	}
	public CtrlAndMethod getCtrlAndMethod(String uri){
		return mappings.get(uri);
	}
}
