package by.epam.halavin.maintask.service.library;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.DAOFactory;
import by.epam.halavin.maintask.dao.user.UserDAO;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.service.exception.ServiceException;

import java.util.List;

public class DriverLibrary extends UserLibrary {

    @Override
    public List<User> getUserIndexOf(int fr, int ls) throws ServiceException {
        if (fr <= 0 || ls <= 0) {
            throw new ServiceException("Incorrect list indexes");
        }

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getDriverDAO();
        List<User> users = null;

        try {
            users = userDAO.getUsersIndexOf(fr, ls);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return users;
    }

    @Override
    public void editUser(User user) throws ServiceException {
        if (user.getClass() != Driver.class){
            throw new ServiceException("Incorrect user's type");
        }

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getDriverDAO();

        try {
            userDAO.userUpdate(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int userCount() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getDriverDAO();
        int result = 0;

        try {
            result = userDAO.getUsersCount();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public User findUser(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getDriverDAO();
        User user = null;

        try {
            user = userDAO.getUser(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return user;
    }
}
