package by.epam.halavin.maintask.service.validate;

import by.epam.halavin.maintask.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface Validator {

    boolean check(HttpServletRequest request) throws ServiceException;
}
