package by.epam.halavin.maintask.dao.repository;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.dao.DAOFactory;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.user.UserDAO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DriverRepositoryTest {
    private static Driver driver1;
    private static Driver driver2;

    @BeforeClass
    public static void init() throws DAOException {
        UserDAO userDAO = DAOFactory.getInstance().getDriverDAO();
        driver1 = (Driver) userDAO.getUser(1);
        driver2 = (Driver) userDAO.getUser(2);
        DriverRepository.getInstance().addDriver(driver1);
        DriverRepository.getInstance().addDriver(driver2);
    }

    @Test
    public void removeDriver() {
        int result = 0;
        DriverRepository.getInstance().removeDriver(driver1);
        result = DriverRepository.getInstance().getFreeDrivers().size();
        DriverRepository.getInstance().addDriver(driver1);

        Assert.assertEquals(result, 1);
    }

    @Test
    public void freeDriver() {
        int result = 0;
        DriverRepository.getInstance().takeDriver(driver1);
        DriverRepository.getInstance().freeDriver(driver1);
        result = DriverRepository.getInstance().getFreeDrivers().size();

        Assert.assertEquals(result, 2);
    }

    @Test
    public void takeDriver() {
        int result = 0;
        DriverRepository.getInstance().takeDriver(driver1);
        result = DriverRepository.getInstance().getFreeDrivers().size();
        DriverRepository.getInstance().freeDriver(driver1);

        Assert.assertEquals(result, 1);
    }

    @Test
    public void getFreeDrivers() {
        int result = 0;
        result = DriverRepository.getInstance().getFreeDrivers().size();

        Assert.assertEquals(result, 2);
    }
}