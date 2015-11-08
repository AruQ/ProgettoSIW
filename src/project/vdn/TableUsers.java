package project.vdn;

import java.io.Serializable;
import java.util.List;

import project.beans.User;
import project.database.BeanDBManager;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;

public class TableUsers implements Serializable
{

	private static final long serialVersionUID = 9116050839565125417L;
	Table table = null;
	private List<User> allUser = null;
	private User user;


	public TableUsers(User user)
	{
		this.user = user;
		// TODO Auto-generated constructor stub
	}

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

				Button save = new Button("Save");
				save.addClickListener(new ClickListener()
				{

					private static final long serialVersionUID = 3543285666483221749L;

					@Override
					public void buttonClick(ClickEvent event)
					{
						if (user.getUsername().equals(TableUsers.this.user.getUsername()))
						{
							Notification notification = new Notification("Operazione non consentita", Type.TRAY_NOTIFICATION);
							notification.setStyleName("mystyle");
							notification.show(Page.getCurrent());
							admin.setValue(true);
						} else if (BeanDBManager.getInstance().setAdmin(user.getUsername(), admin.getValue()))
						{
							Notification notification = new Notification("Utente aggiornato con successo", Type.TRAY_NOTIFICATION);
							notification.setStyleName("mystyle");
							notification.show(Page.getCurrent());
						} else
						{
							Notification notification = new Notification("Non è stato possibile aggiornare l'utente",
									Type.TRAY_NOTIFICATION);
							notification.setStyleName("mystyle");
							notification.show(Page.getCurrent());
						}

					}
				});
				table.addItem(new Object[] { username, profileName, profileImage, admin, save }, i);
				i++;
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
