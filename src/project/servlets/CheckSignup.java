package project.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.database.JsonDBManager;

@WebServlet(name = "CheckSignup", urlPatterns = { "/CheckSignup" })
public class CheckSignup extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String toReturn = "";
		final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		boolean emptyEmail = false;
		boolean emptyUsername = false;
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		if (username.equals(""))
		{
			emptyUsername = true;
		}
		if (email.equals(""))
		{
			emptyEmail = true;
		}

		if (emptyUsername)
		{
			toReturn += "'username': 'empty'";
		} else
		{
			toReturn += JsonDBManager.getInstance().checkUsername(username);
		}
		toReturn += ",";
		if (emptyEmail)
		{
			toReturn += "'email': 'empty'";
		} else if (email.matches(EMAIL_REGEX))
		{
			toReturn += JsonDBManager.getInstance().checkEmail(email);
		} else
		{
			toReturn += "'email': 'false'";

		}
		resp.getWriter().write("{" + toReturn + "}");

	}
}
