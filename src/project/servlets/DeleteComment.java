package project.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.database.JsonDBManager;

@WebServlet(name = "DeleteComment", urlPatterns = { "/DeleteComment" })
public class DeleteComment extends HttpServlet
{

	private static final long serialVersionUID = -817189658351939980L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String username = req.getParameter("username");
		Integer dishID = Integer.parseInt(req.getParameter("dishID"));
		String time = req.getParameter("time");

		JsonDBManager.getInstance().deleteComment(username, dishID, time);
	}

}
