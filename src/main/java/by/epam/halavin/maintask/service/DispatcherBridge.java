package by.epam.halavin.maintask.service;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.dao.repository.DispatcherRepository;

public class DispatcherBridge {

    public OrderDispatcher getOrderDispatcher(Passenger passenger) {
        return DispatcherRepository.getInstance().getOrderDispatcher(passenger);
    }

    public void addOrderDispatcher(OrderDispatcher orderDispatcher) {
        DispatcherRepository.getInstance().addOrderDispatcher(orderDispatcher);
    }

    public void removerOrderDispatcher(Passenger passenger) {
        DispatcherRepository.getInstance().removeOrderDispatcher(passenger);
    }

    public OrderDispatcher driverCheck(Driver driver) {
        return DispatcherRepository.getInstance().driverCheck(driver);
    }
}
