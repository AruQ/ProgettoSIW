package project.beans;

import project.util.JsonConverter;

public class Cafeteria
{
	protected String name;
	protected Double latitude;
	protected Double longitude;

	public Cafeteria(String name, Double latitude, Double longitude)
	{
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Cafeteria()
	{

	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(Double latitude)
	{
		this.latitude = latitude;
	}

	public Double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(Double longitude)
	{
		this.longitude = longitude;
	}

	public String toJson()
	{
		return JsonConverter.converterToJson(this);
	}

	@Override
	public String toString()
	{
		return "Cafeteria [NAME=" + name + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
