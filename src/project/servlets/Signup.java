package project.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.beans.User;
import project.database.BeanDBManager;

@WebServlet(name = "Signup", urlPatterns = { "/Signup" })
public class Signup extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		User user = new User(req.getParameter("username"), req.getParameter("password"), req.getParameter("email"), "", "");
		System.out.println(user.toString());
		BeanDBManager.getInstance().saveUser(user);

		HttpSession session = req.getSession();
		session.setAttribute("user", user);
		resp.setCharacterEncoding("UTF-8");

		resp.getWriter().write(user.toJSON());
	}

}
