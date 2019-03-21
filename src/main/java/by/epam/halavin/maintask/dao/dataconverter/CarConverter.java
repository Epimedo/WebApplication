package by.epam.halavin.maintask.dao.dataconverter;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.sql.ResultSet;
import java.util.List;

/**
 * Defines methods for car converter
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public interface CarConverter {

    /**
     * Method converts first row of result set into new car object and return it
     *
     * @param resultSet
     * @return car
     * @throws DAOException
     */
    Car convertFirstRow(ResultSet resultSet) throws DAOException;

    /**
     * Method converts all rows of result set into car objects adn return list with this objects
     *
     * @param resultSet
     * @return cars
     * @throws DAOException
     */
    List<Car> convertRows(ResultSet resultSet) throws DAOException;
}
