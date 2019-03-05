package by.epam.halavin.maintask.dao;

import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.dao.exception.DAOException;
import org.junit.Test;


public class OrderManagerTest {

    @Test
    public void saveOrder() throws DAOException {
        OrderManager orderManager = new OrderManager();
        Order order = new Order();

        Passenger passenger = new Passenger();
        passenger.setId(3);
        Driver driver = new Driver();
        driver.setId(1);

        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDAte = new java.sql.Date(date.getTime());

        order.setPassenger(passenger);
        order.setDriver(driver);
        order.setDate(date);
        order.setCost(12);
        order.setArrivalPoint("улица Крсная 20");
        order.setDeparturePoint("улица пр-т Независимости 112");
        orderManager.saveOrder(order);
    }
}