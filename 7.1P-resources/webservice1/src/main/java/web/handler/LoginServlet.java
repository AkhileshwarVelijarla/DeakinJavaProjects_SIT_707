package web.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.LoginService;

/**
 * HTTP end-point to handle login service.
 */
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		System.out.println("[LoginServlet] GET");
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		System.out.println("[LoginServlet] POST");

		String username = req.getParameter("username");
		String password = req.getParameter("passwd");
		String dob = req.getParameter("dob");

		System.out.println("Username/password/dob: " + username + ", " + password + ", " + dob);

		String loginStatus = LoginService.login(username, password, dob) ? "success" : "fail";

		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");

		String htmlResponse = "<html>"
				+ "<head><title>" + loginStatus + "</title></head>"
				+ "<body>"
				+ "<h2 id='login-status'>Login status: " + loginStatus + "</h2>"
				+ "<p id='login-message'>"
				+ ("success".equals(loginStatus) ? "Authentication successful." : "Authentication failed.")
				+ "</p>"
				+ "</body>"
				+ "</html>";

		PrintWriter writer = resp.getWriter();
		writer.println(htmlResponse);
		writer.flush();
	}
}