package project.vdn;

import project.beans.Dish;
import project.beans.DishCategory;
import project.database.BeanDBManager;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class AddDishPanel extends VerticalLayout
{

	private static final long serialVersionUID = -8447527159907383839L;
	private MyMediator mediator;

	public AddDishPanel(MyMediator mediator)
	{
		this.mediator = mediator;
		init();
	}

	private void init()
	{


		this.addComponent(new Label("Name:"));
		TextField name = new TextField();
		name.setWidth("50%");
		this.addComponent(name);
		this.addComponent(new Label("Image Url:"));
		TextField image_url = new TextField();
		image_url.setWidth("90%");
		this.addComponent(image_url);
		this.addComponent(new Label("Description:"));
		TextArea description = new TextArea();
		description.setWidth("50%");
		this.addComponent(description);
		this.addComponent(new Label("Category:"));
		ComboBox combobox = new ComboBox();
		combobox.setInvalidAllowed(false);
		combobox.setNullSelectionAllowed(false);

		DishCategory[] values = DishCategory.values();
		for (DishCategory dishCategory : values)
		{
			combobox.addItem(dishCategory.getType());

		}

		this.addComponent(combobox);

		Button submit = new Button("Submit");
		submit.addClickListener(new ClickListener()
		{

			@Override
			public void buttonClick(ClickEvent event)
			{
				Dish dish = new Dish(name.getValue(), image_url.getValue(), description.getValue(), DishCategory
						.getDishCategory((String) combobox.getValue()));
				BeanDBManager.getInstance().addDish(dish);
				mediator.resetSecondContentTab();


			}
		});

		this.addComponent(submit);
		((AbstractOrderedLayout) this).setComponentAlignment(submit, Alignment.BOTTOM_RIGHT);
		this.setWidth("50%");
	}

}
