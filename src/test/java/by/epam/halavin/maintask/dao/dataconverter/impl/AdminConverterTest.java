package by.epam.halavin.maintask.dao.dataconverter.impl;

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

public class AdminConverterTest {
    private static User expected = new User();

    @BeforeClass
    public static void initUser() {
        expected.setId(2);
        expected.setName("Боб");
        expected.setSurname("Грошин");
        expected.setTel("+375 44 909 101 11");
        expected.setPassword("www111");
        expected.setEmail("admin1@gmail.com");
    }

    @Test
    public void convert() throws SQLException, DAOException {
        User result = new User();
        String email = "admin1@gmail.com";
        Connection connection = ConnectionPool.getInstance().getConnection();
        UserConverter converter = new AdminConverter();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from admins where email = '" + email + "'");

        result = converter.convertFirstRow(resultSet);

        Assert.assertEquals(expected, result);
    }
}