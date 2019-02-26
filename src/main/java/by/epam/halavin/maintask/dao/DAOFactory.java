package by.epam.halavin.maintask.dao;

import by.epam.halavin.maintask.dao.connection.ConnectionPool;
import by.epam.halavin.maintask.dao.edited.Edited;
import by.epam.halavin.maintask.dao.edited.impl.DriverEditor;
import by.epam.halavin.maintask.dao.edited.impl.PassengerEdited;
import by.epam.halavin.maintask.dao.repository.DriverRepository;

public class DAOFactory {
    private static DAOFactory instance = new DAOFactory();
    private ConnectionPool connectionPool;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public Edited createPassengerEdited() {
        return new PassengerEdited();
    }

    public Edited createDriverEdited() {
        return new DriverEditor();
    }

    public CarManager createCarManager() {
        return new CarManager();
    }

    public DriverRepository getDriverRepository(){
        return DriverRepository.getInstance();
    }
}
