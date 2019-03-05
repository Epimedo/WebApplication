package by.epam.halavin.maintask.dao.user;

import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.dataconverter.UserConverter;
import by.epam.halavin.maintask.dao.dataconverter.impl.PassengerConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerDAO implements UserDAO {
    private String selectUser = "select * from users where email = ?";
    private String updateUsers = "update users set " +
            "`status` = ?,`bonus` = ?,`discount` = ? where  user_id = ?";
    private String insertUsers = "INSERT INTO users(`email`,`name`,`surname`,`tel`," +
            "`status`,`password`,`bonus`,`discount`) VALUES " +
            "(?,?,?,?,'unban',?,null,null)";
    private String selectPassword = "select password from users where email = ?";
    private String selectUserByIndex = "Select * from (" +
            "SELECT row_number() OVER(order by name ASC) AS 'Row',user_id,email,name,surname,tel," +
            "status,password,bonus,discount" +
            " FROM mydb.users) As A Where A.Row >= ? and A.Row<=?";
    private String selectUserById = "select * from users where user_id = ?";


    @Override
    public User getUser(String email) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        User user = null;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(selectUser);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            UserConverter converter = new PassengerConverter();
            user = converter.convertFirstRow(resultSet);

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
            try {
                statement.close();
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
            statement = connection.prepareStatement(selectUserById);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            UserConverter converter = new PassengerConverter();
            user = converter.convertFirstRow(resultSet);

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }

        return user;
    }

    @Override
    public boolean registration(User user) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(insertUsers);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getTel());
            statement.setString(5, DigestUtils.sha256Hex(user.getPassword()));

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
    public void userUpdate(User user) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        Passenger passenger = (Passenger) user;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(updateUsers);
            statement.setString(1, passenger.getStatus());
            statement.setDouble(2, passenger.getBonus());
            statement.setDouble(3, passenger.getDiscount());
            statement.setInt(4, passenger.getId());

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
    }

    @Override
    public String getPassword(String email) throws DAOException {
        String password;
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(selectPassword);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            resultSet.first();

            password = resultSet.getString(1);

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

        return password;
    }

    @Override
    public List<User> getUsersIndexOf(int fr, int ls) throws DAOException {
        List<User> resultList = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(selectUserByIndex);
            statement.setInt(1, fr);
            statement.setInt(2, ls);
            ResultSet resultSet = statement.executeQuery();

            UserConverter userConverter = new PassengerConverter();
            resultList = userConverter.convertRows(resultSet);

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

        return resultList;
    }

    @Override
    public int getUsersCount() throws DAOException {
        int result = 0;
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("select count(*) from users");
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
}
