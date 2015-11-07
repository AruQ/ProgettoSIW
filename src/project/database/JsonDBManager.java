package project.database;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import project.beans.DishCategory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

public class JsonDBManager extends AbstractDBManager
{
	private static JsonDBManager instance;

	public static JsonDBManager getInstance()
	{
		if (instance == null)
		{
			instance = new JsonDBManager();

		}
		return instance;
	}

	private final SimpleDateFormat dateSDF = new SimpleDateFormat("dd-MM-yyyy");

	private final SimpleDateFormat timestampSDF = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public String countAllUser()
	{
		int count = 0;
		final String query = "SELECT count(*) as num FROM USER";

		final Connection conn = createConnection();

		PreparedStatement ps = null;

		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next())
			{
				count = rs.getInt("num");
			}
		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
		return "{ 'result': " + count + "}";
	}


	public String countAllDishes()
	{
		int count = 0;
		final String query = "SELECT count(*) as num FROM DISH";

		final Connection conn = createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next())
			{
				count = rs.getInt("num");
			}
		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
		return "{ 'result': " + count + "}";
	}

	public String countDishesByCategory(DishCategory dishCategory)
	{
		int count = 0;
		final String query = "SELECT COUNT(*) FROM DISH WHERE CATEGORY=?";

		final Connection conn = createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(query);
			ps.setInt(1, dishCategory.getId());
			rs = ps.executeQuery();
			if (rs.next())
			{
				count = rs.getInt("num");
			}
		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
		return "{ 'result': " + count + "}";

	}

	public String getDishes()
	{
		String json = null;
		final String query = "SELECT * FROM DISH";

		final Connection conn = createConnection();

		Statement statement = null;
		ResultSet rs = null;
		try
		{
			statement = conn.createStatement();
			rs = statement.executeQuery(query);

			json = toJSON(rs);
		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(statement);
			closeConnection(conn);
		}
		return json;

	}

	public String getDishesByCategory(DishCategory dishCategory)
	{
		String json = null;
		final String query = "SELECT * FROM DISH WHERE CATEGORY = ?";

		final Connection conn = createConnection();

		PreparedStatement statement = null;
		ResultSet rs = null;
		try
		{
			statement = conn.prepareStatement(query);
			statement.setInt(1, dishCategory.getId());
			rs = statement.executeQuery();
			json = toJSON(rs);
		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(statement);
			closeConnection(conn);
		}
		return json;

	}


	public String getUser(final String username)
	{
		String json = null;
		final String query = "SELECT * FROM USER WHERE USERNAME = ?";

		final Connection conn = createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(query);
			ps.setString(1, username);

			rs = ps.executeQuery();
			if (rs.next())
			{
				json = toJSON(rs);
			}
		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
		return json;
	}

	public String checkUsername(final String username)
	{
		String json = null;
		final String query = "SELECT * FROM USER WHERE USERNAME = ?";

		final Connection conn = createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(query);
			ps.setString(1, username);

			rs = ps.executeQuery();
			if (rs.next())
			{
				json = "'username': 'false'";
			} else
			{
				json = "'username': 'true'";
			}
		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
		return json;
	}

	public String checkEmail(final String email)
	{
		String json = null;
		final String query = "SELECT * FROM USER WHERE EMAIL = ?";

		final Connection conn = createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(query);
			ps.setString(1, email);

			rs = ps.executeQuery();
			if (rs.next())
			{
				json = "'email': 'false'";
			} else
			{
				json = "'email': 'true'";
			}
		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
		return json;
	}


	public String getUser(final String username, final String password)
	{
		String json = null;
		final String query = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?";

		final Connection conn = createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();

			json = toJSON(rs);
		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
		return json;
	}

	public String getDishComments(final int id)
	{
		String json = null;
		final String query = "SELECT *  FROM COMMENT,USER WHERE COMMENT.DISH = ? AND COMMENT.USERNAME=USER.USERNAME ORDER BY COMMENT.DATA DESC";

		final Connection conn = createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);

			rs = ps.executeQuery();
			json = toJSON(rs);
		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
		return (json == null) ? "[]" : json;

	}

	public String getDishRatingByUser(final int id, final project.beans.User user)
	{
		String json = null;
		final String query = "SELECT *  FROM RATING WHERE DISH=? AND USERNAME=?";

		final Connection conn = createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, user.getUsername());

			rs = ps.executeQuery();

			json = toJSON(rs);

		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
		return (json == null) ? "[]" : json;

	}

	public String getDishFromID(int id)
	{
		String json = null;
		final String query = "SELECT * FROM DISH WHERE ID=?";

		final Connection conn = createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			json = toJSON(rs);
		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
		return json;
	}

	public String getCafeterias()
	{
		String json = null;
		final String query = "SELECT *  FROM CAFETERIA";

		final Connection conn = createConnection();

		Statement statement = null;
		ResultSet rs = null;
		try
		{
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next())
			{
				json = toJSON(rs);
			}
		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(statement);
			closeConnection(conn);
		}
		return json;
	}

	public String getCafeteria(final String name)
	{
		String json = null;
		final String query = "SELECT * FROM CAFETERIA WHERE NAME = ?";

		final Connection conn = createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(query);
			ps.setString(1, name);

			rs = ps.executeQuery();
			json = toJSON(rs);
		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
		return json;
	}

	public String getAllCafeterias()
	{
		String json = null;
		final String query = "SELECT * FROM CAFETERIA ";

		final Connection conn = createConnection();

		Statement statement = null;
		ResultSet rs = null;
		try
		{
			statement = conn.createStatement();
			rs = statement.executeQuery(query);

			json = toJSON(rs);
		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(statement);
			closeConnection(conn);
		}
		return json;
	}

	public String addComment(String username, String text, int dishID)
	{

		String json = null;
		String procedure = "{call addComment(?,?,?)}";
		CallableStatement callableStatement = null;
		final Connection connection = createConnection();
		try
		{
			callableStatement = connection.prepareCall(procedure);
			callableStatement.setString(1, username);
			callableStatement.setInt(2, dishID);
			callableStatement.setString(3, text);
			callableStatement.execute();
			ResultSet resultSet = callableStatement.getResultSet();
			json = toJSON(resultSet);

		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeStatement(callableStatement);
			closeConnection(connection);
		}
		return (json == null) ? "" : json;
	}

	public boolean updateComment(String username, Integer dishID, String timestamp, String text)
	{

		String procedure = "{call updateComment(?,?,?,?)}";
		CallableStatement callableStatement = null;
		final Connection connection = createConnection();
		try
		{
			java.util.Date date = null;
			date = timestampSDF.parse(timestamp);
			java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
			callableStatement = connection.prepareCall(procedure);
			callableStatement.setString(1, username);
			callableStatement.setInt(2, dishID);
			callableStatement.setTimestamp(3, timeStampDate);
			callableStatement.setString(4, text);
			callableStatement.execute();


			return true;

		} catch (final SQLException | ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeStatement(callableStatement);
			closeConnection(connection);
		}
		return false;
	}

	public boolean deleteComment(String username, int dishID, String timestamp)
	{

		String procedure = "{call deleteComment(?,?,?)}";
		CallableStatement callableStatement = null;
		final Connection connection = createConnection();
		try
		{
			java.util.Date date = null;
			date = timestampSDF.parse(timestamp);
			java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
			callableStatement = connection.prepareCall(procedure);
			callableStatement.setString(1, username);
			callableStatement.setInt(2, dishID);
			callableStatement.setTimestamp(3, timeStampDate);
			callableStatement.executeUpdate();
			return true;
		} catch (final SQLException | ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			finally
		{
			closeStatement(callableStatement);
			closeConnection(connection);
		}
		return false;
	}

	public boolean addRating(String username, int dishID, float points)
	{
		String procedure = "{call addRating(?,?,?)}";
		CallableStatement callableStatement = null;
		final Connection connection = createConnection();
		try
		{
			callableStatement = connection.prepareCall(procedure);
			callableStatement.setString(1, username);
			callableStatement.setInt(2, dishID);
			callableStatement.setFloat(3, points);
			callableStatement.executeUpdate();
			return true;
		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeStatement(callableStatement);
			closeConnection(connection);
		}
		return false;
	}

	
	public String bestDishes(int limit)
	{
		String json = null;
		final String query = "SELECT * FROM DISH ORDER BY AVG_RATING DESC LIMIT ?;";

		final Connection conn = createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(query);
			ps.setInt(1, limit);

			rs = ps.executeQuery();
			json = toJSON(rs);
		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
		return json;
	}

	public String getAvaibleDays()
	{
		String json = null;
		final String query = "SELECT DISTINCT MENUDATE FROM DAILYMENU;";

		final Connection conn = createConnection();

		Statement statement = null;
		ResultSet rs = null;
		try
		{
			statement = conn.createStatement();
			rs = statement.executeQuery(query);

			json = toJSON(rs);
		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(statement);
			closeConnection(conn);
		}
		return json;
	}

	public String getDishesByDay(String data)
	{
		String json = null;
		final String query = "SELECT * FROM DAILYMENU, DISH WHERE DISH.ID=DAILYMENU.DISH AND DAILYMENU.MENUDATE=?";

		final Connection conn = createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{

			java.sql.Date dataDB = new Date(dateSDF.parse(data).getTime());
			ps = conn.prepareStatement(query);
			ps.setDate(1, dataDB);

			rs = ps.executeQuery();

			json = toJSON(rs);
		} catch (final SQLException | ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
		return json;
	}
	protected Map<String, Object> getEntityFromResultSet(final ResultSet resultSet) throws SQLException
	{
		final ResultSetMetaData metaData = resultSet.getMetaData();
		final int columnCount = metaData.getColumnCount();
		final Map<String, Object> resultsMap = new HashMap<>();
		for (int i = 1; i <= columnCount; ++i)
		{
			final String columnName = metaData.getColumnName(i).toLowerCase();
			final Object object = resultSet.getObject(i);
			resultsMap.put(columnName, object);
		}
		return resultsMap;
	}

	protected String toJSON(final ResultSet resultSet)
	{

		try
		{
			final StringWriter stringWriter = new StringWriter();
			final JsonFactory f = new JsonFactory();
			JsonGenerator g;
			g = f.createGenerator(stringWriter);

			final ResultSetMetaData metaData = resultSet.getMetaData();
			final int columnCount = metaData.getColumnCount();
			g.writeStartArray();
			while (resultSet.next())
			{
				g.writeStartObject();
				for (int i = 1; i < columnCount + 1; i++)
				{
					final String column_name = metaData.getColumnName(i).toLowerCase();

					if (metaData.getColumnType(i) == java.sql.Types.BOOLEAN)
					{
						g.writeBooleanField(column_name, resultSet.getBoolean(column_name));
					} else if (metaData.getColumnType(i) == java.sql.Types.BLOB)
					{
						g.writeObjectField(column_name, resultSet.getBlob(column_name));
					} else if (metaData.getColumnType(i) == java.sql.Types.DOUBLE)
					{
						g.writeNumberField(column_name, resultSet.getDouble(column_name));
					} else if (metaData.getColumnType(i) == java.sql.Types.FLOAT)
					{
						g.writeNumberField(column_name, resultSet.getFloat(column_name));
					} else if (metaData.getColumnType(i) == java.sql.Types.INTEGER)
					{
						g.writeNumberField(column_name, resultSet.getInt(column_name));
					} else if (metaData.getColumnType(i) == java.sql.Types.NVARCHAR)
					{
						g.writeStringField(column_name, resultSet.getNString(column_name));
					} else if (metaData.getColumnType(i) == java.sql.Types.VARCHAR)
					{
						final String string = resultSet.getString(column_name);
						g.writeStringField(column_name, string);
					} else if (metaData.getColumnType(i) == java.sql.Types.TINYINT)
					{
						g.writeNumberField(column_name, resultSet.getInt(column_name));
					} else if (metaData.getColumnType(i) == java.sql.Types.SMALLINT)
					{
						g.writeNumberField(column_name, resultSet.getInt(column_name));
					} else if (metaData.getColumnType(i) == java.sql.Types.DATE)
					{
						final Date date = resultSet.getDate(column_name);
						g.writeStringField(column_name, dateSDF.format(date));
					} else if (metaData.getColumnType(i) == java.sql.Types.TIMESTAMP)
					{
						final Timestamp date = resultSet.getTimestamp(column_name);
						g.writeStringField(column_name, timestampSDF.format(date));
					} else
					{
						g.writeObjectField(column_name, resultSet.getObject(column_name));
					}
				}
				g.writeEndObject();
			}
			g.writeEndArray();
			g.close();
			return stringWriter.toString();

		} catch (IOException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}
