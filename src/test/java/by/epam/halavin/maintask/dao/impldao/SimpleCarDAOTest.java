package by.epam.halavin.maintask.dao.impldao;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.dao.car.CarDAO;
import by.epam.halavin.maintask.dao.car.SimpleCarDAO;
import by.epam.halavin.maintask.dao.exception.DAOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class SimpleCarDAOTest {
    private static CarDAO carDAO;
    private static Car expected = new Car();

    @BeforeClass
    public static void init() throws ParseException {
        carDAO = new SimpleCarDAO();
        expected.setId(1);
        expected.setName("renault Logan");
        expected.setNumber("5647BN-7");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        java.sql.Date date = new java.sql.Date(simpleDateFormat.parse("2020-08-11").getTime());
        expected.setDate(date);
    }

    @Test
    public void getCar() throws DAOException {
        Car car = carDAO.getCar(1);
        Assert.assertEquals(car, expected);
    }

    @Test
    public void getCarsIndexOf() throws DAOException {
        String expected = "[Car{id=31, number='1020HR-7', name='222', date=null}, Car{id=1, number='5647BN-7'," +
                " name='renault Logan', date=null}, Car{id=2, number='9234CA-7', name='renault Logan', date=null}," +
                " Car{id=3, number='4213TU-7', name='renault Logan', date=null}, Car{id=6, number='4536TR-7'," +
                " name='renault Logan', date=null}]";

        Assert.assertEquals(expected, carDAO.getCarsIndexOf(1, 5).toString());
    }
}