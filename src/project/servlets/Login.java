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

@WebServlet(name = "LoginServlet", urlPatterns = { "/LoginServlet" })
public class Login extends HttpServlet
{
	private static final long serialVersionUID = 5100130324431705791L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		User user = null;

		if (req.getParameter("social").equals("true"))
		{

			String id = req.getParameter("id");
			String imageUrl = req.getParameter("image_url");
			String profileName = req.getParameter("profile_name");
			String email = req.getParameter("email");
			user = BeanDBManager.getInstance().getUser(id, imageUrl, profileName, email);

		} else
		{
			user = BeanDBManager.getInstance().getUser(req.getParameter("username"), req.getParameter("password"));

		}

		if (user != null)
		{
			HttpSession session = req.getSession();
			session.setAttribute("user", user);

			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(user.toJSON());

		}
 else
		{
			resp.getWriter().write("{'user':'null'}");
		}


	}

}
