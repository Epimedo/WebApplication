package by.epam.halavin.maintask.dao.dataconverter;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.sql.ResultSet;
import java.util.List;

public interface CarConverter {

    Car convertFirstRow(ResultSet resultSet) throws DAOException;

    List<Car> convertRows(ResultSet resultSet) throws DAOException;
}
