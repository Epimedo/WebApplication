package by.epam.halavin.maintask.dao.impldao;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.dao.car.CarDAO;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.car.SimpleCarDAO;
import org.junit.BeforeClass;
import org.junit.Test;

public class SimpleCarDAOTest {
    private static CarDAO carDAO;

    @BeforeClass
    public static void init() {
        carDAO = new SimpleCarDAO();
    }

    @Test
    public void getCar() throws DAOException {
        Car car = carDAO.getCar(1);

        System.out.println(car);
    }

    @Test
    public void getCarsIndexOf() throws DAOException {

        System.out.println(carDAO.getCarsIndexOf(1,5));
    }
}