package by.epam.halavin.maintask.dao.dataconverter.impl;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.dataconverter.UserConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class DriverConverterTest {
    private static Driver expected = new Driver();

    @BeforeClass
    public static void initDriver() {
        expected.setId(1);
        expected.setEmail("wolf@gmail.com");
        expected.setName("Антон");
        expected.setSurname("Веровчик");
        expected.setStatus("unban");
        expected.setTel("+375 44 000 111 23");
        expected.setPassword("qqqq");
        expected.setCarName("renault Logan");
        expected.setCarNumber("5647BN-7");
    }

    @Test
    public void convert() throws SQLException, DAOException {
        User result;
        Connection connection = ConnectionPool.getInstance().getConnection();
        String email = "wolf@gmail.com";
        UserConverter converter = new DriverConverter();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("" +
                "SELECT driver_id,email,drivers.name,surname,tel,status,password,cars.name,cars.number " +
                "FROM mydb.drivers inner join mydb.cars on drivers.car_id=cars.car_id " +
                "where email = '" + email + "'");

        result = converter.convertFirstRow(resultSet);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void convertRows() throws SQLException, DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("" +
                "SELECT driver_id,email,drivers.name,surname,tel,status,password,cars.name,cars.number " +
                "FROM mydb.drivers inner join mydb.cars on drivers.car_id=cars.car_id ");

        DriverConverter converter = new DriverConverter();
        List<User> result = converter.convertRows(resultSet);

        System.out.println(Objects.toString(result));
    }
}