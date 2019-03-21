package by.epam.halavin.maintask.dao.dataconverter;

import by.epam.halavin.maintask.dao.exception.DAOException;

import java.sql.ResultSet;
import java.util.List;

/**
 * Defines methods for street converter
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public interface StreetConverter {

    /**
     * Converts first row in result set into street name and return it
     *
     * @param set
     * @return street name
     * @throws DAOException
     */
    String convertFirstRow(ResultSet set) throws DAOException;

    /**
     * Converts all rows in result set into street names adn return list of them
     *
     * @param set
     * @return street list
     * @throws DAOException
     */
    List<String> convertRows(ResultSet set) throws DAOException;
}
