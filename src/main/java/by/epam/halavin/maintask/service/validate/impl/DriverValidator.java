package by.epam.halavin.maintask.service.validate.impl;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.controller.info.StringAttributes;
import by.epam.halavin.maintask.dao.CarManager;
import by.epam.halavin.maintask.dao.DAOFactory;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.validate.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class DriverValidator implements Validator {

    @Override
    public boolean check(HttpServletRequest request) throws ServiceException {
        Car car = null;
        boolean bool = true;
        HttpSession session = request.getSession();
        CarManager carManager = DAOFactory.getInstance().createCarManager();
        Driver driver = (Driver) session.getAttribute(StringAttributes.ACCOUNT.getName());

        try {
            car = carManager.getCar(driver.getId());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        if (driver.getStatus().equals(StringAttributes.BAN.getName())) {
            bool = false;
            session.setAttribute(StringAttributes.DRIVER_STATUS.getName(), StringAttributes.ACCOUNT_BAN.getName());
        }

        if (car.getDate().compareTo(new Date()) < 0) {
            bool = false;
            session.setAttribute(StringAttributes.DRIVER_STATUS.getName(), StringAttributes.CAR_CHECKUP_END.getName());
        }

        return bool;
    }
}
