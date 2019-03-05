package by.epam.halavin.maintask.service.order;

import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.service.exception.ServiceException;

public interface OrderDispatcher {
    Order getOrder();

    String getOrderStatus();

    String getDriverStatus();

    void makeOrder(Passenger passenger, String currentPos, String nextPos) throws ServiceException;

    void acceptOrder();

    Driver declineOrder();

    void initOrder(Passenger passenger, String currentPos, String nextPos) throws ServiceException;

    void resetOrder();

    void orderPayment() throws ServiceException;


}
