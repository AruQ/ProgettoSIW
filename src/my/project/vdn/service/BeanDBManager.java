package my.project.vdn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import my.project.vdn.dto.Image;
import my.project.vdn.dto.Order;
import my.project.vdn.dto.User;

public class BeanDBManager extends AbstractDBManager
{
    private static BeanDBManager instance;

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

    public List<Order> getAllOrders()
    {
        final List<Order> toRet = new ArrayList<>();
        final String query = "SELECT a.id as id_order, a.data, a.paid, a.sent, "
                + "c.id as id_image, c.name, c.image_category_id, c.server_relative_path, c.price, "
                + "d.id as id_user, d.username, d.password, d.creation_date, d.score "
                + "FROM orders as a INNER JOIN basket b ON a.id = b.order_id " + "INNER JOIN image c ON b.image_id = c.id "
                + "INNER JOIN user d ON b.user_id = d.id";

        final Connection conn = createConnection();

        Statement statement = null;
        ResultSet rs = null;
        try
        {
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            Order o = new Order();
            while (rs.next())
            {
                if (o.getId() != rs.getInt("id_order"))
                {
                    if (o.getId() != 0)
                    {
                        toRet.add(o);
                    }
                    o = new Order();
                    o.setId(rs.getInt("id_order"));
                    o.setData(rs.getDate("data"));
                    o.setPaid(rs.getBoolean("paid"));
                    o.setSent(rs.getBoolean("sent"));

                    final User u = new User();
                    u.setId(rs.getInt("id_user"));
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));
                    u.setScore(rs.getDouble("score"));
                    u.setCreationDate(rs.getDate("creation_date"));
                    o.setUser(u);
                }
                final Image i = new Image();
                i.setId(rs.getInt("id_image"));
                i.setImageCategoryId(rs.getInt("image_category_id"));
                i.setName(rs.getString("name"));
                i.setPrice(rs.getDouble("price"));
                i.setServerRelativePath(rs.getString("server_relative_path"));
                o.addImage(i);
            }
            if (o.getId() != 0)
            {
                toRet.add(o);
            }
        }
        catch (final SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeResultSet(rs);
            closeStatement(statement);
            closeConnection(conn);
        }
        return toRet;
    }

    public void updateOrder(final Order bean)
    {
        final String query = "UPDATE orders SET data=?, paid=?, sent=? WHERE id=?";

        final Connection conn = createConnection();

        PreparedStatement ps = null;
        try
        {
            ps = conn.prepareStatement(query);
            ps.setDate(1, convertJavaDateToSqlDate(bean.getData()));
            ps.setBoolean(2, bean.isPaid());
            ps.setBoolean(3, bean.isSent());

            ps.setInt(4, bean.getId());
            ps.execute();

        }
        catch (final SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        finally
        {
            closeStatement(ps);
            closeConnection(conn);
        }
    }
}
