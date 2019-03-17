package by.epam.halavin.maintask.dao.dataconverter.impl;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.dataconverter.UserConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;
import org.apache.commons.codec.digest.DigestUtils;
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
        expected.setTel("+375 44 000 11 23");
        expected.setPassword(DigestUtils.sha256Hex("qqqq"));
        expected.setCarName("renault Logan");
        expected.setCarNumber("5647BN-7");
        Car car = new Car();
        car.setId(1);
        car.setNumber("5647BN-7");
        car.setName("renault Logan");
        expected.setCar(car);
    }

    @Test
    public void convert() throws SQLException, DAOException {
        User result;
        Connection connection = ConnectionPool.getInstance().getConnection();
        String email = "wolf@gmail.com";
        UserConverter converter = new DriverConverter();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("" +
                "SELECT driver_id,email,drivers.name as driver_name,surname,tel,status,password,cars.name,cars.number " +
                "FROM mydb.drivers inner join mydb.cars on drivers.car_id=cars.car_id " +
                "where email = '" + email + "'");

        result = converter.convertFirstRow(resultSet);

        Assert.assertEquals(expected, result);
        System.out.println(expected.equals(result));
    }

    @Test
    public void convertRows() throws SQLException, DAOException {
        String expectedObject = "[{status='unban'car='null'carName='renault Logan', carNumber='5647BN-7'currentPoint='0.0,0.0'}\n" +
                "{id=1, name='Антон', surname='Веровчик', email='wolf@gmail.com', tel='+375 44 000 11 23', password='f98204ba6963009734f0398a80f8e44f9d3ef74ebb9c49e5d4f000bd1c102d29'}\n" +
                ", {status='ban'car='null'carName='renault Logan', carNumber='9234CA-7'currentPoint='0.0,0.0'}\n" +
                "{id=2, name='Стас', surname='Антов', email='frog@gmail.com', tel='+375 29 101 12 21', password='0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c'}\n" +
                ", {status='unban'car='null'carName='renault Logan', carNumber='4213TU-7'currentPoint='0.0,0.0'}\n" +
                "{id=3, name='Геннадий', surname='Дровов', email='genadrov@gmail.com', tel='+375 29 909 13 14', password='79f06f8fde333461739f220090a23cb2a79f6d714bee100d0e4b4af249294619'}\n" +
                ", {status='unban'car='null'carName='volkswagen Polo', carNumber='4562SH-7'currentPoint='0.0,0.0'}\n" +
                "{id=4, name='Вова', surname='Геров', email='vovger@gmail.com', tel='+375 44 221 12 00', password='9af15b336e6a9619928537df30b2e6a2376569fcf9d7e773eccede65606529a0'}\n" +
                ", {status='ban'car='null'carName='volkswagen Polo', carNumber='1234GH-7'currentPoint='0.0,0.0'}\n" +
                "{id=5, name='Коля', surname='Ан', email='kolAn@gmail.com', tel='+375 29 454 78 99', password='edee29f882543b956620b26d0ee0e7e950399b1c4222f5de05e06425b4c995e9'}\n" +
                ", {status='unban'car='null'carName='renault Logan', carNumber='4536TR-7'currentPoint='0.0,0.0'}\n" +
                "{id=6, name='Владислав', surname='Асов', email='vagen@gmail.com', tel='+375 29 234 11 12', password='9af15b336e6a9619928537df30b2e6a2376569fcf9d7e773eccede65606529a0'}\n" +
                ", {status='unban'car='null'carName='renault Logan', carNumber='2323TY-7'currentPoint='0.0,0.0'}\n" +
                "{id=15, name='Александр', surname='Шевчек', email='shevchek@gmail.com', tel='+375 29 111 25 26', password='bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a'}\n" +
                ", {status='unban'car='null'carName='renault Logan', carNumber='1231'currentPoint='0.0,0.0'}\n" +
                "{id=20, name='Па', surname='йу', email='wowka@gmail.com', tel='+375 29 666 34 11', password='bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a'}\n" +
                ", {status='ban'car='null'carName='renault Logan', carNumber='1234HR-7'currentPoint='0.0,0.0'}\n" +
                "{id=23, name='Цуй', surname='Цай', email='csai@gmail.com', tel='+375 29 808 24 04', password='bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a'}\n" +
                "]";
        Connection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("" +
                "SELECT driver_id,email,drivers.name as driver_name,surname,tel,status,password,cars.name,cars.number " +
                "FROM mydb.drivers inner join mydb.cars on drivers.car_id=cars.car_id ");

        DriverConverter converter = new DriverConverter();
        List<User> result = converter.convertRows(resultSet);

        Assert.assertEquals(Objects.toString(result), expectedObject);
    }
}