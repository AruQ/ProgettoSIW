package project.vdn;

import project.beans.Dish;
import project.beans.DishCategory;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ModifyDishPanel extends VerticalLayout
{

	Dish dish;

	public ModifyDishPanel(Dish dish)
	{
		this.dish = dish;
		init();
	}

	private void init()
	{

		this.addComponent(new Label("Name:"));
		TextField name = new TextField();
		name.setValue(dish.getName());
		name.setWidth("50%");
		this.addComponent(name);
		this.addComponent(new Label("Image Url:"));
		TextField image_url = new TextField();
		image_url.setValue(dish.getImageUrl());
		image_url.setWidth("90%");
		this.addComponent(image_url);
		this.addComponent(new Label("Description:"));
		TextArea description = new TextArea();
		description.setValue(dish.getDescription());
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
		combobox.select(dish.getCategory().getType());
		this.addComponent(combobox);

		Button submit = new Button("Submit");

		this.addComponent(submit);
		((AbstractOrderedLayout) this).setComponentAlignment(submit, Alignment.BOTTOM_RIGHT);
		this.setWidth("50%");
	}


}
