package my.project.vdn.dto;

import java.io.Serializable;
import java.util.Date;

public class Image implements Serializable
{
    private static final long serialVersionUID = 1L;

    private boolean fakeCheck;

    private Date fakeDate = new Date();

    private int id;

    private int imageCategoryId;

    private String name;

    private double price;

    private String serverRelativePath;

    public Image()
    {
    }

    public Image(final int id, final int imageCategoryId, final String name, final double price, final String serverRelativePath)
    {
        this.id = id;
        this.imageCategoryId = imageCategoryId;
        this.name = name;
        this.price = price;
        this.serverRelativePath = serverRelativePath;
    }

    public Date getFakeDate()
    {
        return fakeDate;
    }

    public int getId()
    {
        return id;
    }

    public int getImageCategoryId()
    {
        return imageCategoryId;
    }

    public String getName()
    {
        return name;
    }

    public double getPrice()
    {
        return price;
    }

    public String getServerRelativePath()
    {
        return serverRelativePath;
    }

    public boolean isFakeCheck()
    {
        return fakeCheck;
    }

    public void setFakeCheck(final boolean fakeCheck)
    {
        this.fakeCheck = fakeCheck;
    }

    public void setFakeDate(final Date fakeDate)
    {
        this.fakeDate = fakeDate;
    }

    public void setId(final int id)
    {
        this.id = id;
    }

    public void setImageCategoryId(final int imageCategoryId)
    {
        this.imageCategoryId = imageCategoryId;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public void setPrice(final double price)
    {
        this.price = price;
    }

    public void setServerRelativePath(final String serverRelativePath)
    {
        this.serverRelativePath = serverRelativePath;
    }
}
