package by.epam.halavin.maintask.service.user;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.service.exception.ServiceException;

/**
 * Defines methods for authorization
 */
public interface UserService {

    /**
     * Function to sign in
     *
     * @param email
     * @param password
     * @return user
     * @throws ServiceException
     */
    User signIn(String email, String password) throws ServiceException;

    /**
     * Function to registration
     *
     * @param user
     * @return true/false(success/failed registration)
     * @throws ServiceException
     */
    boolean registration(User user) throws ServiceException;

}
