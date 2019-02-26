package by.epam.halavin.maintask.dao.repository;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.dataconverter.UserConverter;
import by.epam.halavin.maintask.dao.dataconverter.impl.DriverConverter;
import by.epam.halavin.maintask.dao.dataconverter.impl.PassengerConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public enum SimpleRepository {
    DRIVER_REPOSITORY("SELECT driver_id,email,drivers.name,surname,tel,status,password,cars.name,cars.number " +
            "FROM mydb.drivers inner join mydb.cars on drivers.car_id=cars.car_id ", new DriverConverter()),
    PASSENGER_REPOSITORY("select * from users ", new PassengerConverter());

    private List<User> list = new ArrayList<>();
    private String request;
    private UserConverter converter;

    SimpleRepository(String request, UserConverter converter) {
        this.request = request;
        this.converter = converter;
    }

    public void listInit() throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            list = converter.convertRows(resultSet);

        } catch (SQLException | DAOException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public List<User> getList() throws DAOException {
        synchronized (this) {
            try {
                if (list.size() == 0) {
                    listInit();
                }
            } catch (DAOException e) {
                throw new DAOException(e);
            }
        }
        return list;
    }
}
