package by.epam.halavin.maintask.dao.edited.impl;

import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.dao.exception.DAOException;
import org.junit.Test;

public class PassengerBonusUpdateTest {

    @Test
    public void edit() throws DAOException {
        Order order = new Order();
        order.setOrderId(1);
        Passenger passenger = new Passenger();
        passenger.setId(3);
        order.setPassenger(passenger);
        order.setCost(7);

//        PassengerBonusUpdate passengerBonusUpdate = new PassengerBonusUpdate();
        //passengerBonusUpdate.edit(order);

    }
}