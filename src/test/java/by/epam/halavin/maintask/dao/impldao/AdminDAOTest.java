package by.epam.halavin.maintask.dao.impldao;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.user.UserDAO;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.user.AdminDAO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AdminDAOTest {
    private static UserDAO userDAO;

    @BeforeClass
    public static void inti() {
        userDAO = new AdminDAO();
    }

    @Test
    public void getUser() throws DAOException {
        User user = userDAO.getUser("admin1@gmail.com");

        System.out.println(user);
    }

    @Test
    public void getUserById() throws DAOException {
        User user = userDAO.getUser(2);

        System.out.println(user);
    }

    @Test
    public void getPassword() throws DAOException {
        String expected = "c2c8a89e5590c58bdcc22a87e514d1ba367da950d61ae0e23e36591a93cdd464";

        Assert.assertEquals(expected, userDAO.getPassword("admin1@gmail.com"));
    }
}