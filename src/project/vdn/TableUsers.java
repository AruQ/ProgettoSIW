package project.vdn;

import java.util.List;

import project.beans.User;
import project.database.BeanDBManager;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;

public class TableUsers
{
	Table table = null;
	private List<User> allUser = null;



	public void initTable()
	{
		table = new Table();
		allUser = BeanDBManager.getInstance().getAllUser();
		table.addContainerProperty("Username", Label.class, null);
		table.addContainerProperty("Profile Name", Label.class, null);
		table.addContainerProperty("Image", Image.class, null);
		table.addContainerProperty("Admin", CheckBox.class, null);
		table.addContainerProperty("", Button.class, null);

		table.setColumnAlignments(Align.CENTER, Align.CENTER, Align.CENTER, Align.CENTER, Align.CENTER);

		if (allUser != null)
		{
			int i = 0;
			for (User user : allUser)
			{
				Label username = new Label(user.getUsername());
				Label profileName = new Label(user.getProfileName());
				Image profileImage = null;
				if (user.getImageUrl() != null && !user.getImageUrl().equals(""))
				{
					ExternalResource resource = new ExternalResource(user.getImageUrl());

					profileImage = new Image("profileImage", resource);
				} else
				{
					profileImage = new Image(null, new ExternalResource("images/profileUnknown.png"));
					profileImage.setHeight("100px");
					profileImage.setWidth("100px");
				}

				CheckBox admin = new CheckBox();
				if (user.getAdmin())
					admin.setValue(true);

				Button submit = new Button("Submit");
				submit.addClickListener(new ClickListener()
				{

					@Override
					public void buttonClick(ClickEvent event)
					{
						System.out.println("submit");

					}
				});
				table.addItem(new Object[] { username, profileName, profileImage, admin, submit }, i);
				i++;
			}
		}

		table.setPageLength(table.size());
		table.setWidth("100%");
	}

	public Table getTable()
	{
		initTable();
		return table;
	}


}
