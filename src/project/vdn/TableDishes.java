package project.vdn;

import java.io.Serializable;
import java.util.List;

import project.beans.Dish;
import project.database.BeanDBManager;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;

public class TableDishes implements Serializable
{

	private static final long serialVersionUID = -498926289006391144L;
	Table table;
	private List<Dish> allDishes = null;
	private MyMediator mediator;


	public TableDishes(MyMediator mediator)
	{
		this.mediator = mediator;
	}

	public void initTable()
	{
		allDishes = BeanDBManager.getInstance().getDishes();
		table = new Table();
		table.addContainerProperty("Preview", Image.class, null);
		table.addContainerProperty("Name", Label.class, null);
		table.addContainerProperty("Description", Label.class, null);
		table.addContainerProperty("Category", Label.class, null);
		table.addContainerProperty("Modify", Button.class, null);
		table.addContainerProperty("Delete", Button.class, null);

		table.setColumnExpandRatio("Name", 10);
		table.setColumnExpandRatio("Category", 5);
		table.setColumnExpandRatio("Description", 25);
		table.setColumnExpandRatio("Preview", 8);

		table.setColumnAlignments(Align.CENTER, Align.CENTER, Align.CENTER, Align.CENTER, Align.CENTER, Align.CENTER);

		if (allDishes != null)
		{
			int index = 0;
			for (Dish dish : allDishes)
			{
				Label name = new Label(dish.getName());
				Label descriprion = new Label(dish.getDescription());
				Image previewImage = null;
				if (dish.getImageUrl() != null && !dish.getImageUrl().equals(""))
				{
					ExternalResource resource = new ExternalResource(dish.getImageUrl());

					previewImage = new Image("profileImage", resource);
					previewImage.setHeight("100px");
					previewImage.setWidth("100px");
				} else
				{
					previewImage = new Image(null, new ExternalResource("images/missing.png"));
					previewImage.setHeight("100px");
					previewImage.setWidth("100px");
				}

				Label category = new Label(dish.getCategory().getType());

				Button modify = new Button("Modify");
				modify.addClickListener(new ClickListener()
				{

					private static final long serialVersionUID = 1221912975966146657L;

					@Override
					public void buttonClick(ClickEvent event)
					{
						ModifyDishPanel modifyDishPanel = new ModifyDishPanel(dish, mediator);
						mediator.setFirstContentTab(modifyDishPanel);

					}
				});

				Button delete = new Button("Delete");
				delete.addClickListener(new ClickListener()
				{

					private static final long serialVersionUID = -6714555180836512953L;

					@Override
					public void buttonClick(ClickEvent event)
					{
						BeanDBManager.getInstance().deleteDish(dish);
						mediator.resetFirstContentTab();

					}
				});
				table.addItem(new Object[] { previewImage, name, descriprion, category, modify, delete }, index);
				index++;
			}
		}

		table.setPageLength(table.size());
		table.setWidth("90%");
	}

	public Table getTable()
	{
		initTable();
		return table;
	}

}
