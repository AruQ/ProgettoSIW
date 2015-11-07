package project.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import project.beans.Cafeteria;
import project.beans.Dish;
import project.beans.DishCategory;
import project.beans.User;

public class BeanDBManager extends AbstractDBManager
{
    private static BeanDBManager instance;
	private final SimpleDateFormat dateSDF = new SimpleDateFormat("dd-MM-yyyy");

    public static BeanDBManager getInstance()
    {
        if (instance == null)
        {
            instance = new BeanDBManager();

        }
        return instance;
    }

    private BeanDBManager()
    {
        super();
    }

    public int countAllUser()
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
        }
        catch (final SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            closeResultSet(rs);
            closeStatement(ps);
            closeConnection(conn);
        }
        return count;
    }


    public List<User> getAllUser()
    {
		final List<User> toReturn = new ArrayList<>();
		final String query = "SELECT * FROM USER";

        final Connection conn = createConnection();

        Statement statement = null;
        ResultSet rs = null;
        try
        {
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next())
            {
				final User user = new User();
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setEmail(rs.getString("EMAIL"));
				user.setProfileName(rs.getString("PROFILE_NAME"));
				user.setImageUrl(rs.getString("IMAGE_URL"));
				user.setSocial(rs.getBoolean("SOCIAL"));
				user.setAdmin(rs.getBoolean("ADMIN"));
				toReturn.add(user);
            }
        }
        catch (final SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            closeResultSet(rs);
            closeStatement(statement);
            closeConnection(conn);
        }
		return toReturn;
    }

	public User getUser(final String username)
    {
		User user = null;
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
				user = new User();
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setEmail(rs.getString("EMAIL"));
				user.setProfileName(rs.getString("PROFILE_NAME"));
				user.setImageUrl(rs.getString("IMAGE_URL"));
				user.setSocial(rs.getBoolean("SOCIAL"));
				user.setAdmin(rs.getBoolean("ADMIN"));
            }
        }
        catch (final SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            closeResultSet(rs);
            closeStatement(ps);
            closeConnection(conn);
        }
		return user;
    }

    public User getUser(final String username, final String password)
    {
		User user = null;
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
            while (rs.next())
            {
				user = new User();
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setEmail(rs.getString("EMAIL"));
				user.setProfileName(rs.getString("PROFILE_NAME"));
				user.setImageUrl(rs.getString("IMAGE_URL"));
				user.setSocial(rs.getBoolean("SOCIAL"));
				user.setAdmin(rs.getBoolean("ADMIN"));
            }
        }
        catch (final SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            closeResultSet(rs);
            closeStatement(ps);
            closeConnection(conn);
        }
		return user;
    }

	public boolean saveUser(final User user)
    {
		String procedure = "{call addUser(?,?,?)}";
		CallableStatement callableStatement = null;
		final Connection connection = createConnection();
		try
		{
			callableStatement = connection.prepareCall(procedure);
			callableStatement.setString(1, user.getUsername());
			callableStatement.setString(2, user.getPassword());
			callableStatement.setString(3, user.getEmail());
			callableStatement.executeUpdate();
			return true;
        }
        catch (final SQLException e)
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

	public boolean setImageToUser(final User user)
	{
		String procedure = "{call setImageToUser(?,?)}";
		CallableStatement callableStatement = null;
		final Connection connection = createConnection();
		try
		{
			callableStatement = connection.prepareCall(procedure);
			callableStatement.setString(1, user.getUsername());
			callableStatement.setString(2, user.getImageUrl());
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

	public boolean setProfileName(final User user)
	{
		String procedure = "{call setProfileName (?,?)}";
		CallableStatement callableStatement = null;
		final Connection connection = createConnection();
		try
		{
			callableStatement = connection.prepareCall(procedure);
			callableStatement.setString(1, user.getUsername());
			callableStatement.setString(2, user.getProfileName());
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

	public List<Dish> getDishesByCategory(DishCategory dishCategory)
	{
		final List<Dish> toReturn = new ArrayList<>();
		final String query = "SELECT * FROM DISH WHERE CATEGORY=?";

		final Connection conn = createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(query);
			ps.setString(1, dishCategory.getType());
			rs = ps.executeQuery();
			while (rs.next())
			{
				final Dish dish = new Dish();
				dish.setID(rs.getInt("ID"));
				dish.setName(rs.getString("NAME"));
				dish.setDescription(rs.getString("DESCRIPTION"));
				dish.setAvgRating(rs.getFloat("AVG_RATING"));
				dish.setImageUrl(rs.getString("IMAGE_URL"));
				dish.setCategory(dishCategory);
				toReturn.add(dish);
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
		return toReturn;

	}

	public List<Dish> getDishes()
	{
		final List<Dish> toReturn = new ArrayList<>();
		final String query = "SELECT * FROM DISH ORDER BY CATEGORY";

		final Connection conn = createConnection();

		Statement statement = null;
		ResultSet rs = null;
		try
		{
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next())
			{
				final Dish dish = new Dish();
				dish.setID(rs.getInt("ID"));
				dish.setName(rs.getString("NAME"));
				dish.setDescription(rs.getString("DESCRiPTION"));
				dish.setAvgRating(rs.getFloat("AVG_RATING"));
				dish.setImageUrl(rs.getString("IMAGE_URL"));
				dish.setCategory(DishCategory.getDishCategoryByID(rs.getString("CATEGORY")));
				toReturn.add(dish);
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
		return toReturn;

	}

	public int countAllDishes()
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
		return count;
	}

	public int countDishesByCategory(DishCategory dishCategory)
	{
		int count = 0;
		final String query = "SELECT COUNT(*) FROM DISH WHERE CATEGORY=?";

		final Connection conn = createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(query);
			ps.setString(1, dishCategory.getType());
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
		return count;

	}

	public boolean addCafeteria(final Cafeteria cafeteria)
	{
		String procedure = "{call addCafeteria(?,?,?)}";
		CallableStatement callableStatement = null;
		final Connection connection = createConnection();
		try
		{
			callableStatement = connection.prepareCall(procedure);
			callableStatement.setString(1, cafeteria.getName());
			callableStatement.setDouble(2, cafeteria.getLatitude());
			callableStatement.setDouble(3, cafeteria.getLongitude());
			callableStatement.execute();
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

	public boolean updateDish(final Dish dish)
	{
		String procedure = "{call updateDish(?,?,?,?,?)}";
		CallableStatement callableStatement = null;
		final Connection connection = createConnection();
		try
		{
			callableStatement = connection.prepareCall(procedure);
			callableStatement.setInt(1, dish.getId());
			callableStatement.setString(2, dish.getName());
			callableStatement.setString(3, dish.getDescription());
			callableStatement.setString(4, dish.getImageUrl());
			callableStatement.setInt(5, dish.getCategory().getId());
			callableStatement.execute();
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

	public boolean addDish(final Dish dish)
	{
		String procedure = "{call addDish(?,?,?,?)}";
		CallableStatement callableStatement = null;
		final Connection connection = createConnection();
		try
		{
			callableStatement = connection.prepareCall(procedure);
			callableStatement.setString(1, dish.getName());
			callableStatement.setString(2, dish.getDescription());
			callableStatement.setString(3, dish.getImageUrl());
			callableStatement.setInt(4, dish.getCategory().getId());
			callableStatement.execute();
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

	public boolean deleteDish(Dish dish)
	{
		final String query = "DELETE FROM DISH WHERE ID=?";
		final Connection conn = createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(query);
			ps.setInt(1, dish.getId());

			ps.executeQuery();
			return true;

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
		return false;

	}

	public Cafeteria getCafeteria(final String name)
	{
		Cafeteria cafeteria = null;

		final String query = "SELECT * FROM CAFETERIA WHERE NAME = ?";

		final Connection conn = createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(query);
			ps.setString(1, name);

			rs = ps.executeQuery();
			if (rs.next())
			{
				cafeteria = new Cafeteria();
				cafeteria.setName(rs.getString("NAME"));
				cafeteria.setLatitude(rs.getDouble("LATITUDE"));
				cafeteria.setLongitude(rs.getDouble("LONGITUDE"));

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
		return cafeteria;
	}

	public List<Cafeteria> getAllCafeterias()
	{
		java.util.List<Cafeteria> cafeterias = new ArrayList<Cafeteria>();

		final String query = "SELECT * FROM CAFETERIA ";

		final Connection conn = createConnection();

		Statement statement = null;
		ResultSet rs = null;
		try
		{
			statement = conn.createStatement();
			rs = statement.executeQuery(query);

			while (rs.next())
			{
				Cafeteria cafeteria = new Cafeteria();
				cafeteria.setName(rs.getString("NAME"));
				cafeteria.setLatitude(rs.getDouble("LATITUDE"));
				cafeteria.setLongitude(rs.getDouble("LONGITUDE"));
				cafeterias.add(cafeteria);

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
		return cafeterias;
	}

	public boolean updateCafeteria(final Cafeteria cafeteria)
	{
		String procedure = "{call updateCafeteria(?,?,?)}";
		CallableStatement callableStatement = null;
		final Connection connection = createConnection();
		try
		{
			callableStatement = connection.prepareCall(procedure);
			callableStatement.setString(1, cafeteria.getName());
			callableStatement.setDouble(2, cafeteria.getLatitude());
			callableStatement.setDouble(3, cafeteria.getLongitude());
			callableStatement.execute();
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

	public User getUser(String id, String imageUrl, String profileName, String email)
	{
		User user = null;
		final String procedure = "{call addUserByID(?,?,?,?)}";

		final Connection connection = createConnection();

		CallableStatement callableStatement = null;
		ResultSet rs = null;
		try
		{
			callableStatement = connection.prepareCall(procedure);
			callableStatement.setString(1, id);
			callableStatement.setString(2, imageUrl);
			callableStatement.setString(3, profileName);
			callableStatement.setString(4, email);

			callableStatement.execute();

			rs = callableStatement.getResultSet();
			if (rs.next())
			{
				user = new User();
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setEmail(rs.getString("EMAIL"));
				user.setProfileName(rs.getString("PROFILE_NAME"));
				user.setImageUrl(rs.getString("IMAGE_URL"));
				user.setSocial(rs.getBoolean("SOCIAL"));
				user.setAdmin(rs.getBoolean("ADMIN"));
			}
		} catch (final SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			closeResultSet(rs);
			closeStatement(callableStatement);
			closeConnection(connection);
		}
		return user;
	}

	public List<Dish> getDishesByDay(String data)
	{
		final List<Dish> toReturn = new ArrayList<>();
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

			while (rs.next())
			{
				final Dish dish = new Dish();
				dish.setID(rs.getInt("ID"));
				dish.setName(rs.getString("NAME"));
				dish.setDescription(rs.getString("DESCRiPTION"));
				dish.setAvgRating(rs.getFloat("AVG_RATING"));
				dish.setImageUrl(rs.getString("IMAGE_URL"));
				dish.setCategory(DishCategory.getDishCategoryByID(rs.getString("CATEGORY")));
				toReturn.add(dish);
			}
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
		return toReturn;
	}

	public void addDishToMenu(String date, int dishID)
	{
		final String query = "INSERT IGNORE INTO DAILYMENU (MENUDATE, DISH) VALUE (?,?)";

		final Connection conn = createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{

			java.sql.Date dataDB = new Date(dateSDF.parse(date).getTime());
			ps = conn.prepareStatement(query);
			ps.setDate(1, dataDB);
			ps.setInt(2, dishID);

			ps.executeQuery();


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
	}

	public void deleteDishToMenu(String date, int dishID)
	{
		final String query = "DELETE FROM DAILYMENU WHERE MENUDATE=? AND DISH =?";

		final Connection conn = createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{

			java.sql.Date dataDB = new Date(dateSDF.parse(date).getTime());
			ps = conn.prepareStatement(query);
			ps.setDate(1, dataDB);
			ps.setInt(2, dishID);

			ps.executeQuery();

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
	}

}
