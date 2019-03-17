package by.epam.halavin.maintask.dao.street;

import by.epam.halavin.maintask.dao.exception.DAOException;

public interface StreetDAO {

    String getStreet(String streetName) throws DAOException;

    void addStreet(String streetName) throws DAOException;

    String findSuitableStreet(String symbols) throws DAOException;
}
