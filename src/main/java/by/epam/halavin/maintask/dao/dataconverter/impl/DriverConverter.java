package by.epam.halavin.maintask.dao.dataconverter.impl;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.car.CarDAO;
import by.epam.halavin.maintask.dao.dataconverter.UserConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.car.SimpleCarDAO;
import by.epam.halavin.maintask.util.builder.user.DriverBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverConverter implements UserConverter {
    public static final String ID_COL = "driver_id";
    public static final String EMAIL_COL = "email";
    public static final String NAME_COL = "driver_name";
    public static final String SURNAME_COL = "surname";
    public static final String TEL_COL = "tel";
    public static final String STATUS_COL = "status";
    public static final String PASSWORD_COl = "password";
    public static final String CAR_NAME = "name";
    public static final String CAR_NUMBER = "number";


    @Override
    public User convertFirstRow(ResultSet resultSet) throws DAOException {
        DriverBuilder builder = new DriverBuilder();
        CarDAO carDAO = new SimpleCarDAO();

        try {
            if(!resultSet.next()){
                return null;
            }
            if (!resultSet.isFirst()) {
                resultSet.first();
            }
            Car car = carDAO.getCar(resultSet.getString(CAR_NAME),resultSet.getString(CAR_NUMBER));
            builder.setId(resultSet.getInt(ID_COL)).setName(resultSet.getString(NAME_COL))
                    .setSurname(resultSet.getString(SURNAME_COL)).setEmail(resultSet.getString(EMAIL_COL))
                    .setTel(resultSet.getString(TEL_COL)).setPassword(resultSet.getString(PASSWORD_COl))
                    .setStatus(resultSet.getString(STATUS_COL)).setCarName(resultSet.getString(CAR_NAME))
                    .setCarNumber(resultSet.getString(CAR_NUMBER)).setCar(car);


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
