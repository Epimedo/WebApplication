package by.epam.halavin.maintask.dao.order;

import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.dataconverter.OrderConverter;
import by.epam.halavin.maintask.dao.dataconverter.impl.SimpleOrderConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SimpleOrderDAO implements OrderDAO {
    private String selectOrder = "SELECT * FROM mydb.orders inner join mydb.users on orders.user_id = users.user_id" +
            " inner join mydb.drivers" +
            " on orders.driver_id = drivers.driver_id where orders.user_id = ? and date = ? ";
    private String selectOrders = "" +
            "select * from(" +
            "SELECT row_number() OVER(order by date ASC) AS 'Row',order_id,orders.user_id," +
            "orders.driver_id,cost,date,departure,arrival" +
            " FROM mydb.orders inner join mydb.users on orders.user_id = users.user_id inner join mydb.drivers " +
            "on orders.driver_id = drivers.driver_id where orders.user_id = ?) As A" +
            " where A.Row>=? and A.Row<=?";
    private String selectOrdersCount = "SELECT COUNT(*) FROM mydb.orders inner join mydb.users on orders.user_id = users.user_id" +
            " inner join mydb.drivers" +
            " on orders.driver_id = drivers.driver_id where orders.user_id = ?";
    private String insertOrder = "insert into `mydb`.`orders` (`user_id`,`driver_id`,`cost`,`date`,`departure`,`arrival`)" +
            " values (?,?,?,?,?,?)";

    @Override
    public boolean saveOrder(Order order) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(insertOrder);
            statement.setInt(1, order.getPassenger().getId());
            statement.setInt(2, order.getDriver().getId());
            statement.setDouble(3, order.getCost());
            statement.setDate(4, new java.sql.Date(order.getDate().getTime()));
            statement.setString(5, order.getDeparturePoint());
            statement.setString(6, order.getArrivalPoint());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        return true;
    }

    @Override
    public Order getUserOrder(int id, Date date) throws DAOException {
        Order order = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        OrderConverter converter = new SimpleOrderConverter();
        DateFormat format = new SimpleDateFormat("yyyy-m-dd", Locale.ENGLISH);
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(selectOrder);
            statement.setInt(1, id);
            statement.setString(2, format.format(date));
            ResultSet resultSet = statement.executeQuery();

            order = converter.convertFirstRow(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }

        return order;
    }

    @Override
    public int getUserOrderCount(int id) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        int result = 0;

        try {
            statement = connection.prepareStatement(selectOrdersCount);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.first();
            result = resultSet.getInt(1);

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        return result;
    }

    @Override
    public List<Order> getUserOrdersIndexOf(int id, int fr, int ls) throws DAOException {
        List<Order> listOrder = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        OrderConverter converter = new SimpleOrderConverter();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(selectOrders);
            statement.setInt(1, id);
            statement.setInt(2, fr);
            statement.setInt(3, ls);
            ResultSet resultSet = statement.executeQuery();

            listOrder = converter.convertRows(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }

        return listOrder;
    }
}
