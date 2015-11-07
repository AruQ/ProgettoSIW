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
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ModifyDishPanel extends VerticalLayout
{

	private static final long serialVersionUID = -4061428744720039343L;
	Dish dish;
	private MyMediator mediator;

	public ModifyDishPanel(Dish dish, MyMediator mediator)
	{
		this.dish = dish;
		this.mediator = mediator;
		init();
	}

	private void init()
	{

		this.addComponent(new Label("Name:"));
		TextField name = new TextField();
		if (dish.getName() != null)
			name.setValue(dish.getName());
		name.setWidth("50%");
		this.addComponent(name);
		this.addComponent(new Label("Image Url:"));
		TextField image_url = new TextField();
		if (dish.getImageUrl() != null)
			image_url.setValue(dish.getImageUrl());
		image_url.setWidth("90%");
		this.addComponent(image_url);
		this.addComponent(new Label("Description:"));
		TextArea description = new TextArea();
		if (dish.getDescription() != null)
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

		submit.addClickListener(new ClickListener()
		{
			private static final long serialVersionUID = -6498470603330991020L;

			@Override
			public void buttonClick(ClickEvent event)
			{
				dish.setName(name.getValue());
				dish.setImageUrl(image_url.getValue());
				dish.setDescription(description.getValue());
				dish.setCategory(DishCategory.getDishCategory((String) combobox.getValue()));
				BeanDBManager.getInstance().updateDish(dish);
				Notification.show("Piatto aggiornato", Type.TRAY_NOTIFICATION);
				mediator.resetSecondContentTab();

			}
		});

		this.addComponent(submit);
		((AbstractOrderedLayout) this).setComponentAlignment(submit, Alignment.BOTTOM_RIGHT);
		this.setWidth("50%");
	}

}
