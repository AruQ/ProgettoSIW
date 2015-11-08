package project.vdn;

import project.beans.Dish;
import project.beans.DishCategory;
import project.database.BeanDBManager;

import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
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

		this.addComponent(new Label("<h3>Nome<span style='color:red;'>*</span> :</h3>", ContentMode.HTML));
		TextField name = new TextField();
		name.setWidth("50%");
		this.addComponent(name);
		this.addComponent(new Label("<h3>Image Url :</h3>", ContentMode.HTML));
		TextField image_url = new TextField();
		image_url.setWidth("90%");
		this.addComponent(image_url);
		this.addComponent(new Label("<h3>Description :</h3>", ContentMode.HTML));
		TextArea description = new TextArea();
		description.setWidth("50%");
		this.addComponent(description);
		this.addComponent(new Label("<h3>Category<span style='color:red;'>*</span> :</h3>", ContentMode.HTML));
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

			private static final long serialVersionUID = -6301007882947242241L;

			@Override
			public void buttonClick(ClickEvent event)
			{
				if (name.getValue().equals("") || (combobox.getValue() == null || combobox.getValue().equals("")))
				{
					Notification notification = new Notification("Bisogna inserire i campi obbligatori", Type.WARNING_MESSAGE);
					notification.setStyleName("mystyle");
					notification.show(Page.getCurrent());

				} else
				{
					Dish dish = new Dish(name.getValue(), image_url.getValue(), description.getValue(), DishCategory
							.getDishCategory((String) combobox.getValue()));
					BeanDBManager.getInstance().addDish(dish);
					mediator.resetSecondContentTab();
					mediator.resetFirstContentTab();
				}

			}
		});

		this.addComponent(submit);
		((AbstractOrderedLayout) this).setComponentAlignment(submit, Alignment.BOTTOM_RIGHT);
		this.setWidth("50%");
	}

}
