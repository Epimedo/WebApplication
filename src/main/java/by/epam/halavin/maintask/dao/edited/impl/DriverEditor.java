package by.epam.halavin.maintask.dao.edited.impl;

import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.edited.Edited;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.repository.SimpleRepository;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DriverEditor implements Edited {
    private String sqlUpdateDrviers = "update drivers set";
    private String conditioId = "where driver_id = ";

    @Override
    public void edit(HttpServletRequest request) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlUpdateDrviers +
                    "`status` = \"" + request.getParameter("status") + "\" " +
                    conditioId + request.getParameter("id") + "");

            SimpleRepository.DRIVER_REPOSITORY.listInit();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
