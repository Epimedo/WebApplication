package by.epam.halavin.maintask.dao.dataconverter.impl;

import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.dao.dataconverter.OrderConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.user.DriverDAO;
import by.epam.halavin.maintask.dao.user.PassengerDAO;
import by.epam.halavin.maintask.dao.user.UserDAO;
import by.epam.halavin.maintask.util.builder.order.SimpleOrderBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleOrderConverter implements OrderConverter {
    private String DEPARTURE = "departure";
    private String ARRIVAL = "arrival";
    private String COST = "cost";
    private String DATE = "date";
    private String ID = "order_id";
    private String USER_ID = "user_id";
    private String DRIVER_ID = "driver_id";

    @Override
    public Order convertFirstRow(ResultSet resultSet) throws DAOException {
        SimpleOrderBuilder builder = new SimpleOrderBuilder();
        UserDAO passDAO = new PassengerDAO();
        UserDAO driverDAO = new DriverDAO();

        User passenger = new Passenger();
        User driver = new Driver();

        try {
            if (!resultSet.isFirst()) {
                resultSet.first();
            }
            passenger = passDAO.getUser(resultSet.getInt(USER_ID));
            driver = driverDAO.getUser(resultSet.getInt(DRIVER_ID));

            builder.setDriver((Driver) driver).setPassenger((Passenger) passenger).setCost(resultSet.getDouble(COST))
                    .setDate(resultSet.getDate(DATE)).setArrival(resultSet.getString(ARRIVAL))
                    .setDepature(resultSet.getString(DEPARTURE)).setId(resultSet.getInt(ID));


        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return builder.getObject();
    }

    @Override
    public List<Order> convertRows(ResultSet resultSet) throws DAOException {
        SimpleOrderBuilder builder = new SimpleOrderBuilder();
        UserDAO passDAO = new PassengerDAO();
        UserDAO driverDAO = new DriverDAO();

        List<Order> list = new ArrayList<>();
        User passenger = new Passenger();
        User driver = new Driver();

        try {
            resultSet.beforeFirst();

            while (resultSet.next()) {
                passenger = passDAO.getUser(resultSet.getInt(USER_ID));
                driver = driverDAO.getUser(resultSet.getInt(DRIVER_ID));

                builder.setDriver((Driver) driver).setPassenger((Passenger) passenger).setCost(resultSet.getDouble(COST))
                        .setDate(resultSet.getDate(DATE)).setArrival(resultSet.getString(ARRIVAL))
                        .setDepature(resultSet.getString(DEPARTURE)).setId(resultSet.getInt(ID));

                list.add(builder.getObject());
                builder.reset();
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return list;
    }
}
