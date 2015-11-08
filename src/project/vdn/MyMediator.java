package project.vdn;

import java.io.Serializable;

import project.beans.User;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class MyMediator implements Serializable
{

	private static final long serialVersionUID = -4873773922770304465L;
	private VerticalLayout mainPanel;
	private TabSheet tabsheet;
	private VerticalLayout userTab;
	private VerticalLayout dishesTab;
	private VerticalLayout menuTab;
	private VerticalLayout addDishTab;
	private TableUsers tableUsers;
	private TableDishes tableDishes;
	private VerticalLayout menuPanel;
	private User user;

	public MyMediator(Progetto_siwUI progetto_siwUI, User user)
	{
		this.user = user;
		userTab = new VerticalLayout();
		dishesTab = new VerticalLayout();
		menuTab = new VerticalLayout();
		addDishTab = new VerticalLayout();
		this.tableUsers = new TableUsers(user);
		this.tableDishes = new TableDishes(this);
		this.menuPanel = new MenuPanel();

	}

	@SuppressWarnings("deprecation")
	private void init()
	{
		mainPanel = new VerticalLayout();
		tabsheet = new TabSheet();
		mainPanel.addComponent(tabsheet);


		Table table = tableDishes.getTable();
		dishesTab.addComponent(table);
		dishesTab.setCaption("Dish");
		((AbstractOrderedLayout) dishesTab).setComponentAlignment(table, Alignment.MIDDLE_CENTER);
		tabsheet.addTab(dishesTab, "Dishes", FontAwesome.CUTLERY);

		AddDishPanel panel = new AddDishPanel(this);
		addDishTab.addComponent(panel);
		addDishTab.setCaption("addDish");
		((AbstractOrderedLayout) addDishTab).setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		tabsheet.addTab(addDishTab, "Add Dish", FontAwesome.PLUS);

		menuTab.setCaption("Menu");
		menuTab.addComponent(menuPanel);
		tabsheet.addTab(menuTab, "Menu", FontAwesome.CALENDAR);
		((AbstractOrderedLayout) menuTab).setComponentAlignment(menuPanel, Alignment.MIDDLE_CENTER);

		Table userTable = tableUsers.getTable();
		userTab.addComponent(userTable);
		userTab.setCaption("Users");
		((AbstractOrderedLayout) userTab).setComponentAlignment(userTable, Alignment.MIDDLE_CENTER);
		tabsheet.addTab(userTab, "Users", FontAwesome.USERS);

		tabsheet.addListener(new TabSheet.SelectedTabChangeListener() {

			private static final long serialVersionUID = 2629934417938000352L;

			public void selectedTabChange(SelectedTabChangeEvent event)
			{
				String caption = ((TabSheet) event.getSource()).getSelectedTab().getCaption();
				if (caption != null && caption.equals("Menu"))
				{
					((MenuPanel) menuPanel).updateDishes();
				}

			}
		});

		tabsheet.setWidth("100%");

		mainPanel.setExpandRatio(tabsheet, 1f);

	}

	Layout getPanel()
	{
		init();
		return mainPanel;
	}

	public void setFirstContentTab(ModifyDishPanel modifyDishPanel)
	{
		dishesTab.removeAllComponents();
		dishesTab.addComponent(modifyDishPanel);
		((AbstractOrderedLayout) dishesTab).setComponentAlignment(modifyDishPanel, Alignment.MIDDLE_CENTER);
		dishesTab.setImmediate(true);
	}

	public void resetFirstContentTab()
	{
		dishesTab.removeAllComponents();
		Table table = tableDishes.getTable();
		dishesTab.addComponent(table);
		((AbstractOrderedLayout) dishesTab).setComponentAlignment(table, Alignment.MIDDLE_CENTER);
		dishesTab.setImmediate(true);

	}

	public void resetSecondContentTab()
	{
		addDishTab.removeAllComponents();
		VerticalLayout panel = new AddDishPanel(this);
		addDishTab.addComponent(panel);
		((AbstractOrderedLayout) addDishTab).setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		addDishTab.setImmediate(true);
	}



}
