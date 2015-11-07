package my.project.vdn.service;

import java.util.List;

import my.project.vdn.dto.Order;

public class PixBuyController
{
    public List<Order> getAllOrders()
    {
        return BeanDBManager.getInstance().getAllOrders();
    }

    public void sendOrder(final Order bean)
    {
        bean.setSent(true);
		// BeanDBManager.getInstance().updateOrder(bean);

        // chiama web service per notificare il magazzino e avvisare il corriere...
    }
}
