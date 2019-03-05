package by.epam.halavin.maintask.dao.impldao;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.user.UserDAO;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.user.DriverDAO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class DriverDAOTest {
    private static UserDAO userDAO;

    @BeforeClass
    public static void init() {
        userDAO = new DriverDAO();
    }

    @Test
    public void getUser() throws DAOException {
        User user = userDAO.getUser("wolf@gmail.com");

        System.out.println(user);
    }

    @Test
    public void getUserById() throws DAOException {
        User user = userDAO.getUser(1);

        System.out.println(user);
    }

    @Test
    public void registration() throws DAOException{
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
        int expected = 7;

        Assert.assertEquals(expected, userDAO.getUsersCount());
    }
}