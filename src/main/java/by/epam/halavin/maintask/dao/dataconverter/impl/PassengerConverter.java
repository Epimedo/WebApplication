package by.epam.halavin.maintask.dao.dataconverter.impl;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.dataconverter.UserConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.util.builder.user.PassengerBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerConverter implements UserConverter {
    public static final int ID_COL = 1;
    public static final int EMAIL_COL = 2;
    public static final int NAME_COL = 3;
    public static final int SURNAME_COL = 4;
    public static final int TEL_COL = 5;
    public static final int STATUS_COL = 6;
    public static final int PASSWORD_COl = 7;
    public static final int BONUS_COL = 8;
    public static final int DISCOUNT_COL = 9;

    @Override
    public User convertFirstRow(ResultSet resultSet) throws DAOException {
        PassengerBuilder builder = new PassengerBuilder();

        try {
            if (!resultSet.isFirst()) {
                resultSet.first();
            }
            builder.setId(resultSet.getInt(ID_COL)).setName(resultSet.getString(NAME_COL))
                    .setSurname(resultSet.getString(SURNAME_COL)).setEmail(resultSet.getString(EMAIL_COL))
                    .setTel(resultSet.getString(TEL_COL)).setPassword(resultSet.getString(PASSWORD_COl))
                    .setStatus(resultSet.getString(STATUS_COL)).setBonus(resultSet.getDouble(BONUS_COL))
                    .setDiscount(resultSet.getDouble(DISCOUNT_COL));

        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return builder.getObject();
    }

    @Override
    public List<User> convertRows(ResultSet resultSet) throws DAOException {
        List<User> list = new ArrayList<>();

        PassengerBuilder builder = new PassengerBuilder();

        try {
            resultSet.beforeFirst();

            while (resultSet.next()) {
                builder.setId(resultSet.getInt(ID_COL)).setName(resultSet.getString(NAME_COL))
                        .setSurname(resultSet.getString(SURNAME_COL)).setEmail(resultSet.getString(EMAIL_COL))
                        .setTel(resultSet.getString(TEL_COL)).setPassword(resultSet.getString(PASSWORD_COl))
                        .setStatus(resultSet.getString(STATUS_COL)).setBonus(resultSet.getDouble(BONUS_COL))
                        .setDiscount(resultSet.getDouble(DISCOUNT_COL));

                list.add(builder.getObject());
                builder.reset();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return list;
    }
}
