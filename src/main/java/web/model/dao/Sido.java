package web.model.dao;

import java.io.Serializable;

public class Sido implements Serializable{
	private String sido_code,sido_name;

	public Sido() {}

	public Sido(String sido_code, String sido_name) {
		setSido_code(sido_code);
		setSido_name(sido_name);
	}

	public String getSido_code() {
		return sido_code;
	}

	public void setSido_code(String sido_code) {
		if (sido_code != null) {
			this.sido_code = sido_code;
		}
	}

	public String getSido_name() {
		return sido_name;
	}

	public void setSido_name(String sido_name) {
		if (sido_name != null) {
			this.sido_name = sido_name;
		}
	}
	
	
	
	
}
