package by.epam.halavin.maintask.service.validate.impl;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.dao.DAOFactory;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.user.UserDAO;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.validate.Validator;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;

public class DriverValidator implements Validator {

    public boolean isEndCheckaupEnd(User user) throws ServiceException {
        boolean bool = false;
        Date date = new Date();
        Car car = ((Driver) user).getCar();

        if (date.compareTo(car.getDate()) >= 0) {
            bool = true;
        }
        return bool;
    }

    @Override
    public boolean isBan(User user) throws ServiceException {
        boolean bool = false;
        Driver driver = (Driver) user;

        if (driver.getStatus().equals(Attributes.BAN.getName())) {
            bool = true;
        }

        return bool;
    }

    @Override
    public boolean isCorrectEmail(String email) throws ServiceException {
        boolean bool = true;
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getDriverDAO();

        try {
            User user = userDAO.getUser(email);
            if (user == null) {
                bool = false;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return bool;
    }

    @Override
    public boolean isCorrectPassword(String email, String password) throws ServiceException {
        boolean bool = true;
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getDriverDAO();

        try {
            User user = userDAO.getUser(email);
            if (!user.getPassword().equals(DigestUtils.sha256Hex(password))) {
                bool = false;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return bool;
    }

    @Override
    public String registrationCheck(User user, String secondPass) throws ServiceException {
        String response = "";
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getDriverDAO();

        User check = null;

        try {
            check = userDAO.getUser(user.getEmail());
            if (check != null) {
                response = "existingEmail";
            }
            if (!secondPass.equals(user.getPassword())) {
                response = "secPassIncorrect";
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return response;
    }
}
