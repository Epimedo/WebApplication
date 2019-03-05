package by.epam.halavin.maintask.dao.user;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.dataconverter.UserConverter;
import by.epam.halavin.maintask.dao.dataconverter.impl.AdminConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDAO implements UserDAO {
    private String selectAdmin = "select * from admins where email = ?";
    private String selectPassword = "select password from admins where email =?";
    private String selectAdminById = "select * from admins where admin_id = ?";

    @Override
    public User getUser(String email) throws DAOException {
        User user = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(selectAdmin);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            UserConverter converter = new AdminConverter();
            user = converter.convertFirstRow(resultSet);

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

        return user;
    }

    @Override
    public User getUser(int id) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        User user = null;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(selectAdminById);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            UserConverter converter = new AdminConverter();
            user = converter.convertFirstRow(resultSet);

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

        return user;
    }

    @Override
    public boolean registration(User user) throws DAOException {
        return false;
    }

    @Override
    public void userUpdate(User user) throws DAOException {

    }

    @Override
    public String getPassword(String email) throws DAOException {
        String result = "";
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(selectPassword);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            resultSet.first();

            result = resultSet.getString(1);

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
    public List<User> getUsersIndexOf(int fr, int ls) throws DAOException {
        return null;
    }

    @Override
    public int getUsersCount() throws DAOException {
        return 0;
    }
}
