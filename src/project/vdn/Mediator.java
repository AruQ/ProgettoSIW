package project.vdn;

import java.util.List;

import my.project.vdn.dto.Order;
import my.project.vdn.service.PixBuyController;

import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class Mediator
{
	private final PixBuyController controller = new PixBuyController();

    private final Image headerImage = new Image(null, new ThemeResource("img/logo.png"));

    private VerticalLayout mainPanel;

    private ManagerPanel managerPanel;

    private MyLoginForm myLoginForm;

    private OrderManagerPanel orderManagerPanel;

    public MyLoginForm getLoginForm()
    {
        myLoginForm = new MyLoginForm(this);
        return myLoginForm;
    }

    public VerticalLayout getMainPanel()
    {
        final VerticalLayout header = getHeader();

        mainPanel = new VerticalLayout();
        mainPanel.addComponent(header);
        return mainPanel;
    }

    public ManagerPanel getManagerPanel()
    {
        managerPanel = new ManagerPanel(this);
        return managerPanel;
    }

    public OrderManagerPanel getOrderManagerPanel()
    {
        orderManagerPanel = new OrderManagerPanel(this);
        return orderManagerPanel;
    }

    public void loadImages(final Order o)
    {
		orderManagerPanel.getImagesBeanItem().removeAllItems();
		orderManagerPanel.getImagesBeanItem().addAll(o.getImages());
    }

    public void loadOrders()
    {
		final List<Order> allOrders = controller.getAllOrders();
		orderManagerPanel.getOrderBeanItem().removeAllItems();
		orderManagerPanel.getOrderBeanItem().addAll(allOrders);
    }

    public void sendOrder(final Order bean)
    {
		controller.sendOrder(bean);
        loadOrders();
    }

    public void tryLogin(final String username, final String password)
    {
        /*
         * TODO: check login
         
         if(!controller.check(userName, password))
         {
             Notification.show("Login error", Notification.Type.ERROR_MESSAGE);
         }
         else {
         */

        VaadinService.getCurrentRequest().getWrappedSession().setAttribute("logged_user", username);

        mainPanel.removeAllComponents();

        Notification.show("Login Succesfully", Notification.Type.TRAY_NOTIFICATION);

        mainPanel.addComponent(getHeader());
        mainPanel.addComponent(getManagerPanel());
        mainPanel.setExpandRatio(managerPanel, -1.0f);

        /*
         }
         */
    }

    protected VerticalLayout getHeader()
    {
        final VerticalLayout lay = new VerticalLayout();
        lay.addComponent(headerImage);
        lay.setMargin(true);
        return lay;
    }
}
