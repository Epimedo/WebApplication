package by.epam.halavin.maintask.dao.registration.impl;

import by.epam.halavin.maintask.controller.info.StringAttributes;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.dataconverter.UserConverter;
import by.epam.halavin.maintask.dao.dataconverter.impl.PassengerConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.registration.UserRegister;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PassengerRegister implements UserRegister {
    private String email = StringAttributes.EMAIL.getName();
    private String name = StringAttributes.NAME.getName();
    private String surname = StringAttributes.SURNAME.getName();
    private String tel = StringAttributes.TEL.getName();
    private String sqlInsertUsers = "INSERT INTO users(`email`,`name`,`surname`,`tel`," +
            "`status`,`password`,`bonus`,`discount`) VALUES ";
    private String sqlSelectUsersEnail = "select * from users where email = '";
    private String sqlselectUserTel = "select * from users where tel = '";

    @Override
    public String registerIn(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        String responseStatus = "";
        HttpSession session = request.getSession();
        Connection connection = ConnectionPool.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            responseStatus = check(request, statement);

            if (responseStatus.equals(StringAttributes.SUCCESS.getName())) {
                statement.executeUpdate(sqlInsertUsers +
                        "(\"" + request.getParameter(email) + "\"," +
                        "\"" + request.getParameter(name) + "\"," +
                        "\"" + request.getParameter(surname) + "\"," +
                        "\"" + request.getParameter(tel) + "\"," +
                        "\"unban\"," +
                        "\"" + DigestUtils.sha256Hex(request.getParameter(StringAttributes.PASSWORD.getName())) + "\"," +
                        "null," +
                        "null)");

                ResultSet resultSet = statement.executeQuery(sqlSelectUsersEnail
                        + request.getParameter(StringAttributes.EMAIL.getName()) + "'");
                UserConverter converter = new PassengerConverter();
                session.setAttribute(StringAttributes.ACCOUNT.getName(), converter.convertFirstRow(resultSet));
                responseStatus = StringAttributes.SIGNED.getName();
            }

        } catch (SQLException | DAOException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return responseStatus;
    }

    private String check(HttpServletRequest request, Statement statement) throws DAOException {
        String result = StringAttributes.SUCCESS.getName();

        try {
            ResultSet resultSet = statement.executeQuery(sqlSelectUsersEnail
                    + request.getParameter(StringAttributes.EMAIL.getName()) + "'");
            if (resultSet.next()) {
                result = "existingEmail";
            }

            resultSet = statement.executeQuery(sqlselectUserTel
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
