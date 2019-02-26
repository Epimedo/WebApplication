package by.epam.halavin.maintask.service.driver;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.controller.info.StringAttributes;
import by.epam.halavin.maintask.dao.repository.DriverRepository;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.validate.Validator;
import by.epam.halavin.maintask.service.validate.impl.DriverValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DriverManager {

    public void activate(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        Validator validator = new DriverValidator();

        try {
            if (validator.check(request)) {
                Driver driver = (Driver) session.getAttribute(StringAttributes.ACCOUNT.getName());
                DriverRepository.getInstance().addDriver(driver);
                session.setAttribute(StringAttributes.DRIVER_STATUS.getName(), StringAttributes.ACTIVATE.getName());
            }
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
    }

    public void deactivate(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Driver driver = (Driver) session.getAttribute(StringAttributes.ACCOUNT.getName());

        DriverRepository.getInstance().removeDriver(driver);
        session.setAttribute(StringAttributes.DRIVER_STATUS.getName(), null);
    }
}
