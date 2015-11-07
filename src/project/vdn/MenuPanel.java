package project.vdn;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import project.beans.Dish;
import project.database.BeanDBManager;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.VerticalLayout;

public class MenuPanel extends VerticalLayout
{

	private static final long serialVersionUID = 1L;
	private Table table = null;
	private Map<Integer, CheckBox> selectedDishes;
	private List<Dish> allDishes;
	private Button save;
	private DateField date;

	public MenuPanel()
	{
		initPanel();
	}

	@SuppressWarnings("deprecation")
	private void initPanel()
	{
		table = new Table();
		selectedDishes = new HashMap<Integer, CheckBox>();


		// Create a DateField with the default style
		date = new DateField();

		// Set the date and time to present
		date.setValue(new Date());

		// listener that shows a value change notification
		date.addListener(new DateField.ValueChangeListener()
		{

			private static final long serialVersionUID = -2907331888114579350L;

			public void valueChange(com.vaadin.data.Property.ValueChangeEvent event)
			{
				if (((java.util.Date) event.getProperty().getValue()).before(new Date()))
				{
					date.setValue(new Date());

					changeSelectedDishes(date.getValue());
					Notification.show("Non puoi selezionare una data precedente a oggi", Type.WARNING_MESSAGE);
				} else
				{
					changeSelectedDishes(date.getValue());
				}
			}

		});

		this.addComponent(date);

		initTable(date.getValue());
		table.setPageLength(table.size());
		table.setWidth("50%");
		this.addComponent(table);
		((AbstractOrderedLayout) this).setComponentAlignment(table, Alignment.MIDDLE_CENTER);

		save = new Button("Save");
		save.addClickListener(new ClickListener()
		{

			private static final long serialVersionUID = 8870896793591692316L;

			@Override
			public void buttonClick(ClickEvent event)
			{
				Set<Integer> keySet = selectedDishes.keySet();
				DateFormat toFormat = new SimpleDateFormat("dd-MM-yyyy");
				String currentDate = toFormat.format(date.getValue());
				for (Integer key : keySet)
				{
					if (selectedDishes.get(key).getValue())
					{

						BeanDBManager.getInstance().addDishToMenu(currentDate, key);
					} else
					{

						BeanDBManager.getInstance().deleteDishToMenu(currentDate, key);
					}

				}
				Notification.show("Menu del " + currentDate + " aggiornato", Type.TRAY_NOTIFICATION);

			}
		});
		this.addComponent(save);
		((AbstractOrderedLayout) this).setComponentAlignment(save, Alignment.BOTTOM_RIGHT);
		this.setWidth("90%");
	}

	public void initTable(Date date)
	{

		DateFormat toFormat = new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = toFormat.format(date);
		List<Dish> dishesByDay = BeanDBManager.getInstance().getDishesByDay(currentDate);
		allDishes = BeanDBManager.getInstance().getDishes();
		table.removeAllItems();
		table.addContainerProperty("Select", CheckBox.class, null);
		table.addContainerProperty("Name", Label.class, null);
		table.addContainerProperty("Category", Label.class, null);

		table.setColumnExpandRatio("Select", 10);
		table.setColumnExpandRatio("Name", 25);
		table.setColumnExpandRatio("Category", 25);

		table.setColumnAlignments(Align.CENTER, Align.CENTER, Align.CENTER);

		if (allDishes != null)
		{
			int index = 0;
			for (Dish dish : allDishes)
			{

				Label name = new Label(dish.getName());

				Label category = new Label(dish.getCategory().getType());

				CheckBox checkBox = new CheckBox();
				if (dishesByDay.contains(dish))
					checkBox.setValue(true);
				selectedDishes.put(dish.getId(), checkBox);

				table.addItem(new Object[] { checkBox, name, category }, index);
				index++;
			}
		}

		this.setImmediate(true);

	}

	void changeSelectedDishes(Date date)
	{
		DateFormat toFormat = new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = toFormat.format(date);
		List<Dish> dishesByDay = BeanDBManager.getInstance().getDishesByDay(currentDate);

		for (Dish dish : allDishes)
		{
			if (dishesByDay.contains(dish))
				selectedDishes.get(dish.getId()).setValue(true);
			else
				selectedDishes.get(dish.getId()).setValue(false);

		}

		this.setImmediate(true);
	}

	public void updateDishes()
	{
		initTable(date.getValue());
	}

}
