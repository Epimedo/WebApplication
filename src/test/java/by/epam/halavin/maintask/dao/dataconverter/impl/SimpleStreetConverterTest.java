package by.epam.halavin.maintask.dao.dataconverter.impl;

import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.dataconverter.StreetConverter;
import by.epam.halavin.maintask.dao.exception.DAOException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleStreetConverterTest {

    @Test
    public void convertFirstRow() throws DAOException, SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from streets");
        StreetConverter converter = new SimpleStreetConverter();

        String expected = "ангарская 4";
        Assert.assertEquals(converter.convertFirstRow(resultSet), expected);
    }

}