package web.model.vo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import web.util.EmailCheck;

public class MemberVO {
	private String name, id, pw, email, loc;

	public MemberVO(String name, String id, String pw, String email, String loc) {
		super();
		setName(name);
		setId(id);
		setPw(pw);
		setEmail(email);
		setLoc(loc);
	}

	public MemberVO(String id, String pw) {
		super();
		setId(id);
		setPw(pw);
	}

	public MemberVO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null) {
			this.name = name;
		} else {

		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id != null) {
			this.id = id;
		} else {

		}

	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		if (pw != null) {
			this.pw = pw;
		} else {

		}

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email != null && EmailCheck.isValidEmail(email)) {
			this.email = email;
		} 

	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	@Override
	public String toString() {
		return "MemberVO [name=" + name + ", id=" + id + ", pw=" + pw + ", email=" + email + ", loc=" + loc + "]";
	}

	

}
