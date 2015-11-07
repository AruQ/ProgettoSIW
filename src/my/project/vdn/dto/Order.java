package my.project.vdn.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Date data;

    private int id;

    private List<Image> images;

    private boolean paid;

    private boolean sent;

    private User user;

    public Order()
    {
    }

    public Order(final Date data, final int id, final List<Image> images, final boolean paid, final boolean sent, final User user)
    {
        this.data = data;
        this.id = id;
        this.images = images;
        this.paid = paid;
        this.sent = sent;
        this.user = user;
    }

    public void addImage(final Image i)
    {
        if (images == null)
        {
            images = new ArrayList<Image>();
        }
        images.add(i);
    }

    public Date getData()
    {
        return data;
    }

    public int getId()
    {
        return id;
    }

    public List<Image> getImages()
    {
        return images;
    }

    public User getUser()
    {
        return user;
    }

    public boolean isPaid()
    {
        return paid;
    }

    public boolean isSent()
    {
        return sent;
    }

    public void setData(final Date data)
    {
        this.data = data;
    }

    public void setId(final int id)
    {
        this.id = id;
    }

    public void setImages(final List<Image> images)
    {
        this.images = images;
    }

    public void setPaid(final boolean paid)
    {
        this.paid = paid;
    }

    public void setSent(final boolean sent)
    {
        this.sent = sent;
    }

    public void setUser(final User user)
    {
        this.user = user;
    }
}
