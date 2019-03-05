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
    public static final String ID_COL = "user_id";
    public static final String EMAIL_COL = "email";
    public static final String NAME_COL = "name";
    public static final String SURNAME_COL = "surname";
    public static final String TEL_COL = "tel";
    public static final String STATUS_COL = "status";
    public static final String PASSWORD_COl = "password";
    public static final String BONUS_COL = "bonus";
    public static final String DISCOUNT_COL = "discount";

    @Override
    public User convertFirstRow(ResultSet resultSet) throws DAOException {
        PassengerBuilder builder = new PassengerBuilder();

        try {
            if(!resultSet.next()){
                return null;
            }
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
