package project.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.database.JsonDBManager;

@WebServlet(name = "UpdateComment", urlPatterns = { "/UpdateComment" })
public class UpdateComment extends HttpServlet
{
	private static final long serialVersionUID = -3874351001027823378L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

		String toReturn = "";
		String username = req.getParameter("username");
		Integer dishID = Integer.parseInt(req.getParameter("dishID"));
		String time = req.getParameter("time");
		String text = req.getParameter("text");



		boolean updatedComment = JsonDBManager.getInstance().updateComment(username, dishID, time, text);
		if (updatedComment)
			toReturn += "'updated': 'true'";
		else
			toReturn += "'updated': 'false'";

		resp.getWriter().write("{" + toReturn + "}");

	}

}
