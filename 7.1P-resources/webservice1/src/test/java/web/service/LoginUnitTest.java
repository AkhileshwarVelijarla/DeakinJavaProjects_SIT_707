package web.service;

import org.junit.Assert;
import org.junit.Test;

public class LoginUnitTest {

	@Test
	public void loginReturnsTrueForValidCredentials() {
		Assert.assertTrue(LoginService.login("ahsan", "ahsan_pass", "2000-01-15"));
	}

	@Test
	public void loginFailsWhenUsernameIsNull() {
		Assert.assertFalse(LoginService.login(null, "ahsan_pass", "2000-01-15"));
	}

	@Test
	public void loginFailsWhenUsernameHasLeadingSpace() {
		Assert.assertFalse(LoginService.login(" ahsan", "ahsan_pass", "2000-01-15"));
	}

	@Test
	public void loginFailsWhenUsernameHasTrailingSpace() {
		Assert.assertFalse(LoginService.login("ahsan ", "ahsan_pass", "2000-01-15"));
	}

	@Test
	public void loginFailsWhenUsernameIsTooShort() {
		Assert.assertFalse(LoginService.login("ab", "ahsan_pass", "2000-01-15"));
	}

	@Test
	public void loginFailsWhenUsernameContainsInvalidCharacter() {
		Assert.assertFalse(LoginService.login("ahsan!", "ahsan_pass", "2000-01-15"));
	}

	@Test
	public void loginFailsWhenPasswordIsNull() {
		Assert.assertFalse(LoginService.login("ahsan", null, "2000-01-15"));
	}

	@Test
	public void loginFailsWhenPasswordContainsSpace() {
		Assert.assertFalse(LoginService.login("ahsan", "ahsan pass", "2000-01-15"));
	}

	@Test
	public void loginFailsWhenPasswordIsTooShort() {
		Assert.assertFalse(LoginService.login("ahsan", "short", "2000-01-15"));
	}

	@Test
	public void loginFailsWhenDobIsNull() {
		Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", null));
	}

	@Test
	public void loginFailsWhenDobIsBlank() {
		Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", ""));
	}

	@Test
	public void loginFailsWhenDobFormatIsInvalid() {
		Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "15-01-2000"));
	}

	@Test
	public void loginFailsWhenDobDateIsImpossible() {
		Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "2000-02-30"));
	}

	@Test
	public void loginFailsWhenDobYearIsOutOfRange() {
		Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "1800-01-01"));
	}

	@Test
	public void loginFailsWhenUsernamePasswordAreCorrectButDobIsWrong() {
		Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "2000-01-16"));
	}

	@Test
	public void loginFailsWhenAllInputsAreWellFormedButCredentialsDoNotMatch() {
		Assert.assertFalse(LoginService.login("ahsan123", "password12", "2000-01-15"));
	}
}