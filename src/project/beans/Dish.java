package project.beans;

import project.util.JsonConverter;

public class Dish
{
	protected Integer id;
	protected String name;
	protected String imageUrl;
	protected String description;
	protected Float avgRating;
	protected DishCategory category;

	public Dish(Integer id, String name, String imageUrl, String description, Float avgRating, DishCategory category)
	{
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.description = description;
		this.avgRating = avgRating;
		this.category = category;

	}

	public Dish()
	{
	}

	public Integer getId()
	{
		return id;
	}

	public void setID(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Float getAvgRating()
	{
		return avgRating;
	}

	public void setAvgRating(Float avgRating)
	{
		this.avgRating = avgRating;
	}

	public DishCategory getCategory()
	{
		return category;
	}

	public void setCategory(DishCategory category)
	{
		this.category = category;
	}

	public String toJson()
	{
		return JsonConverter.converterToJson(this);
	}

	@Override
	public String toString()
	{
		return "User [ID=" + id + ", name=" + name + ", description=" + description + ", imageUrl=" + imageUrl + ", avgRating="
				+ avgRating + ",category= " + category + "]";
	}

}
