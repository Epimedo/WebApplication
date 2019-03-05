package by.epam.halavin.maintask.service.bridge;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.dao.repository.DispatcherRepository;
import by.epam.halavin.maintask.service.order.DefaultOrderDispatcher;

public class SimpleDispatcherBridge implements DispatcherBridge {

    public DefaultOrderDispatcher getOrderDispatcher(Passenger passenger) {
        return DispatcherRepository.getInstance().getOrderDispatcher(passenger);
    }

    public void addOrderDispatcher(DefaultOrderDispatcher orderDispatcher) {
        DispatcherRepository.getInstance().addOrderDispatcher(orderDispatcher);
    }

    public void removeOrderDispatcher(Passenger passenger) {
        DispatcherRepository.getInstance().removeOrderDispatcher(passenger);
    }

    public DefaultOrderDispatcher driverCheck(Driver driver) {
        return DispatcherRepository.getInstance().driverCheck(driver);
    }
}
