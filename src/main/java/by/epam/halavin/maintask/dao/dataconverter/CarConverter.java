package by.epam.halavin.maintask.dao.dataconverter;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.util.builder.CarBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarConverter {
    public static final int ID_COL = 1;
    public static final int CAR_NAME = 2;
    public static final int CAR_NUMBER = 3;
    public static final int CHECKUP_END = 4;

    public Car convertFirstRow(ResultSet resultSet) throws DAOException {
        CarBuilder builder = new CarBuilder();

        try {
            if (!resultSet.isFirst()) {
                resultSet.first();
            }
            builder.setId(resultSet.getInt(ID_COL)).setCarName(resultSet.getString(CAR_NAME))
                    .setCarNumber(resultSet.getString(CAR_NUMBER)).setCarCheckupEnd(resultSet.getDate(CHECKUP_END));

        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return builder.getObject();
    }

    public List<Car> convertRows(ResultSet resultSet) throws DAOException {
        CarBuilder builder = new CarBuilder();
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
