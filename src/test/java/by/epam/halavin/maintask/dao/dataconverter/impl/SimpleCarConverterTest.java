package by.epam.halavin.maintask.dao.dataconverter.impl;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.dataconverter.CarConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class SimpleCarConverterTest {
    private static Car car = new Car();

    @BeforeClass
    public static void initCar() throws ParseException {
        car.setId(1);
        car.setName("renault Logan");
        car.setNumber("5647BN-7");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        java.sql.Date date = new java.sql.Date(simpleDateFormat.parse("2020-08-11").getTime());
        car.setDate(date);
    }

    @Test
    public void convertFirstRow() throws DAOException, SQLException {
        int id = 1;
        Connection connection = ConnectionPool.getInstance().getConnection();
        CarConverter carConverter = new SimpleCarConverter();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from cars where car_id = '" + id + "'");

        Car expected = carConverter.convertFirstRow(resultSet);

        Assert.assertEquals(expected, car);

    }

    @Test
    public void convertRows() throws DAOException,SQLException {
        String expected = "[Car{id=1, number='5647BN-7', name='renault Logan', date=null}, Car{id=2, number='9234CA-7'," +
                " name='renault Logan', date=null}, Car{id=3, number='4213TU-7', name='renault Logan', date=null}, " +
                "Car{id=4, number='4562SH-7', name='volkswagen Polo', date=null}, Car{id=5, number='1234GH-7', " +
                "name='volkswagen Polo', date=null}, Car{id=6, number='4536TR-7', name='renault Logan', date=null}, " +
                "Car{id=19, number='2323TY-7', name='renault Logan', date=null}, Car{id=24, number='1231'," +
                " name='renault Logan', date=null}, Car{id=28, number='1234HR-7', name='renault Logan', date=null}, " +
                "Car{id=31, number='1020HR-7', name='222', date=null}]";
        Connection connection = ConnectionPool.getInstance().getConnection();
        CarConverter carConverter = new SimpleCarConverter();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from cars ");

        List<Car> list = carConverter.convertRows(resultSet);
        Assert.assertEquals(expected,list.toString());
    }
}