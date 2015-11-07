package project.vdn;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import my.project.vdn.dto.Image;
import my.project.vdn.dto.Order;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent;
import com.vaadin.ui.renderers.ClickableRenderer.RendererClickListener;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.renderers.NumberRenderer;

/**
 * see: https://vaadin.com/blog/-/blogs/using-vaadin-grid
 *      http://fortawesome.github.io/Font-Awesome/icons/
 */
public class OrderManagerPanel extends HorizontalLayout
{
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private static final long serialVersionUID = 1L;

	private final BeanItemContainer<Image> imagesBeanItem = new BeanItemContainer<>(Image.class);

    private Grid imagesGrid;

    private final Mediator mediator;

    private final BeanItemContainer<Order> orderBeanItem = new BeanItemContainer<>(Order.class);

    private Grid ordersGrid;

    public OrderManagerPanel(final Mediator mediator)
    {
        this.mediator = mediator;
        buildComponent();
    }

    public BeanItemContainer<Image> getImagesBeanItem()
    {
        return imagesBeanItem;
    }

    public BeanItemContainer<Order> getOrderBeanItem()
    {
        return orderBeanItem;
    }

    private void buildComponent()
    {
        setSizeFull();
        setSpacing(true);
        setMargin(true);

        buildOrdersGrid();
        buildImagesGrid();

        addComponent(ordersGrid);
        addComponent(imagesGrid);
    }

    protected void buildImagesGrid()
    {
        imagesGrid = new Grid("<h3 style='text-align:center;'><b>Order's images</b></h3>");
        imagesGrid.setSizeFull();
        imagesGrid.setCaptionAsHtml(true);
        imagesGrid.setContainerDataSource(getImagesBeanItem());
        imagesGrid.setColumnOrder("id", "imageCategoryId", "name", "price", "serverRelativePath", "fakeDate", "fakeCheck");

        imagesGrid.getColumn("fakeDate").setRenderer(new DateRenderer(dateFormat));
        imagesGrid.getColumn("price").setRenderer(new NumberRenderer(new DecimalFormat("� ##0.00")));

        final HeaderRow groupingHeader = imagesGrid.prependHeaderRow();
        final HeaderCell namesCell = groupingHeader.join(groupingHeader.getCell("fakeDate"), groupingHeader.getCell("fakeCheck"));
        namesCell.setHtml("<h4 style='text-align:center;'>FAKE</h4>");

        imagesGrid.setSelectionMode(Grid.SelectionMode.NONE);
        imagesGrid.setEditorEnabled(true);
        imagesGrid.getEditorFieldGroup().addCommitHandler(new CommitHandler()
            {
                private static final long serialVersionUID = 1L;

                @Override
                public void postCommit(final CommitEvent commitEvent) throws CommitException
                {
                    Notification.show("SAVED!!!", Type.TRAY_NOTIFICATION);
                }

                @Override
                public void preCommit(final CommitEvent commitEvent) throws CommitException
                {

                }
            });
    }

    protected void buildOrdersGrid()
    {
        getOrderBeanItem().addNestedContainerProperty("user.username");

        getOrderBeanItem().addNestedContainerProperty("user.score");
        //        getOrderBeanItem().
        final GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(getOrderBeanItem());
        gpc.addGeneratedProperty("send", new PropertyValueGenerator<String>()
            {
                private static final long serialVersionUID = 1L;

                @Override
                public Class<String> getType()
                {
                    return String.class;
                }

                @Override
                public String getValue(final Item item, final Object itemId, final Object propertyId)
                {
                    return "Send"; // The caption
                }
            });

        ordersGrid = new Grid("<h3 style='text-align:center;'><b>Orders</b></h3>", gpc);
        ordersGrid.setSizeFull();
        ordersGrid.setCaptionAsHtml(true);
        //        ordersGrid.setContainerDataSource(gpc);
        ordersGrid.setColumnOrder("id", "data", "user.username", "user.score", "paid", "sent");

        ordersGrid.removeColumn("images");
        ordersGrid.removeColumn("user");

        ordersGrid.getColumn("data").setRenderer(new DateRenderer(dateFormat)).setHeaderCaption("Data Ordine");
        ordersGrid.getColumn("user.score").setRenderer(new NumberRenderer(new DecimalFormat("###.##")))
                .setHeaderCaption("User Score");

        ordersGrid.getColumn("send").setRenderer(new ButtonRenderer(new RendererClickListener()
            {
                private static final long serialVersionUID = 1L;

                @Override
                public void click(final RendererClickEvent event)
                {
                    final BeanItem<Order> item = getOrderBeanItem().getItem(event.getItemId());
                    Notification.show("Send " + item.getBean(), Type.TRAY_NOTIFICATION);

                    mediator.sendOrder(item.getBean());
                }
            }));

        ordersGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        ordersGrid.addSelectionListener(new SelectionListener()
            {
                private static final long serialVersionUID = 1L;

                @Override
                public void select(final SelectionEvent event)
                {
                    final Order o = (Order) event.getSelected().iterator().next();
                    mediator.loadImages(o);
                }
            });
    }
}
