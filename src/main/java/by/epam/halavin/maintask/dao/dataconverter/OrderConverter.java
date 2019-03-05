package by.epam.halavin.maintask.dao.dataconverter;

import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.sql.ResultSet;
import java.util.List;

public interface OrderConverter {

    Order convertFirstRow(ResultSet resultSet) throws DAOException;

    List<Order> convertRows(ResultSet resultSet) throws DAOException;
}
