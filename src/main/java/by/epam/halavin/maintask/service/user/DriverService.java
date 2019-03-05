package by.epam.halavin.maintask.service.user;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.DAOFactory;
import by.epam.halavin.maintask.dao.user.UserDAO;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.service.exception.ServiceException;

public class DriverService implements UserService {

    @Override
    public User signIn(String email, String password) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getDriverDAO();
        User user = null;

        try {
            user = userDAO.getUser(email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public boolean registration(User user) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getDriverDAO();

        try {
            userDAO.registration(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return true;
    }
}
