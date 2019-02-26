package by.epam.halavin.maintask.dao.registration.impl;

import by.epam.halavin.maintask.controller.info.StringAttributes;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.registration.UserRegister;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CarRegister implements UserRegister {
    private String sqlInsertCars = "INSERT INTO `mydb`.`cars`";
    private String sqlSelectIdFromCars = "select car_id from cars where `number` = ";
    private String sqlSelectCars = "select * from cars where number = '";

    @Override
    public String registerIn(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        String responseStatus = "";
        HttpSession session = request.getSession();
        Connection connection = ConnectionPool.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            responseStatus = check(request, statement);

            if (responseStatus.equals("success")) {
                statement.executeUpdate(sqlInsertCars +
                        "(`name`," +
                        "`number`," +
                        "`checkup_end`)" +
                        "VALUES" +
                        "(\"" + request.getParameter("car") + "\"," +
                        "\"" + request.getParameter("carNumber") + "\"," +
                        "\"" + request.getParameter("checkupEnd") + "\");");
                ResultSet resultSet = statement.executeQuery(sqlSelectIdFromCars +
                        " \"" + request.getParameter("carNumber") + "\" ;");
                resultSet.next();
                responseStatus = resultSet.getString(1);
            }

        } catch (SQLException | DAOException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return responseStatus;
    }

    public String check(HttpServletRequest request, Statement statement) throws DAOException {
        String result = StringAttributes.SUCCESS.getName();

        try {
            ResultSet resultSet = statement.executeQuery(sqlSelectCars
                    + request.getParameter("carNumber") + "'");
            if (resultSet.next()) {
                result = "existingNumber";
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return result;
    }
}
