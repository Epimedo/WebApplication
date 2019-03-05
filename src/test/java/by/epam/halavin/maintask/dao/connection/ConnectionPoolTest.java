package by.epam.halavin.maintask.dao.connection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPoolTest {
    private static ConnectionPool pool;

    @BeforeClass
    public static void initConnectionPool() {
        pool = ConnectionPool.getInstance();
    }

    @Test
    public void getConnection() {
        Connection connection = pool.getConnection();
        String expected = "admin1@gmail.comadmin2@gmail.comadmin3@gmail.com";
        String result = "";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select email from admins");
            while (resultSet.next()) {
                result += resultSet.getString(1);
            }

        } catch (SQLException e) {

        }

        Assert.assertEquals(expected, result);

    }
}