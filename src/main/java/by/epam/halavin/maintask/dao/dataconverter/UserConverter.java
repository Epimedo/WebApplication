package by.epam.halavin.maintask.dao.dataconverter;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.sql.ResultSet;
import java.util.List;

public interface UserConverter {

    User convertFirstRow(ResultSet resultSet) throws DAOException;

    List<User> convertRows(ResultSet resultSet) throws DAOException;
}
