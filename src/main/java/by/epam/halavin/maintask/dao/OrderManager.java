package by.epam.halavin.maintask.dao;

import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderManager {
    private String sqlInsertOrder = "INSERT INTO `mydb`.`orders`" +
            "(`user_id`," +
            "`driver_id`," +
            "`cost`," +
            "`date`," +
            "`departure`," +
            "`arrival`)" +
            "VALUES";

    public void saveOrder(Order order) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlInsertOrder +
                    "(\"" + order.getPassenger().getId() + "\"," +
                    "\"" + order.getDriver().getId() + "\"," +
                    "\"" + order.getCost() + "\"," +
                    "\"" + new Date(order.getDate().getTime()) + "\"," +
                    "\"" + order.getDeparturePoint() + "\"," +
                    "\"" + order.getArrivalPoint() + "\");");


        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
