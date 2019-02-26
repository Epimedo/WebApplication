package by.epam.halavin.maintask.dao.edited.impl;

import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.repository.SimpleRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PassengerBonusUpdate {
    private static final double PROCENT = .05;
    private String sqlUpdateUsers = "update users set";
    private String conditionId = "where user_id = ";
    private String sqlSelectBonusUser = "select bonus from users where  user_id = ";

    public void edit(Order order) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        double cost = (double) Math.round((order.getCost() * PROCENT * 100d)) / 100d;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelectBonusUser +
                    order.getPassenger().getId() + "");
            resultSet.first();
            cost += resultSet.getDouble(1);
            cost = (double) Math.round(cost * 100d) / 100d;

            statement.executeUpdate(sqlUpdateUsers +
                    "`bonus` = " + cost + " " +
                    conditionId + order.getPassenger().getId() + "");

            SimpleRepository.PASSENGER_REPOSITORY.listInit();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
