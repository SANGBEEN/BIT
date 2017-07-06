package kr.co.bit.framework.annotation;

import java.util.HashMap;
import java.util.Map;

/**
 * 모델: 화면단에서 사용할 데이터 저장해서 request공유객체에 등록
 * view 응답화면 페이지 정보 저장
 * @author bit-user
 *
 */
public class ModelAndView {
	private String view;
	private Map<String,Object> model;
	
	public ModelAndView(){
		model = new HashMap<>();
	}
	

	public ModelAndView(String view) {
		super();
		this.view = view;
	}


	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
	
	public void addAttribute(String key, Object value){
		model.put(key, value);
	}
	
}
