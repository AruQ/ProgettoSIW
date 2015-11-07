package project.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.database.JsonDBManager;

@WebServlet(name = "CafeteriaPositionServlet", urlPatterns = { "/CafeteriaPositionServlet" })
public class CafeteriaPositionServlet extends HttpServlet
{
	private static final long serialVersionUID = -3592798396414356757L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

		String allCafeterias = JsonDBManager.getInstance().getAllCafeterias();
		System.err.println("sono nella servlet delle posizioni delle mense");
		resp.getWriter().write(allCafeterias);
	}
	

}
