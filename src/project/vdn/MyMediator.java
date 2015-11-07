package project.vdn;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class MyMediator
{
	private VerticalLayout mainPanel;
	private TabSheet tabsheet;
	VerticalLayout userTab = new VerticalLayout();
	VerticalLayout dishesTab = new VerticalLayout();
	private TableUsers tableUsers;
	private TableDishes tableDishes;

	public MyMediator(Progetto_siwUI progetto_siwUI)
	{
		this.tableUsers = new TableUsers();
		this.tableDishes = new TableDishes(this);
		// TODO Auto-generated constructor stub
	}

	private void init()
	{
		mainPanel = new VerticalLayout();
		tabsheet = new TabSheet();
		mainPanel.addComponent(tabsheet);

		// Create the first tab
		VerticalLayout tab1 = new VerticalLayout();
		tab1.addComponent(tableUsers.getTable());
		tabsheet.addTab(tab1, "Users", null);

		Button addDish = new Button("Add Dish");
		addDish.addClickListener(new ClickListener()
		{

			@Override
			public void buttonClick(ClickEvent event)
			{
				dishesTab.removeAllComponents();
				dishesTab.addComponent(new AddDishPanel());

			}
		});
		dishesTab.addComponent(addDish);
		dishesTab.addComponent(tableDishes.getTable());
		tabsheet.addTab(dishesTab, "Dishes", null);

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

}
