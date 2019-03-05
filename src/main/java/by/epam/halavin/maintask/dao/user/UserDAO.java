package by.epam.halavin.maintask.dao.user;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.util.List;

public interface UserDAO {

    User getUser(String email) throws DAOException;

    User getUser(int id) throws DAOException;

    boolean registration(User user) throws DAOException;

    void userUpdate(User user) throws DAOException;

    String getPassword(String email) throws DAOException;

    List<User> getUsersIndexOf(int fr, int ls) throws DAOException;

    int getUsersCount() throws DAOException;

}
