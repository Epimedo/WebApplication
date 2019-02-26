package by.epam.halavin.maintask.dao;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.dataconverter.CarConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarManager {
    private String sqlSelectCarId = "select * from cars where car_id = ";
    private String sqlSelectCars = "select * from cars";

    public Car getCar(int id) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        Car result = new Car();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelectCarId +
                    +id + " ");
            CarConverter carConverter = new CarConverter();

            result = carConverter.convertFirstRow(resultSet);

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return result;
    }

    public List<Car> getCars() throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        List<Car> result = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelectCars);
            CarConverter carConverter = new CarConverter();

            result = carConverter.convertRows(resultSet);

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return result;
    }
}
