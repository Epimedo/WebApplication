package by.epam.halavin.maintask.dao.dataconverter;

import by.epam.halavin.maintask.dao.exception.DAOException;

import java.sql.ResultSet;
import java.util.List;

public interface StreetConverter {

    String convertFirstRow(ResultSet set) throws DAOException;

    List<String> convertRows(ResultSet set) throws DAOException;
}
