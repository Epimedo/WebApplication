package by.epam.halavin.maintask.dao;

import by.epam.halavin.maintask.dao.exception.DAOException;
import org.junit.Test;

import java.util.Objects;

public class CarManagerTest {

    @Test
    public void getCars() throws DAOException {
        CarManager carManager = new CarManager();
        System.out.println(Objects.toString(carManager.getCars()));
        System.out.println(Objects.toString(carManager.getCar(1)));
    }
}