package by.epam.halavin.maintask.service.bridge;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.service.order.DefaultOrderDispatcher;
import by.epam.halavin.maintask.service.order.OrderDispatcher;

/**
 * Bridge is used to control
 */
public interface DispatcherBridge {

    /**
     * Function to get order dispatcher by passenger
     *
     * @param passenger
     * @return
     */
    DefaultOrderDispatcher getOrderDispatcher(Passenger passenger);

    /**
     * Function to add order dispatcher
     *
     * @param orderDispatcher
     */
    void addOrderDispatcher(OrderDispatcher orderDispatcher);

    /**
     * Function to remove order dispatcher
     *
     * @param passenger
     */
    void removeOrderDispatcher(Passenger passenger);

    /**
     * Function to get order dispatcher by driver
     *
     * @param driver
     * @return
     */
    DefaultOrderDispatcher driverCheck(Driver driver);
}
