package by.epam.halavin.maintask.dao.edited.impl;

import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.edited.Edited;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.repository.SimpleRepository;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PassengerEdited implements Edited {
    private String sqlUpdateUsers = "update users set";
    private String conditionId = "where user_id = ";

    @Override
    public void edit(HttpServletRequest request) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlUpdateUsers +
                    "`status` = \"" + request.getParameter("status") + "\", " +
                    "`bonus` = " + request.getParameter("bonus") + ", " +
                    "`discount` = " + request.getParameter("discount") + " " +
                    conditionId + request.getParameter("id") + "");

            SimpleRepository.PASSENGER_REPOSITORY.listInit();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
