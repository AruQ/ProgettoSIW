package project.vdn;

import java.io.Serializable;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.VerticalLayout;

public class MyMediator implements Serializable
{

	private static final long serialVersionUID = -4873773922770304465L;
	private VerticalLayout mainPanel;
	private TabSheet tabsheet;
	private VerticalLayout userTab = new VerticalLayout();
	private VerticalLayout dishesTab = new VerticalLayout();
	private VerticalLayout menuTab = new MenuPanel();
	private TableUsers tableUsers;
	private TableDishes tableDishes;
	private Button addDish;

	public MyMediator(Progetto_siwUI progetto_siwUI)
	{
		this.tableUsers = new TableUsers();
		this.tableDishes = new TableDishes(this);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("deprecation")
	private void init()
	{
		mainPanel = new VerticalLayout();
		tabsheet = new TabSheet();
		mainPanel.addComponent(tabsheet);

		// Create the first tab

		addDish = new Button("Add Dish");
		addDish.addClickListener(new ClickListener()
		{

			private static final long serialVersionUID = -8655290812196259815L;

			@Override
			public void buttonClick(ClickEvent event)
			{
				dishesTab.removeAllComponents();
				dishesTab.addComponent(new AddDishPanel(MyMediator.this));

			}
		});
		dishesTab.addComponent(addDish);
		dishesTab.addComponent(tableDishes.getTable());
		dishesTab.setCaption("Dish");
		tabsheet.addTab(dishesTab, "Dishes", FontAwesome.CUTLERY);

		menuTab.setCaption("Menu");
		tabsheet.addTab(menuTab, "Menu", FontAwesome.CALENDAR);

		userTab.addComponent(tableUsers.getTable());
		userTab.setCaption("Users");
		tabsheet.addTab(userTab, "Users", FontAwesome.USERS);

		tabsheet.addListener(new TabSheet.SelectedTabChangeListener() {

			private static final long serialVersionUID = 2629934417938000352L;

			public void selectedTabChange(SelectedTabChangeEvent event)
			{
				String caption = ((TabSheet) event.getSource()).getSelectedTab().getCaption();
				if (caption != null && caption.equals("Menu"))
				{
					((MenuPanel) menuTab).updateDishes();
				}

			}
		});


	}

	Layout getPanel()
	{
		init();
		return mainPanel;
	}

	public void setSecondContentTab(ModifyDishPanel modifyDishPanel)
	{
		dishesTab.removeAllComponents();
		dishesTab.addComponent(modifyDishPanel);
		dishesTab.setImmediate(true);
	}

	public void resetSecondContentTab()
	{
		dishesTab.removeAllComponents();
		dishesTab.addComponent(addDish);
		dishesTab.addComponent(tableDishes.getTable());
		dishesTab.setImmediate(true);

	}

}
