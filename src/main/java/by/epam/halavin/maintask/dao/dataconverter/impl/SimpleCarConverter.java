package by.epam.halavin.maintask.dao.dataconverter.impl;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.dao.dataconverter.CarConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.util.builder.car.SipmpleCarBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SimpleCarConverter implements CarConverter {
    public static final String ID_COL = "car_id";
    public static final String CAR_NAME = "name";
    public static final String CAR_NUMBER = "number";
    public static final String CHECKUP_END = "checkup_end";

    public Car convertFirstRow(ResultSet resultSet) throws DAOException {
        SipmpleCarBuilder builder = new SipmpleCarBuilder();

        try {
            if (!resultSet.isFirst()) {
                resultSet.first();
            }
            Date date = resultSet.getDate(CHECKUP_END);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date secDate = simpleDateFormat.parse(date.toString());

            builder.setId(resultSet.getInt(ID_COL)).setCarName(resultSet.getString(CAR_NAME))
                    .setCarNumber(resultSet.getString(CAR_NUMBER)).setCarCheckupEnd(secDate);

        } catch (SQLException | ParseException e) {
            throw new DAOException(e);
        }

        return builder.getObject();
    }

    public List<Car> convertRows(ResultSet resultSet) throws DAOException {
        SipmpleCarBuilder builder = new SipmpleCarBuilder();
        List<Car> list = new ArrayList<>();

        try {
            resultSet.beforeFirst();

            while (resultSet.next()) {
                builder.setId(resultSet.getInt(ID_COL)).setCarName(resultSet.getString(CAR_NAME))
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
