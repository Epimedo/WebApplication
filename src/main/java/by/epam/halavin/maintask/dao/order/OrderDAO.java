package by.epam.halavin.maintask.dao.order;

import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.util.Date;
import java.util.List;

/**
 * Presents methods for interacting with the order database
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public interface OrderDAO {

    /**
     * Function to save order
     *
     * @param order
     * @return true/false(success/failed saving)
     * @throws DAOException
     */
    boolean saveOrder(Order order) throws DAOException;

    /**
     * Function to get car with appropriate id and date
     *
     * @param id
     * @param date
     * @return order
     * @throws DAOException
     */
    Order getUserOrder(int id, Date date) throws DAOException;

    /**
     * Function to get number of users's orders
     *
     * @param id
     * @return orders number
     * @throws DAOException
     */
    int getUserOrderCount(int id) throws DAOException;

    /**
     * Function to get user's orders from first position to last position
     *
     * @param id
     * @param firstPo
     * @param lastPos
     * @return
     * @throws DAOException
     */
    List<Order> getUserOrdersIndexOf(int id, int firstPo, int lastPos) throws DAOException;

}
