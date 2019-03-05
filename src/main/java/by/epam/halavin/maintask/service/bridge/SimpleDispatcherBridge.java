package by.epam.halavin.maintask.service.bridge;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.dao.repository.DispatcherRepository;
import by.epam.halavin.maintask.service.order.DefaultOrderDispatcher;
import by.epam.halavin.maintask.service.order.OrderDispatcher;

public class SimpleDispatcherBridge implements DispatcherBridge {

    public DefaultOrderDispatcher getOrderDispatcher(Passenger passenger) {
        return DispatcherRepository.getInstance().getOrderDispatcher(passenger);
    }

    public void addOrderDispatcher(OrderDispatcher orderDispatcher) {
        DispatcherRepository.getInstance().addOrderDispatcher((DefaultOrderDispatcher) orderDispatcher);
    }

    public void removeOrderDispatcher(Passenger passenger) {
        DispatcherRepository.getInstance().removeOrderDispatcher(passenger);
    }

    public DefaultOrderDispatcher driverCheck(Driver driver) {
        return DispatcherRepository.getInstance().driverCheck(driver);
    }
}
