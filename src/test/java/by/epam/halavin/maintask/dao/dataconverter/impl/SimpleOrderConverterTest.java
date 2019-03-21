package by.epam.halavin.maintask.dao.dataconverter.impl;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.dataconverter.OrderConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SimpleOrderConverterTest {
    private static Passenger passenger;
    private static Driver driver = new Driver();
    private static Order expected = new Order();

    private String selectOrder = "SELECT * FROM mydb.orders inner join mydb.users on orders.user_id = users.user_id" +
            " inner join mydb.drivers" +
            " on orders.driver_id = drivers.driver_id where orders.user_id = ? and date = ? ";

    @BeforeClass
    public static void initOrder() throws ParseException {
        passenger = new Passenger();
        passenger.setId(1);
        passenger.setName("Федор");
        passenger.setSurname("Федоров");
        passenger.setEmail("wowka@gmail.com");
        passenger.setTel("+375 29 444 77 99");
        passenger.setStatus("unban");
        passenger.setPassword(DigestUtils.sha256Hex("1111"));
        passenger.setBonus(9.65);
        passenger.setDiscount(1.1);

        driver.setId(1);
        driver.setEmail("wolf@gmail.com");
        driver.setName("Антон");
        driver.setSurname("Веровчик");
        driver.setStatus("unban");
        driver.setTel("+375 44 000 11 23");
        driver.setPassword(DigestUtils.sha256Hex("qqqq"));
        driver.setCarName("renault Logan");
        driver.setCarNumber("5647BN-7");
        Car car = new Car();
        car.setId(1);
        car.setNumber("5647BN-7");
        car.setName("renault Logan");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        java.sql.Date date = new java.sql.Date(simpleDateFormat.parse("2020-08-11").getTime());
        car.setDate(date);
        driver.setCar(car);

        expected.setOrderId(2);
        expected.setCost(6.94);
        expected.setDriver(driver);
        expected.setPassenger(passenger);
        expected.setArrivalPoint("улица Аранская, 13");
        expected.setDeparturePoint("улица Красная, 20");
        expected.setDate(new java.sql.Date(simpleDateFormat.parse("2019-02-25").getTime()));
    }

    @Test
    public void convertFirstRow() throws DAOException, ParseException, SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        OrderConverter converter = new SimpleOrderConverter();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Date date = simpleDateFormat.parse("2019-02-25");
        PreparedStatement statement = connection.prepareStatement(selectOrder);
        statement.setInt(1, 1);
        statement.setString(2, format.format(expected.getDate()));
        ResultSet resultSet = statement.executeQuery();

        Order order = converter.convertFirstRow(resultSet);

        Assert.assertEquals(order, expected);
    }

    @Test
    public void convertRows() throws DAOException, SQLException {
        String strExpeted = "[Order{orderId=1, passenger={id=3, name='Антон', surname='Филин', email='antonfil@gmail.com', tel='+375 44 333 22 11', password='79f06f8fde333461739f220090a23cb2a79f6d714bee100d0e4b4af249294619'}\n" +
                "{status='ban', discount=1.0, bonus='12.0'} \n" +
                ", driver={status='unban'car='Car{id=1, number='5647BN-7', name='renault Logan', date=Tue Aug 11 00:00:00 MSK 2020}'carName='renault Logan', carNumber='5647BN-7'currentPoint='0.0,0.0'}\n" +
                "{id=1, name='Антон', surname='Веровчик', email='wolf@gmail.com', tel='+375 44 000 11 23', password='f98204ba6963009734f0398a80f8e44f9d3ef74ebb9c49e5d4f000bd1c102d29'}\n" +
                ", date=2019-01-21, arrivalPoint='улица Крсная 20', departurePoint='улица пр-т Независимости 112', cost=12.0}, Order{orderId=2, passenger={id=1, name='Федор', surname='Федоров', email='wowka@gmail.com', tel='+375 29 444 77 99', password='0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c'}\n" +
                "{status='unban', discount=1.1, bonus='9.65'} \n" +
                ", driver={status='unban'car='Car{id=1, number='5647BN-7', name='renault Logan', date=Tue Aug 11 00:00:00 MSK 2020}'carName='renault Logan', carNumber='5647BN-7'currentPoint='0.0,0.0'}\n" +
                "{id=1, name='Антон', surname='Веровчик', email='wolf@gmail.com', tel='+375 44 000 11 23', password='f98204ba6963009734f0398a80f8e44f9d3ef74ebb9c49e5d4f000bd1c102d29'}\n" +
                ", date=2019-02-25, arrivalPoint='улица Аранская, 13', departurePoint='улица Красная, 20', cost=6.94}, Order{orderId=3, passenger={id=1, name='Федор', surname='Федоров', email='wowka@gmail.com', tel='+375 29 444 77 99', password='0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c'}\n" +
                "{status='unban', discount=1.1, bonus='9.65'} \n" +
                ", driver={status='unban'car='Car{id=1, number='5647BN-7', name='renault Logan', date=Tue Aug 11 00:00:00 MSK 2020}'carName='renault Logan', carNumber='5647BN-7'currentPoint='0.0,0.0'}\n" +
                "{id=1, name='Антон', surname='Веровчик', email='wolf@gmail.com', tel='+375 44 000 11 23', password='f98204ba6963009734f0398a80f8e44f9d3ef74ebb9c49e5d4f000bd1c102d29'}\n" +
                ", date=2019-02-26, arrivalPoint='улица Аранская, 13', departurePoint='улица Красная, 20', cost=6.94}, Order{orderId=4, passenger={id=1, name='Федор', surname='Федоров', email='wowka@gmail.com', tel='+375 29 444 77 99', password='0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c'}\n" +
                "{status='unban', discount=1.1, bonus='9.65'} \n" +
                ", driver={status='unban'car='Car{id=1, number='5647BN-7', name='renault Logan', date=Tue Aug 11 00:00:00 MSK 2020}'carName='renault Logan', carNumber='5647BN-7'currentPoint='0.0,0.0'}\n" +
                "{id=1, name='Антон', surname='Веровчик', email='wolf@gmail.com', tel='+375 44 000 11 23', password='f98204ba6963009734f0398a80f8e44f9d3ef74ebb9c49e5d4f000bd1c102d29'}\n" +
                ", date=2019-02-26, arrivalPoint='улица Аранская, 13', departurePoint='улица Красная, 20', cost=6.94}, Order{orderId=5, passenger={id=1, name='Федор', surname='Федоров', email='wowka@gmail.com', tel='+375 29 444 77 99', password='0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c'}\n" +
                "{status='unban', discount=1.1, bonus='9.65'} \n" +
                ", driver={status='unban'car='Car{id=1, number='5647BN-7', name='renault Logan', date=Tue Aug 11 00:00:00 MSK 2020}'carName='renault Logan', carNumber='5647BN-7'currentPoint='0.0,0.0'}\n" +
                "{id=1, name='Антон', surname='Веровчик', email='wolf@gmail.com', tel='+375 44 000 11 23', password='f98204ba6963009734f0398a80f8e44f9d3ef74ebb9c49e5d4f000bd1c102d29'}\n" +
                ", date=2019-03-04, arrivalPoint='улица Аранская, 13', departurePoint='улица Красная, 20', cost=6.94}, Order{orderId=6, passenger={id=1, name='Федор', surname='Федоров', email='wowka@gmail.com', tel='+375 29 444 77 99', password='0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c'}\n" +
                "{status='unban', discount=1.1, bonus='9.65'} \n" +
                ", driver={status='unban'car='Car{id=1, number='5647BN-7', name='renault Logan', date=Tue Aug 11 00:00:00 MSK 2020}'carName='renault Logan', carNumber='5647BN-7'currentPoint='0.0,0.0'}\n" +
                "{id=1, name='Антон', surname='Веровчик', email='wolf@gmail.com', tel='+375 44 000 11 23', password='f98204ba6963009734f0398a80f8e44f9d3ef74ebb9c49e5d4f000bd1c102d29'}\n" +
                ", date=2019-03-04, arrivalPoint='улица Аранская, 13', departurePoint='улица Красная, 20', cost=6.94}, Order{orderId=7, passenger={id=1, name='Федор', surname='Федоров', email='wowka@gmail.com', tel='+375 29 444 77 99', password='0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c'}\n" +
                "{status='unban', discount=1.1, bonus='9.65'} \n" +
                ", driver={status='unban'car='Car{id=1, number='5647BN-7', name='renault Logan', date=Tue Aug 11 00:00:00 MSK 2020}'carName='renault Logan', carNumber='5647BN-7'currentPoint='0.0,0.0'}\n" +
                "{id=1, name='Антон', surname='Веровчик', email='wolf@gmail.com', tel='+375 44 000 11 23', password='f98204ba6963009734f0398a80f8e44f9d3ef74ebb9c49e5d4f000bd1c102d29'}\n" +
                ", date=2019-03-04, arrivalPoint='улица Аранская, 13', departurePoint='улица Красная, 20', cost=6.94}, Order{orderId=8, passenger={id=1, name='Федор', surname='Федоров', email='wowka@gmail.com', tel='+375 29 444 77 99', password='0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c'}\n" +
                "{status='unban', discount=1.1, bonus='9.65'} \n" +
                ", driver={status='unban'car='Car{id=1, number='5647BN-7', name='renault Logan', date=Tue Aug 11 00:00:00 MSK 2020}'carName='renault Logan', carNumber='5647BN-7'currentPoint='0.0,0.0'}\n" +
                "{id=1, name='Антон', surname='Веровчик', email='wolf@gmail.com', tel='+375 44 000 11 23', password='f98204ba6963009734f0398a80f8e44f9d3ef74ebb9c49e5d4f000bd1c102d29'}\n" +
                ", date=2019-03-04, arrivalPoint='улица Аранская, 13', departurePoint='улица Красная, 20', cost=6.94}, Order{orderId=9, passenger={id=1, name='Федор', surname='Федоров', email='wowka@gmail.com', tel='+375 29 444 77 99', password='0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c'}\n" +
                "{status='unban', discount=1.1, bonus='9.65'} \n" +
                ", driver={status='unban'car='Car{id=1, number='5647BN-7', name='renault Logan', date=Tue Aug 11 00:00:00 MSK 2020}'carName='renault Logan', carNumber='5647BN-7'currentPoint='0.0,0.0'}\n" +
                "{id=1, name='Антон', surname='Веровчик', email='wolf@gmail.com', tel='+375 44 000 11 23', password='f98204ba6963009734f0398a80f8e44f9d3ef74ebb9c49e5d4f000bd1c102d29'}\n" +
                ", date=2019-03-04, arrivalPoint='улица Аранская, 13', departurePoint='улица Красная, 20', cost=6.94}, Order{orderId=10, passenger={id=1, name='Федор', surname='Федоров', email='wowka@gmail.com', tel='+375 29 444 77 99', password='0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c'}\n" +
                "{status='unban', discount=1.1, bonus='9.65'} \n" +
                ", driver={status='unban'car='Car{id=1, number='5647BN-7', name='renault Logan', date=Tue Aug 11 00:00:00 MSK 2020}'carName='renault Logan', carNumber='5647BN-7'currentPoint='0.0,0.0'}\n" +
                "{id=1, name='Антон', surname='Веровчик', email='wolf@gmail.com', tel='+375 44 000 11 23', password='f98204ba6963009734f0398a80f8e44f9d3ef74ebb9c49e5d4f000bd1c102d29'}\n" +
                ", date=2019-03-06, arrivalPoint='улица Аранская, 13', departurePoint='улица Красная, 20', cost=6.94}, Order{orderId=11, passenger={id=1, name='Федор', surname='Федоров', email='wowka@gmail.com', tel='+375 29 444 77 99', password='0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c'}\n" +
                "{status='unban', discount=1.1, bonus='9.65'} \n" +
                ", driver={status='unban'car='Car{id=1, number='5647BN-7', name='renault Logan', date=Tue Aug 11 00:00:00 MSK 2020}'carName='renault Logan', carNumber='5647BN-7'currentPoint='0.0,0.0'}\n" +
                "{id=1, name='Антон', surname='Веровчик', email='wolf@gmail.com', tel='+375 44 000 11 23', password='f98204ba6963009734f0398a80f8e44f9d3ef74ebb9c49e5d4f000bd1c102d29'}\n" +
                ", date=2019-03-10, arrivalPoint='аранская 13', departurePoint=' ангарская 4', cost=14.36}, Order{orderId=12, passenger={id=1, name='Федор', surname='Федоров', email='wowka@gmail.com', tel='+375 29 444 77 99', password='0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c'}\n" +
                "{status='unban', discount=1.1, bonus='9.65'} \n" +
                ", driver={status='unban'car='Car{id=1, number='5647BN-7', name='renault Logan', date=Tue Aug 11 00:00:00 MSK 2020}'carName='renault Logan', carNumber='5647BN-7'currentPoint='0.0,0.0'}\n" +
                "{id=1, name='Антон', surname='Веровчик', email='wolf@gmail.com', tel='+375 44 000 11 23', password='f98204ba6963009734f0398a80f8e44f9d3ef74ebb9c49e5d4f000bd1c102d29'}\n" +
                ", date=2019-03-10, arrivalPoint=' проспект победителей 129', departurePoint=' филимонова 23', cost=20.46}, Order{orderId=13, passenger={id=1, name='Федор', surname='Федоров', email='wowka@gmail.com', tel='+375 29 444 77 99', password='0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c'}\n" +
                "{status='unban', discount=1.1, bonus='9.65'} \n" +
                ", driver={status='unban'car='Car{id=1, number='5647BN-7', name='renault Logan', date=Tue Aug 11 00:00:00 MSK 2020}'carName='renault Logan', carNumber='5647BN-7'currentPoint='0.0,0.0'}\n" +
                "{id=1, name='Антон', surname='Веровчик', email='wolf@gmail.com', tel='+375 44 000 11 23', password='f98204ba6963009734f0398a80f8e44f9d3ef74ebb9c49e5d4f000bd1c102d29'}\n" +
                ", date=2019-03-10, arrivalPoint=' проспект победителей 129', departurePoint='филимонова 55', cost=20.8}, Order{orderId=14, passenger={id=1, name='Федор', surname='Федоров', email='wowka@gmail.com', tel='+375 29 444 77 99', password='0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c'}\n" +
                "{status='unban', discount=1.1, bonus='9.65'} \n" +
                ", driver={status='unban'car='Car{id=1, number='5647BN-7', name='renault Logan', date=Tue Aug 11 00:00:00 MSK 2020}'carName='renault Logan', carNumber='5647BN-7'currentPoint='0.0,0.0'}\n" +
                "{id=1, name='Антон', surname='Веровчик', email='wolf@gmail.com', tel='+375 44 000 11 23', password='f98204ba6963009734f0398a80f8e44f9d3ef74ebb9c49e5d4f000bd1c102d29'}\n" +
                ", date=2019-03-10, arrivalPoint=' аранская 18', departurePoint=' ангарская 4', cost=14.41}]";
        Connection connection = ConnectionPool.getInstance().getConnection();
        OrderConverter converter = new SimpleOrderConverter();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM mydb.orders inner join mydb.users" +
                " on orders.user_id = users.user_id" +
                " inner join mydb.drivers" +
                " on orders.driver_id = drivers.driver_id");

        Assert.assertEquals(strExpeted, converter.convertRows(statement.executeQuery()).toString());
    }
}