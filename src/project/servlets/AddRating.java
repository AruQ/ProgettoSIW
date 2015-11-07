package project.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.database.JsonDBManager;

@WebServlet(name = "AddRating", urlPatterns = { "/AddRating" })
public class AddRating extends HttpServlet
{

	private static final long serialVersionUID = 688724943594540784L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

		String username = (String) req.getParameter("username");
		Float points = Float.parseFloat(req.getParameter("pointsRating"));
		Integer dishID = Integer.parseInt(req.getParameter("dishID"));

		JsonDBManager.getInstance().addRating(username, dishID, points);
	}

}
