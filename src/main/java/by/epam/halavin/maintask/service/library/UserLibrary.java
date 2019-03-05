package by.epam.halavin.maintask.service.library;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public abstract class UserLibrary {

    public abstract List<User> getUserIndexOf(int fr, int ls) throws ServiceException;

    public abstract void editUser(User user) throws ServiceException;

    public abstract int userCount() throws ServiceException;

    public abstract User findUser(int id) throws ServiceException;

    public List<Integer> getUserBlocks() throws ServiceException {
        List<Integer> blocks = new ArrayList<>();
        int size = 0;

        try {
            size = userCount();
            for (int i = 1; i <= Math.ceil(size * 1. / 5); i++) {
                blocks.add(i);
            }
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }

        return blocks;
    }
}
