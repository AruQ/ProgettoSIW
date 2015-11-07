package project.vdn;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class ManagerPanel extends VerticalLayout
{
    private static final long serialVersionUID = 1L;

    private final Mediator mediator;

    private final TabSheet tabs = new TabSheet();

    public ManagerPanel(final Mediator mediator)
    {
        this.mediator = mediator;
        buildComponent();
    }

    private HorizontalLayout buildComingSoonPanel()
    {
        final HorizontalLayout comingSoonPanel = new HorizontalLayout();
        comingSoonPanel.setSizeFull();
        final Label message = new Label("<h1 style='text-align: center;'>COMING SOON...</h1>", ContentMode.HTML);
        comingSoonPanel.addComponent(message);

        comingSoonPanel.setComponentAlignment(message, Alignment.MIDDLE_CENTER);

        return comingSoonPanel;
    }

    private void buildComponent()
    {
        setSizeFull();
        tabs.setSizeFull();

        final HorizontalLayout imageManagerPanel = buildComingSoonPanel();
        tabs.addTab(imageManagerPanel, "Image Manager", FontAwesome.IMAGE);

        final HorizontalLayout userManagerPanel = buildComingSoonPanel();
        tabs.addTab(userManagerPanel, "User Manager", FontAwesome.USERS);

        final HorizontalLayout ordersManagerPanel = mediator.getOrderManagerPanel();
        tabs.addTab(ordersManagerPanel, "Orders Manager", FontAwesome.MONEY);

        mediator.loadOrders();

        addComponent(tabs);
    }
}
