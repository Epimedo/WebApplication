package by.epam.halavin.maintask.dao.connection;

import by.epam.halavin.maintask.dao.exception.DAOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionPoolTest {
    private static ConnectionPool pool;

    @BeforeClass
    public static void initConnectionPool() throws DAOException {
        pool = ConnectionPool.getInstance();
    }

    @Test
    public void getConnection() throws DAOException {
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
            e.printStackTrace();
        }

        Assert.assertEquals(expected, result);

    }

    @Test
    public void releaseConnection() throws DAOException {
        ArrayList<Connection> list = new ArrayList<>(10);
        int sum = 0;
        int expected = 0;

        for (int i = 0; i < 10; i++) {
            list.add(i, pool.getConnection());
            sum++;
        }
        for (int i = 0; i < 10; i++) {
            pool.releaseConnection(list.get(i));
            expected++;
        }

        Assert.assertEquals(sum, expected);
    }

    @Test(expected = DAOException.class)
    public void releaseConnectionException() throws DAOException, SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "1111";
        Connection connection = DriverManager.getConnection(url, user, password);

        pool.releaseConnection(connection);
    }

}