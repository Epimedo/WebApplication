package by.epam.halavin.maintask.dao.registration.impl;

import by.epam.halavin.maintask.controller.info.StringAttributes;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.registration.UserRegister;
import by.epam.halavin.maintask.dao.repository.SimpleRepository;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DriverRegister implements UserRegister {
    private String sqlInsertDrivers = "INSERT INTO drivers(`email`,`name`,`surname`,`tel`," +
            "`status`,`password`,`car_id`) VALUES ";
    private String sqlSelectDriversEmail = "select * from drivers where email = '";
    private String sqlSelectDriversTel = "select * from users where tel = '";

    @Override
    public String registerIn(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        String responseStatusDriver = "";
        String responseStatusCar = "";
        Connection connection = ConnectionPool.getInstance().getConnection();
        CarRegister carRegister = new CarRegister();

        try {
            Statement statement = connection.createStatement();
            responseStatusDriver = check(request, statement);
            responseStatusCar = check(request, statement);

            if (responseStatusDriver.equals(StringAttributes.SUCCESS.getName()) &&
                    responseStatusCar.equals(StringAttributes.SUCCESS.getName())) {
                statement.executeUpdate(sqlInsertDrivers +
                        "(\"" + request.getParameter("email") + "\"," +
                        "\"" + request.getParameter("name") + "\"," +
                        "\"" + request.getParameter("surname") + "\"," +
                        "\"" + request.getParameter("tel") + "\"," +
                        "\"ban\"," +
                        "\"" + DigestUtils.sha256Hex(request.getParameter("password")) + "\"," +
                        "" + carRegister.registerIn(request, response) + ");");

                responseStatusDriver = StringAttributes.SUCCESS.getName();
                SimpleRepository.DRIVER_REPOSITORY.listInit();
            }

        } catch (SQLException | DAOException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return responseStatusDriver;
    }

    private String check(HttpServletRequest request, Statement statement) throws DAOException {
        String result = StringAttributes.SUCCESS.getName();

        try {
            ResultSet resultSet = statement.executeQuery(sqlSelectDriversEmail
                    + request.getParameter("email") + "'");
            if (resultSet.next()) {
                result = "existingEmail";
            }

            resultSet = statement.executeQuery(sqlSelectDriversTel
                    + request.getParameter("tel") + "'");
            if (resultSet.next()) {
                result = "existingPhone";
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return result;
    }
}
