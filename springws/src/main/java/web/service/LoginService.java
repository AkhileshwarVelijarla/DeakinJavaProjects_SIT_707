package web.service;

/**
 * Business logic to handle login functions.
 */
public class LoginService {

	public static boolean login(String username, String password, String dob) {
		if (isBlank(username) || isBlank(password) || isBlank(dob)) {
			return false;
		}

		// Use all 3 fields as required by the task
		return "ahsan".equals(username.trim())
				&& "ahsan_pass".equals(password.trim())
				&& "2000-01-01".equals(dob.trim());
	}

	private static boolean isBlank(String value) {
		return value == null || value.trim().isEmpty();
	}
}