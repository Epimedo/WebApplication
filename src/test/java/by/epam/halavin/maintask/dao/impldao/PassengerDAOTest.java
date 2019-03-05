package by.epam.halavin.maintask.dao.impldao;

import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.user.UserDAO;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.user.PassengerDAO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class PassengerDAOTest {
    private static UserDAO userDAO;
    private static User testUser;

    @BeforeClass
    public static void init() {
        userDAO = new PassengerDAO();

        testUser = new Passenger();
        testUser.setId(5);
        testUser.setName("Павел");
        testUser.setSurname("Савчик");
        testUser.setEmail("tut@gmail.com");
        testUser.setTel("+375 29 666 34 11");
        testUser.setPassword("318aee3fed8c9d040d35a7fc1fa776fb31303833aa2de885354ddf3d44d8fb69");
        ((Passenger) testUser).setStatus("unban");
    }

    @Test
    public void getUser() throws DAOException {
        User user = userDAO.getUser("tut@gmail.com");

        Assert.assertEquals(testUser, user);
    }

    @Test
    public  void getUserById() throws DAOException{
        User user = userDAO.getUser(5);

        Assert.assertEquals(testUser, user);
    }

    @Test
    public void registration() throws DAOException {

    }

    @Test
    public void userUpdate() throws DAOException {
        Passenger user = (Passenger) userDAO.getUser("wowka@gmail.com");

        user.setStatus("unban");
        user.setBonus(1.1);
        user.setDiscount(1.1);

        userDAO.userUpdate(user);

    }

    @Test
    public void getPassword() throws DAOException {
        String password = userDAO.getPassword("tut@gmail.com");

        Assert.assertEquals(testUser.getPassword(), password);
    }

    @Test
    public void getUsersIndexOf() throws DAOException {
        List<User> users = userDAO.getUsersIndexOf(1, 5);

        System.out.println(users);
    }

    @Test
    public void getUsersCount() throws DAOException{
        int count = 12;

        Assert.assertEquals(count,userDAO.getUsersCount());
    }
}