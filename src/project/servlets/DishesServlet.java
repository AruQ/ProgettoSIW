package project.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.beans.DishCategory;
import project.database.JsonDBManager;

@WebServlet(name = "DishServlet", urlPatterns = { "/DishServlet" })
public class DishesServlet extends HttpServlet
{
	private static final long serialVersionUID = 1999010156269736433L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

		String json = null;
		
		String objects = "";
		
		DishCategory[] values = DishCategory.values();
		for (int i = 0; i < values.length; i++)
		{
			String dishesByCategory = JsonDBManager.getInstance().getDishesByCategory(values[i]);
			objects += "{'category':'" + values[i].getType() + "', 'dishes':" + dishesByCategory + "}";
			if (i != values.length-1)
			{
				objects += ",";
			}
		}
		json = "[" + objects + "]";

		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);


	}

}
