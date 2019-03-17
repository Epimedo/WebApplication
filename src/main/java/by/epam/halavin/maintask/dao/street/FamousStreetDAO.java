package by.epam.halavin.maintask.dao.street;

import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.dataconverter.StreetConverter;
import by.epam.halavin.maintask.dao.dataconverter.impl.SimpleStreetConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FamousStreetDAO implements StreetDAO {
    private final String insertStreet = "INSERT INTO `mydb`.`streets`(`street_name`) VALUES (?);";
    private final String selectStreetLike = "SELECT * FROM mydb.streets where street_name like(?);";
    private final String selectStreet = "SELECT * FROM mydb.streets where street_name = ? ;";

    @Override
    public String getStreet(String streetName) throws DAOException {
        String result = "";
        Connection connection = ConnectionPool.getInstance().getConnection();
        StreetConverter converter = new SimpleStreetConverter();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(selectStreet);
            statement.setString(1, streetName);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                result = converter.convertFirstRow(set);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        return result;
    }

    @Override
    public void addStreet(String streetName) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(insertStreet);
            statement.setString(1, streetName);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public String findSuitableStreet(String symbols) throws DAOException {
        String result = "";
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        StreetConverter converter = new SimpleStreetConverter();

        try {
            statement = connection.prepareStatement(selectStreetLike);
            statement.setString(1, symbols + "%");
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                result = converter.convertFirstRow(set);
            }
        } catch (SQLException e) {

        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }

        return result;
    }
}
