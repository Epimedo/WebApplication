package by.epam.halavin.maintask.dao.impldao;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.user.AdminDAO;
import by.epam.halavin.maintask.dao.user.UserDAO;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AdminDAOTest {
    private static UserDAO userDAO;
    private static User expected = new User();

    static {
        expected.setId(2);
        expected.setName("Боб");
        expected.setSurname("Грошин");
        expected.setTel("+375 44 909 101 11");
        expected.setEmail("admin1@gmail.com");
        expected.setPassword(DigestUtils.sha256Hex("www111"));
    }

    @BeforeClass
    public static void init() {
        userDAO = new AdminDAO();
    }

    @Test
    public void getUser() throws DAOException {
        User user = userDAO.getUser("admin1@gmail.com");

        Assert.assertEquals(user, expected);
    }

    @Test
    public void getUserById() throws DAOException {
        User user = userDAO.getUser(2);

        Assert.assertEquals(user, expected);
    }

    @Test(expected = DAOException.class)
    public void registrationTest() throws DAOException {
        userDAO.registration(expected);
    }

    @Test(expected = DAOException.class)
    public void userUpdateTest() throws DAOException {
        userDAO.userUpdate(expected);
    }

    @Test
    public void getPassword() throws DAOException {
        String expected = "c2c8a89e5590c58bdcc22a87e514d1ba367da950d61ae0e23e36591a93cdd464";
        Assert.assertEquals(expected, userDAO.getPassword("admin1@gmail.com"));
    }
}