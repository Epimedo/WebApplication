package by.epam.halavin.maintask.service.validate;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.service.exception.ServiceException;

public interface Validator {

    boolean isBan(User user) throws ServiceException;

    boolean isCorrectEmail(String email) throws ServiceException;

    boolean isCorrectPassword(String email, String password) throws ServiceException;

    String registrationCheck(User user,String secondPass) throws ServiceException;
}
