package by.epam.halavin.maintask.service.order;

import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.service.exception.ServiceException;

/**
 * Dispatcher controls order's state and manipulate with it
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public interface OrderDispatcher {
    /**
     * Function to get order
     *
     * @return order
     */
    Order getOrder();

    /**
     * Function to get order status
     *
     * @return order status
     */
    String getOrderStatus();

    /**
     * Function to get driver status
     *
     * @return driver status
     */
    String getDriverStatus();

    /**
     * Method makes new order
     *
     * @param passenger
     * @param currentPos
     * @param nextPos
     * @throws ServiceException
     */
    void makeOrder(Passenger passenger, String currentPos, String nextPos) throws ServiceException;

    /**
     * Method makes order accepted
     */
    void acceptOrder();

    /**
     * Methods makes order declined
     *
     * @return
     */
    Driver declineOrder();

    /**
     * Order's initialization
     *
     * @param passenger
     * @param currentPos
     * @param nextPos
     * @throws ServiceException
     */
    void initOrder(Passenger passenger, String currentPos, String nextPos) throws ServiceException;

    /**
     * Makes order reseted
     */
    void resetOrder();

    /**
     * Makes order paid
     *
     * @throws ServiceException
     */
    void orderPayment() throws ServiceException;


}
