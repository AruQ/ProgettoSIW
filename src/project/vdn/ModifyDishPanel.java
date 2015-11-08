package project.vdn;

import project.beans.Dish;
import project.beans.DishCategory;
import project.database.BeanDBManager;

import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
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



		this.addComponent(new Label("<h3>Nome<span style='color:red;'>*</span> :</h3>", ContentMode.HTML));
		TextField name = new TextField();
		if (dish.getName() != null)
			name.setValue(dish.getName());
		name.setWidth("50%");
		this.addComponent(name);

		this.addComponent(new Label("<h3>Image Url :</h3>", ContentMode.HTML));
		TextField image_url = new TextField();
		if (dish.getImageUrl() != null)
			image_url.setValue(dish.getImageUrl());
		image_url.setWidth("90%");
		this.addComponent(image_url);

		this.addComponent(new Label("<h3>Description :</h3>", ContentMode.HTML));
		TextArea description = new TextArea();
		if (dish.getDescription() != null)
			description.setValue(dish.getDescription());
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
		combobox.select(dish.getCategory().getType());
		this.addComponent(combobox);

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		Button back = new Button("Back");
		back.addClickListener(new ClickListener()
		{
			private static final long serialVersionUID = 5051601390370346680L;

			@Override
			public void buttonClick(ClickEvent event)
			{
				mediator.resetFirstContentTab();
				
			}
		});
		horizontalLayout.addComponent(back);
	

		Button submit = new Button("Submit");

		submit.addClickListener(new ClickListener()
		{
			private static final long serialVersionUID = -6498470603330991020L;

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
				dish.setName(name.getValue());
				dish.setImageUrl(image_url.getValue());
				dish.setDescription(description.getValue());
				dish.setCategory(DishCategory.getDishCategory((String) combobox.getValue()));
				BeanDBManager.getInstance().updateDish(dish);
				Notification notification = new Notification("Piatto aggiornato", Type.TRAY_NOTIFICATION);
				notification.setStyleName("mystyle");
				notification.show(Page.getCurrent());
					mediator.resetFirstContentTab();
				}

			}
		});

		horizontalLayout.addComponent(submit);
		((AbstractOrderedLayout) horizontalLayout).setComponentAlignment(submit, Alignment.BOTTOM_RIGHT);
		horizontalLayout.setMargin(new MarginInfo(true, false, false, false));
		horizontalLayout.setWidth("100%");
		this.addComponent(horizontalLayout);
		this.setWidth("50%");
	}

}
