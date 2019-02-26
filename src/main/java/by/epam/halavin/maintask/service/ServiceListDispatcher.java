package by.epam.halavin.maintask.service;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.service.exception.ServiceException;

import java.util.List;

public interface ServiceListDispatcher {
    int NUMBER_PLUS = 5;

    void setSource(String str);

    List<User> getNextUserListItems() throws ServiceException;

    List<User> getCurrentUserListItems() throws ServiceException;

    List<User> getNumberOfUsers(int count) throws ServiceException;

    List<Integer> getUserCount() throws ServiceException;
}
