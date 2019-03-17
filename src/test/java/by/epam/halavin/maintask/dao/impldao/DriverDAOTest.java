package by.epam.halavin.maintask.dao.impldao;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.user.DriverDAO;
import by.epam.halavin.maintask.dao.user.UserDAO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DriverDAOTest {
    private static UserDAO userDAO;
    private static Driver driver = new Driver();

    @BeforeClass
    public static void init() throws ParseException {
        userDAO = new DriverDAO();
        driver.setId(1);
        driver.setStatus("unban");
        driver.setName("Антон");
        driver.setSurname("Веровчик");
        driver.setEmail("wolf@gmail.com");
        driver.setTel("+375 44 000 11 23");
        driver.setPassword("f98204ba6963009734f0398a80f8e44f9d3ef74ebb9c49e5d4f000bd1c102d29");
        driver.setCarNumber("5647BN-7");
        driver.setCarName("renault Logan");
        Car car = new Car();
        car.setId(1);
        car.setName("renault Logan");
        car.setNumber("5647BN-7");
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd", DateFormatSymbols.getInstance(Locale.ENGLISH));
        Date date = format.parse("2020-08-11");
        car.setDate(date);
        System.out.println(date);
        driver.setCar(car);
    }

    @Test
    public void getUser() throws DAOException, ParseException {
        User user = userDAO.getUser("wolf@gmail.com");
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd", DateFormatSymbols.getInstance(Locale.ENGLISH));
        Date date = format.parse(((Driver) user).getCar().getDate().toString());
        ((Driver) user).getCar().setDate(date);
        Assert.assertEquals(user, driver);
    }

    @Test
    public void getUserById() throws DAOException {
        User user = userDAO.getUser(1);

        System.out.println(user);
    }

    @Test
    public void registration() throws DAOException, ParseException {
        Car car = new Car();
        car.setDate(new Date());
        car.setName("log");
        car.setNumber("HHHHHHH");
        Driver driver = new Driver();
        driver.setCar(car);
        driver.setName("car");
        driver.setSurname("car");
        driver.setStatus("ban");
        driver.setTel("2132131");
        driver.setPassword("11111");
        driver.setEmail("dawdqwwdq");


        userDAO.registration(driver);
    }

    @Test
    public void userUpdate() throws DAOException {
        Driver user = (Driver) userDAO.getUser("wolf@gmail.com");
        user.setStatus("ban");

        userDAO.userUpdate(user);
        user = (Driver) userDAO.getUser("wolf@gmail.com");

        Assert.assertEquals("ban", user.getStatus());
    }

    @Test
    public void getPassword() throws DAOException {
        String expected = "f98204ba6963009734f0398a80f8e44f9d3ef74ebb9c49e5d4f000bd1c102d29";
        User user = userDAO.getUser("wolf@gmail.com");

        Assert.assertEquals(expected, user.getPassword());
    }

    @Test
    public void getUsersIndexOf() throws DAOException {
        List<User> users = userDAO.getUsersIndexOf(1, 5);

        System.out.println(users);
    }

    @Test
    public void getUsersCount() throws DAOException {
        int expected = 9;

        Assert.assertEquals(expected, userDAO.getUsersCount());
    }
}