package project.vdn;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("progetto_siw")

public class Progetto_siwUI extends UI {

	@WebServlet(value = { "/admin/*", "/VAADIN/*" }, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Progetto_siwUI.class,
			widgetset = "my.project.vdn.ui.widgetset.My_project_vdnWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {

		// TableDishes tableDishes = new TableDishes(this);


		VerticalLayout mainPanel = new VerticalLayout();
		mainPanel.addComponent(new MyMediator(this).getPanel());

		setContent(mainPanel);
	}

}