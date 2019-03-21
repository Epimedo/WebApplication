package by.epam.halavin.maintask.dao.dataconverter;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.sql.ResultSet;
import java.util.List;

/**
 * Defines methods for user converter
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public interface UserConverter {

    /**
     * Convert first row in result set into user object and return it
     *
     * @param resultSet
     * @return user
     * @throws DAOException
     */
    User convertFirstRow(ResultSet resultSet) throws DAOException;

    /**
     * COnvert all wors in result set into use objects nad return list of them
     *
     * @param resultSet
     * @return usr list
     * @throws DAOException
     */
    List<User> convertRows(ResultSet resultSet) throws DAOException;
}
