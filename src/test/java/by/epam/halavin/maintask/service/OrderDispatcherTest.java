package by.epam.halavin.maintask.service;

import by.epam.halavin.maintask.bean.geocoding.Point;
import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.dao.repository.DriverRepository;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.util.GeoGenerator;
import org.junit.Test;

import java.util.Date;
import java.util.Objects;

public class OrderDispatcherTest {

    @Test
    public void searchDriver() throws ServiceException {
        for (int i = 0; i < 10; i++) {
            Driver driver = new Driver();
            driver.setId(i);
            DriverRepository.getInstance().addDriver(driver);
        }
        GeoGenerator geoGenerator = new GeoGenerator();
        geoGenerator.run();
        Point point = new Point(0, 0);
        OrderDispatcher orderDispatcher = new OrderDispatcher();

        System.out.println(orderDispatcher.searchDriver(point));
        System.out.println(Objects.toString(DriverRepository.getInstance().getFreeDrivers()));
    }
}