package project.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.database.JsonDBManager;

@WebServlet(name = "MenuServlet", urlPatterns = { "/MenuServlet" })
public class MenuServlet extends HttpServlet
{
	private static final long serialVersionUID = -6727232677364483311L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		if (req.getParameter("request").equals("days"))
		{
			String avaibleDays = JsonDBManager.getInstance().getAvaibleDays();
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(avaibleDays);
		}
	}
}
