package by.epam.halavin.maintask.dao.street;

import by.epam.halavin.maintask.dao.exception.DAOException;

/**
 * Presents methods for interacting with the street database
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public interface StreetDAO {

    /**
     * Function to take street
     *
     * @param streetName
     * @return
     * @throws DAOException
     */
    String getStreet(String streetName) throws DAOException;

    /**
     * Function to add new street to database
     *
     * @param streetName
     * @throws DAOException
     */
    void addStreet(String streetName) throws DAOException;

    /**
     * Function to find a street matching first name
     *
     * @param symbols
     * @return
     * @throws DAOException
     */
    String findSuitableStreet(String symbols) throws DAOException;
}
