package by.epam.halavin.maintask.service.library;

import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class OrderLibrary {

    public abstract List<Order> getUserOrdersIndexOf(int id,int fr, int ls) throws ServiceException;

    public abstract int orderCount(int id) throws ServiceException;

    public abstract User findOrder(int id, Date date) throws ServiceException;

    public List<Integer> getUserBlocks(int id) throws ServiceException {
        List<Integer> blocks = new ArrayList<>();
        int size = 0;

        try {
            size = orderCount(id);
            for (int i = 1; i <= Math.ceil(size * 1. / 5); i++) {
                blocks.add(i);
            }
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }

        return blocks;
    }
}
