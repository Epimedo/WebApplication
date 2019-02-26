package by.epam.halavin.maintask.dao.repository;

import by.epam.halavin.maintask.dao.exception.DAOException;
import org.junit.Test;

import java.util.Objects;

public class SimpleRepositoryTest {

    @Test
    public void getMap() throws DAOException {
        System.out.println(Objects.toString(SimpleRepository.PASSENGER_REPOSITORY.getList()));
        System.out.println(Objects.toString(SimpleRepository.DRIVER_REPOSITORY.getList()));
    }
}