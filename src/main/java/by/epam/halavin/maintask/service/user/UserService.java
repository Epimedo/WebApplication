package by.epam.halavin.maintask.service.user;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.service.exception.ServiceException;

public interface UserService {

    User signIn(String email,String password) throws ServiceException;

    boolean registration(User user) throws ServiceException;

}
