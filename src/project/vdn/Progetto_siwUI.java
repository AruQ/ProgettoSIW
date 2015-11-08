package project.vdn;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.beans.User;
import project.database.BeanDBManager;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.ServiceException;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("progetto_siw")
public class Progetto_siwUI extends UI
{

	@WebServlet(value = { "/admin/*", "/VAADIN/*" }, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Progetto_siwUI.class,
			widgetset = "my.project.vdn.ui.widgetset.My_project_vdnWidgetset")
	public static class Servlet extends VaadinServlet
	{
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
		{
			// TODO Auto-generated method stub
			super.doPost(req, resp);
			System.out.println(req.getParameter("id"));
		}

		@Override
		protected VaadinServletService createServletService(DeploymentConfiguration deploymentConfiguration) throws ServiceException
		{
			VaadinServletService service = new VaadinServletService(this, deploymentConfiguration)
			{

				@Override
				public void requestEnd(VaadinRequest request, VaadinResponse response, VaadinSession session)
				{
					super.requestEnd(request, response, session);
					final String myParam = request.getParameter("username");
					if (myParam != null)
					{
						session.setAttribute("username", myParam);
					}
				}
			};
			service.init();
			return service;
		}
	}

	@Override
	protected void init(VaadinRequest request)
	{

		Object myParam = getSession().getAttribute("username");
		User user = null;
		if (myParam != null)
		{
			user = BeanDBManager.getInstance().getUser((String) myParam);
		}
		VerticalLayout mainPanel = new VerticalLayout();
		if (user != null && user.getAdmin())
		{
			mainPanel.addComponent(new MyMediator(this, user).getPanel());

		} else
		{
			Notification notification = new Notification("Non hai i permessi necessari per accedere", Type.WARNING_MESSAGE);
			notification.setStyleName("mystyle");
			notification.setDelayMsec(-1);
			notification.show(Page.getCurrent());
		}

		setContent(mainPanel);
	}

}