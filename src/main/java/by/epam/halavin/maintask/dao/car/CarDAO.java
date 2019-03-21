package by.epam.halavin.maintask.dao.car;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.dao.exception.DAOException;

import java.util.List;

/**
 * Presents methods for interacting with the car database
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public interface CarDAO {

    /**
     * Function to get car with appropriate id
     *
     * @param id
     * @return - car
     * @throws DAOException
     */
    Car getCar(int id) throws DAOException;

    /**
     * Function to get a list of cars in a certain range
     *
     * @param fr
     * @param ls
     * @return car list
     * @throws DAOException
     */
    List<Car> getCarsIndexOf(int fr, int ls) throws DAOException;

    /**
     * Car database registration function
     *
     * @param car
     * @return true/false(success/failed registration)
     * @throws DAOException
     */
    boolean registration(Car car) throws DAOException;

    /**
     * Function to get car with appropriate name and number
     *
     * @param name
     * @param number
     * @return car
     * @throws DAOException
     */
    Car getCar(String name, String number) throws DAOException;
}
