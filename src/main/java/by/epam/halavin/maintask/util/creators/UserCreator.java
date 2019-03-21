package by.epam.halavin.maintask.util.creators;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.util.UtilException;
import by.epam.halavin.maintask.util.builder.user.UserBuilder;

import javax.servlet.http.HttpServletRequest;

/**
 * Creator for user objects
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public abstract class UserCreator {
    protected User user;
    protected UserBuilder userBuilder;

    /**
     * Function gets http request with parameters and create a new user object.
     *
     * @param request
     * @return - user object
     * @throws UtilException
     */
    public abstract User create(HttpServletRequest request) throws UtilException;
}
