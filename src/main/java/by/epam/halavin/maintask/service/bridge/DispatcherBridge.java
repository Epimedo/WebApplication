package by.epam.halavin.maintask.service.bridge;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.service.order.DefaultOrderDispatcher;

public interface DispatcherBridge {

    DefaultOrderDispatcher getOrderDispatcher(Passenger passenger);

    void addOrderDispatcher(DefaultOrderDispatcher orderDispatcher);

    void removeOrderDispatcher(Passenger passenger);

    DefaultOrderDispatcher driverCheck(Driver driver);
}
