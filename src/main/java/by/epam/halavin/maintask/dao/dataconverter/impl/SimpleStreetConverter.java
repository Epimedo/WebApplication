package by.epam.halavin.maintask.dao.dataconverter.impl;

import by.epam.halavin.maintask.dao.dataconverter.StreetConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SimpleStreetConverter implements StreetConverter {
    private final String STREET_NAME = "street_name";

    @Override
    public String convertFirstRow(ResultSet resultSet) throws DAOException {
        String result = "";

        try {
            if (!resultSet.isFirst()) {
                resultSet.first();
            }

            result = resultSet.getString(STREET_NAME);
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return result;
    }

    @Override
    public List<String> convertRows(ResultSet set) {
        return null;
    }
}
