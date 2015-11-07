package project.util;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

public class JsonConverter
{

	public static String converterToJson(Object toConvert)
	{
		try
		{

			final StringWriter stringWriter = new StringWriter();
			final JsonFactory jsonFactory = new JsonFactory();
			final JsonGenerator jsonGenerator = jsonFactory.createGenerator(stringWriter);

			jsonGenerator.writeStartObject();
			Field[] declaredFields = toConvert.getClass().getDeclaredFields();

			for (Field field : declaredFields)
			{
				field.setAccessible(true);
				String fieldName = field.getName();

				Class<?> fieldType = field.getType();
				Object object = field.get(toConvert);

				if (fieldType == Enum.class)
				{
					Object[] consts = ((Class<? extends Object>) object).getEnumConstants();
					String toWrite = "";
					for (Object constant : consts)
					{
						if (toWrite.length() != 0)
							toWrite += "," + constant;

					}
					writeJson(String.class, fieldName, jsonGenerator, toWrite);
				} else
				{
					writeJson(fieldType, fieldName, jsonGenerator, object);
				}
			}
			jsonGenerator.close();
			return stringWriter.toString();

		} catch (IllegalArgumentException | IllegalAccessException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	private static void writeJson(Class<?> fieldType, String fieldName, JsonGenerator jsonGenerator, Object object)
	{
		try
		{
			if (fieldType == String.class)
			{
				jsonGenerator.writeStringField(fieldName, (String) object);

			} else if (fieldType == Integer.class)
			{
				jsonGenerator.writeNumberField(fieldName, (Integer) object);
			} else if (fieldType == Float.class)
			{
				jsonGenerator.writeNumberField(fieldName, (Float) object);
			} else if (fieldType == Double.class)
			{
				jsonGenerator.writeNumberField(fieldName, (Double) object);
			}

			else if (fieldType == Boolean.class)
			{
				jsonGenerator.writeBooleanField(fieldName, (Boolean) object);
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
