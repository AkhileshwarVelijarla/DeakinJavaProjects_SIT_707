package web.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Business logic to handle login functions.
 */
public class LoginService {

	private static final String VALID_USERNAME = "ahsan";
	private static final String VALID_PASSWORD = "ahsan_pass";
	private static final String VALID_DOB = "2000-01-15";

	/**
	 * Static method returns true for successful login, false otherwise.
	 */
	public static boolean login(String username, String password, String dob) {
		if (!isValidUsername(username)) {
			return false;
		}

		if (!isValidPassword(password)) {
			return false;
		}

		if (!isValidDob(dob)) {
			return false;
		}

		return VALID_USERNAME.equals(username)
				&& VALID_PASSWORD.equals(password)
				&& VALID_DOB.equals(dob);
	}

	static boolean isValidUsername(String username) {
		if (username == null) {
			return false;
		}

		if (!username.equals(username.trim())) {
			return false;
		}

		if (username.length() < 3 || username.length() > 20) {
			return false;
		}

		return username.matches("[A-Za-z0-9_]+");
	}

	static boolean isValidPassword(String password) {
		if (password == null) {
			return false;
		}

		if (password.contains(" ")) {
			return false;
		}

		return password.length() >= 8 && password.length() <= 20;
	}

	static boolean isValidDob(String dob) {
		if (dob == null || dob.trim().isEmpty()) {
			return false;
		}

		if (!dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
			return false;
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);

		try {
			format.parse(dob);
		} catch (ParseException e) {
			return false;
		}

		int year = Integer.parseInt(dob.substring(0, 4));
		return year >= 1900 && year <= 2099;
	}
}