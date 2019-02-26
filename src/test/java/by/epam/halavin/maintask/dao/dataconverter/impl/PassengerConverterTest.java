package by.epam.halavin.maintask.dao.dataconverter.impl;

import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
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

public class PassengerConverterTest {
    private static Passenger expected;

    @BeforeClass
    public static void initUser() {
        expected = new Passenger();
        expected.setId(1);
        expected.setName("Федор");
        expected.setSurname("Федоров");
        expected.setEmail("wowka@gmail.com");
        expected.setTel("+375 29 444 77 99");
        expected.setStatus("unban");
        expected.setPassword("1111");
    }

    @Test
    public void convert() throws SQLException, DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String email = "wowka@gmail.com";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users where email = '" + email + "'");
        PassengerConverter converter = new PassengerConverter();
        User result = converter.convertFirstRow(resultSet);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void convertRows() throws SQLException, DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users ");
        PassengerConverter converter = new PassengerConverter();
        List<User> result = converter.convertRows(resultSet);

        System.out.println(Objects.toString(result));
    }

}