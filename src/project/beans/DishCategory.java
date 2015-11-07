package project.beans;

import project.util.JsonConverter;

public enum DishCategory
{
	MAIN_COURSE(1, "Main Course", 1), SECOND_COURSE(2, "Second Course", 2), SIDE_DISH(3, "Side Dish", 1);

	protected final int id;
	protected final String category;
	protected final int points;

	private DishCategory(int id, String type, int points)
	{
		this.id = id;
		this.category = type;
		this.points = points;
	}

	public int getId()
	{
		return id;
	}

	public String getType()
	{
		return category;
	}

	public int getPoints()
	{
		return points;
	}

	public static DishCategory getDishCategory(String category)
	{
		switch (category)
		{
		case "Main Course":
			return MAIN_COURSE;
		case "Second Course":
			return SECOND_COURSE;
		case "Side Dish":
			return SIDE_DISH;
		default:
			return null;
		}

	}

	public static DishCategory getDishCategoryByID(String category)
	{
		switch (category)
		{
		case "1":
			return MAIN_COURSE;
		case "2":
			return SECOND_COURSE;
		case "3":
			return SIDE_DISH;
		default:
			return null;
		}

	}

	public String toJSon()
	{
		return JsonConverter.converterToJson(this);
	}

	@Override
	public String toString()
	{
		return "DishCategory [id=" + id + ", type=" + category + ", points=" + points + "]";
	}

}
