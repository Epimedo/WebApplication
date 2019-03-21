package by.epam.halavin.maintask.dao.dataconverter;

import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.sql.ResultSet;
import java.util.List;

/**
 * Defines methods for order converter
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public interface OrderConverter {

    /**
     * Function convert first row in result set into order object and return it
     *
     * @param resultSet
     * @return order
     * @throws DAOException
     */
    Order convertFirstRow(ResultSet resultSet) throws DAOException;

    /**
     * Function convert all rows in result into order objects and return order list with them
     *
     * @param resultSet
     * @return orders list
     * @throws DAOException
     */
    List<Order> convertRows(ResultSet resultSet) throws DAOException;
}
