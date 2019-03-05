package by.epam.halavin.maintask.util.creators;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.util.UtilException;
import by.epam.halavin.maintask.util.builder.user.UserBuilder;

import javax.servlet.http.HttpServletRequest;

public abstract class UserCreator {
    protected User user;
    protected UserBuilder userBuilder;

    public abstract User create(HttpServletRequest request) throws UtilException;
}
