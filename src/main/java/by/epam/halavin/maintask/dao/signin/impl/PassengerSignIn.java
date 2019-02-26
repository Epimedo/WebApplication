package by.epam.halavin.maintask.dao.signin.impl;

import by.epam.halavin.maintask.controller.info.StringAttributes;
import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.dataconverter.UserConverter;
import by.epam.halavin.maintask.dao.dataconverter.impl.PassengerConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.signin.SignIn;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PassengerSignIn implements SignIn {

    @Override
    public String sign(String email, String password, HttpSession session) throws DAOException {
        String response = "";
        Connection connection = ConnectionPool.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where email = '" + email + "'");
            response = check(password, resultSet);

            if (response.equals(StringAttributes.SIGNED.getName())) {
                UserConverter converter = new PassengerConverter();
                session.setAttribute(StringAttributes.ACCOUNT.getName(), converter.convertFirstRow(resultSet));
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return response;
    }

    private String check(String password, ResultSet resultSet) throws SQLException {
        String result = StringAttributes.SIGNED.getName();

        if (!resultSet.isBeforeFirst()) {
            result = StringAttributes.INCORRECT_EMAIL.getName();
        } else {
            resultSet.next();
            if (!resultSet.getString(7).equals(DigestUtils.sha256Hex(password))) {
                result = StringAttributes.INCORRECT_PASSWORD.getName();
            }
        }

        return result;
    }
}
