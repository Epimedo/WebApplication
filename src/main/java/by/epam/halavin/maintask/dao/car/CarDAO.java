package by.epam.halavin.maintask.dao.car;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.util.List;

public interface CarDAO {

    Car getCar(int id) throws DAOException;

    List<Car> getCarsIndexOf(int fr, int ls) throws DAOException;

    boolean registration(Car car) throws DAOException;

    Car getCar(String name, String number) throws DAOException;
}
