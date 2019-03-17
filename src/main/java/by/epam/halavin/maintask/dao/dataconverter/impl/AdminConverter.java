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
    public static final String ID_COL = "admin_id";
    public static final String EMAIL_COL = "email";
    public static final String NAME_COL = "name";
    public static final String SURNAME_COL = "surname";
    public static final String TEL_COL = "tel";
    public static final String PASSWORD_COl = "password";


    @Override
    public User convertFirstRow(ResultSet resultSet) throws DAOException {
        AdminBuilder builder = new AdminBuilder();

        try {
            if (!resultSet.next()) {
                return null;
            }
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
