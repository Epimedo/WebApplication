package by.epam.halavin.maintask.dao.dataconverter.impl;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.dataconverter.UserConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.util.builder.user.DriverBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverConverter implements UserConverter {
    public static final int ID_COL = 1;
    public static final int EMAIL_COL = 2;
    public static final int NAME_COL = 3;
    public static final int SURNAME_COL = 4;
    public static final int TEL_COL = 5;
    public static final int STATUS_COL = 6;
    public static final int PASSWORD_COl = 7;
    public static final int CAR_NAME = 8;
    public static final int CAR_NUMBER = 9;


    @Override
    public User convertFirstRow(ResultSet resultSet) throws DAOException {
        DriverBuilder builder = new DriverBuilder();

        try {
            if (!resultSet.isFirst()) {
                resultSet.first();
            }
            builder.setId(resultSet.getInt(ID_COL)).setName(resultSet.getString(NAME_COL))
                    .setSurname(resultSet.getString(SURNAME_COL)).setEmail(resultSet.getString(EMAIL_COL))
                    .setTel(resultSet.getString(TEL_COL)).setPassword(resultSet.getString(PASSWORD_COl))
                    .setStatus(resultSet.getString(STATUS_COL)).setCarName(resultSet.getString(CAR_NAME))
                    .setCarNumber(resultSet.getString(CAR_NUMBER));


        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return builder.getObject();
    }

    @Override
    public List<User> convertRows(ResultSet resultSet) throws DAOException {
        List<User> list = new ArrayList<>();
        DriverBuilder builder = new DriverBuilder();

        try {
            resultSet.beforeFirst();

            while (resultSet.next()) {
                builder.setId(resultSet.getInt(ID_COL)).setName(resultSet.getString(NAME_COL))
                        .setSurname(resultSet.getString(SURNAME_COL)).setEmail(resultSet.getString(EMAIL_COL))
                        .setTel(resultSet.getString(TEL_COL)).setPassword(resultSet.getString(PASSWORD_COl))
                        .setStatus(resultSet.getString(STATUS_COL)).setCarName(resultSet.getString(CAR_NAME))
                        .setCarNumber(resultSet.getString(CAR_NUMBER));

                list.add(builder.getObject());
                builder.reset();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return list;
    }
}
