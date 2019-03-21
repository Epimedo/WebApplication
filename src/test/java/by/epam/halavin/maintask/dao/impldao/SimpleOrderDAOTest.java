package by.epam.halavin.maintask.dao.impldao;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.order.OrderDAO;
import by.epam.halavin.maintask.dao.order.SimpleOrderDAO;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SimpleOrderDAOTest {
    private static OrderDAO orderDAO;
    private static Passenger passenger;
    private static Driver driver = new Driver();
    private static Order expected = new Order();


    @BeforeClass
    public static void init() throws ParseException {
        orderDAO = new SimpleOrderDAO();
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
        car.setDate(simpleDateFormat.parse("2020-08-11"));
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
    public void getUserOrder() throws DAOException, ParseException {
        Date date = expected.getDate();
        int id = 1;

        Order order = orderDAO.getUserOrder(id, date);

        Assert.assertEquals(order, expected);
    }

    @Test
    public void getUserOrderCount() throws DAOException {
        int expectedCount = 13;
        Assert.assertEquals(expectedCount, orderDAO.getUserOrderCount(1));
    }


    @Test
    public void getUserOrdersIndexOf() throws DAOException {
        String expectedOrders = "[Order{orderId=1, passenger={id=3, name='Антон', surname='Филин', " +
                "email='antonfil@gmail.com', tel='+375 44 333 22 11'," +
                " password='79f06f8fde333461739f220090a23cb2a79f6d714bee100d0e4b4af249294619'}" +
                "{status='ban', discount=1.0, bonus='12.0'} \n" +
                ", driver={status='unban'car='Car{id=1, number='5647BN-7', name='renault Logan'," +
                " date=Tue Aug 11 00:00:00 MSK 2020}'carName='renault Logan', carNumber='5647BN-7'currentPoint='0.0,0.0'}\n" +
                "{id=1, name='Антон', surname='Веровчик', email='wolf@gmail.com', tel='+375 44 000 11 23'," +
                " password='f98204ba6963009734f0398a80f8e44f9d3ef74ebb9c49e5d4f000bd1c102d29'}\n" +
                ", date=2019-01-21, arrivalPoint='улица Крсная 20', departurePoint='улица пр-т Независимости 112', cost=12.0}]";
        Assert.assertEquals(orderDAO.getUserOrdersIndexOf(3, 1, 4).toString(), expectedOrders);
    }
}