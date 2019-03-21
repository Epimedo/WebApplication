package by.epam.halavin.maintask.dao.repository;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.dao.DAOFactory;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.user.UserDAO;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.order.DefaultOrderDispatcher;
import by.epam.halavin.maintask.service.order.OrderDispatcher;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DispatcherRepositoryTest {
    private static Passenger passenger;
    private static Driver driver;
    private static DefaultOrderDispatcher expectedOrderDispatcher = new DefaultOrderDispatcher();
    private static String curPos = "ангарская 4";
    private static String nextPos = "красная 20";

    @BeforeClass
    public static void initOrderDispatcher() throws DAOException, ServiceException {
        UserDAO passDAO = DAOFactory.getInstance().getPassDAO();
        UserDAO driverDAO = DAOFactory.getInstance().getDriverDAO();
        passenger = (Passenger) passDAO.getUser(1);
        driver = (Driver) driverDAO.getUser(1);
        DriverRepository.getInstance().addDriver(driver);
        expectedOrderDispatcher.makeOrder(passenger, curPos, nextPos);
        DispatcherRepository.getInstance().addOrderDispatcher(expectedOrderDispatcher);
    }

    @Test
    public void driverCheck() {
        OrderDispatcher orderDispatcher = DispatcherRepository.getInstance().driverCheck(driver);
        Assert.assertEquals(orderDispatcher, expectedOrderDispatcher);
    }

    @Test
    public void getOrderDispatcher() {
        OrderDispatcher orderDispatcher = DispatcherRepository.getInstance().getOrderDispatcher(passenger);
        Assert.assertEquals(orderDispatcher, expectedOrderDispatcher);
    }

    @Test
    public void removeOrderDispatcher() {
        OrderDispatcher result = null;
        DispatcherRepository.getInstance().removeOrderDispatcher(passenger);
        result = DispatcherRepository.getInstance().getOrderDispatcher(passenger);
        DispatcherRepository.getInstance().addOrderDispatcher(expectedOrderDispatcher);

        Assert.assertEquals(result, null);
    }

}