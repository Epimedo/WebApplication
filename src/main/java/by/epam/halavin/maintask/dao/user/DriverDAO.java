package by.epam.halavin.maintask.dao.user;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.car.CarDAO;
import by.epam.halavin.maintask.dao.car.SimpleCarDAO;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.dataconverter.UserConverter;
import by.epam.halavin.maintask.dao.dataconverter.impl.DriverConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DriverDAO implements UserDAO {
    private String selectDriver = "" +
            "SELECT driver_id,email,drivers.name As driver_name,surname,tel,status,password,cars.name,cars.number " +
            "FROM mydb.drivers inner join mydb.cars on drivers.car_id=cars.car_id " +
            "where email = ?";
    private String selectDriverById = "" +
            "SELECT driver_id,email,drivers.name As driver_name,surname,tel,status,password,cars.name,cars.number " +
            "FROM mydb.drivers inner join mydb.cars on drivers.car_id=cars.car_id " +
            "where driver_id = ?";

    private String updateDrivers = "update drivers set " +
            "`status` = ? where driver_id = ?";
    private String selectPassword = "select password from drivers where email = ?";
    private String selectDriversByIndex = "select * from ( " +
            "SELECT row_number() OVER(order by surname ASC) AS 'Row',driver_id,email,drivers.name AS driver_name," +
            "surname,tel,status,password,cars.name,cars.number" +
            " FROM mydb.drivers inner join mydb.cars on drivers.car_id=cars.car_id ) As A where A.Row>=? and A.Row<=?";
    private String insertDriver = "INSERT INTO drivers(`email`,`name`,`surname`,`tel`," +
            "`status`,`password`,`car_id`) VALUES (?,?,?,?,?,?,?)";
    private String selectCount = "select count(*) from drivers";


    @Override
    public User getUser(String email) throws DAOException {
        User result = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(selectDriver);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            UserConverter converter = new DriverConverter();
            result = converter.convertFirstRow(resultSet);

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
    public User getUser(int id) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        User user = null;
        PreparedStatement statement = null;
        UserConverter converter = new DriverConverter();

        try {
            statement = connection.prepareStatement(selectDriverById);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

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
        Driver driver = (Driver) user;
        Connection connection = ConnectionPool.getInstance().getConnection();
        CarDAO carDAO = new SimpleCarDAO();
        carDAO.registration(driver.getCar());
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(insertDriver);
            statement.setString(1, driver.getEmail());
            statement.setString(2, driver.getName());
            statement.setString(3, driver.getSurname());
            statement.setString(4, driver.getTel());
            statement.setString(5, driver.getStatus());
            statement.setString(6, DigestUtils.sha256Hex(driver.getPassword()));
            statement.setInt(7,
                    carDAO.getCar(driver.getCar().getName(), driver.getCar().getNumber()).getId());

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
        Driver driver = (Driver) user;
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(updateDrivers);
            statement.setString(1, driver.getStatus());
            statement.setInt(2, driver.getId());
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
        List<User> users = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(selectDriversByIndex);
            statement.setInt(1, fr);
            statement.setInt(2, ls);
            ResultSet resultSet = statement.executeQuery();

            UserConverter converter = new DriverConverter();
            users = converter.convertRows(resultSet);

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

        return users;
    }

    @Override
    public int getUsersCount() throws DAOException {
        int result = 0;
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(selectCount);
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
