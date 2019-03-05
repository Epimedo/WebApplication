package by.epam.halavin.maintask.service.library;

import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.DAOFactory;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.order.OrderDAO;
import by.epam.halavin.maintask.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

public class SimpleOrderLibrary extends OrderLibrary {

    @Override
    public List<Order> getUserOrdersIndexOf(int id, int fr, int ls) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        OrderDAO orderDAO = factory.getSimpleOrderDAO();

        List<Order> list = null;

        try {
            list = orderDAO.getUserOrdersIndexOf(id, fr, ls);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return list;
    }

    @Override
    public int orderCount(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        OrderDAO orderDAO = factory.getSimpleOrderDAO();
        int result = 0;

        try {
            result = orderDAO.getUserOrderCount(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public User findOrder(int id, Date date) throws ServiceException {
        return null;
    }
}
