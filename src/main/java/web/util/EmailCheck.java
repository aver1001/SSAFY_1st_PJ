package web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailCheck {
	// 정상적인 이메일 인지 검증.
	public static boolean isValidEmail(String email) {
		boolean err = false;
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		if (m.matches()) {
			err = true;
		}
		return err;
	}
}
