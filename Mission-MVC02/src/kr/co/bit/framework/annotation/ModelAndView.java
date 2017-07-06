package kr.co.bit.framework.annotation;

import java.util.HashMap;
import java.util.Map;

/**
 * ��: ȭ��ܿ��� ����� ������ �����ؼ� request������ü�� ���
 * view ����ȭ�� ������ ���� ����
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
