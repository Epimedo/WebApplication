package by.epam.halavin.maintask.dao.dataconverter.impl;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.dataconverter.UserConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.util.builder.user.AdminBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminConverter implements UserConverter {
    public static final int ID_COL = 1;
    public static final int EMAIL_COL = 2;
    public static final int NAME_COL = 3;
    public static final int SURNAME_COL = 4;
    public static final int TEL_COL = 5;
    public static final int PASSWORD_COl = 6;


    @Override
    public User convertFirstRow(ResultSet resultSet) throws DAOException {
        AdminBuilder builder = new AdminBuilder();

        try {
            if (!resultSet.isFirst()) {
                resultSet.first();
            }
            builder.setId(resultSet.getInt(ID_COL)).setName(resultSet.getString(NAME_COL))
                    .setSurname(resultSet.getString(SURNAME_COL)).setEmail(resultSet.getString(EMAIL_COL))
                    .setTel(resultSet.getString(TEL_COL)).setPassword(resultSet.getString(PASSWORD_COl));

        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return builder.getObject();
    }

    @Override
    public List<User> convertRows(ResultSet resultSet) throws DAOException {
        return new ArrayList<>();
    }

}
