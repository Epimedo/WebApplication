package by.epam.halavin.maintask.dao.car;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.dataconverter.CarConverter;
import by.epam.halavin.maintask.dao.dataconverter.impl.SimpleCarConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SimpleCarDAO implements CarDAO {
    private String selectCarById = "select * from cars where car_id =?";
    private String selectCarByName = "select * from cars where name = ? and number = ?";
    private String selectCarIndexOf = "select * from (" +
            "SELECT row_number() OVER(order by name ASC) AS 'Row',car_id,name,number,checkup_end FROM mydb.cars) as A" +
            " where A.Row >=? and A.Row<=?";
    private String insertCar = "INSERT INTO `mydb`.`cars` (`name`,`number`,`checkup_end`) VALUES (?,?,?)";

    @Override
    public Car getCar(int id) throws DAOException {
        Car car = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(selectCarById);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            CarConverter carConverter = new SimpleCarConverter();
            car = carConverter.convertFirstRow(resultSet);

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

        return car;
    }

    @Override
    public List<Car> getCarsIndexOf(int fr, int ls) throws DAOException {
        List<Car> list = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(selectCarIndexOf);
            statement.setInt(1, fr);
            statement.setInt(2, ls);
            ResultSet resultSet = statement.executeQuery();

            CarConverter carConverter = new SimpleCarConverter();
            list = carConverter.convertRows(resultSet);
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

        return list;
    }

    @Override
    public boolean registration(Car car) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(insertCar);
            statement.setString(1, car.getName());
            statement.setString(2, car.getNumber());
            statement.setDate(3, new java.sql.Date(car.getDate().getTime()));

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
    public Car getCar(String name, String number) throws DAOException {
        Car car = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        CarConverter carConverter = new SimpleCarConverter();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(selectCarByName);
            statement.setString(1, name);
            statement.setString(2, number);

            ResultSet resultSet = statement.executeQuery();
            car = carConverter.convertFirstRow(resultSet);
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

        return car;
    }
}
