package by.epam.halavin.maintask.dao.order;

import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.util.Date;
import java.util.List;

public interface OrderDAO {

    boolean saveOrder(Order order) throws DAOException;

    Order getUserOrder(int id, Date date) throws DAOException;

    int getUserOrderCount(int id) throws DAOException;

    List<Order> getUserOrdersIndexOf(int id, int fr, int ls) throws DAOException;

}
