package by.epam.halavin.maintask.dao;

import by.epam.halavin.maintask.dao.order.OrderDAO;
import by.epam.halavin.maintask.dao.order.SimpleOrderDAO;
import by.epam.halavin.maintask.dao.repository.DriverRepository;
import by.epam.halavin.maintask.dao.street.FamousStreetDAO;
import by.epam.halavin.maintask.dao.street.StreetDAO;
import by.epam.halavin.maintask.dao.user.AdminDAO;
import by.epam.halavin.maintask.dao.user.DriverDAO;
import by.epam.halavin.maintask.dao.user.PassengerDAO;
import by.epam.halavin.maintask.dao.user.UserDAO;

public class DAOFactory {
    private static DAOFactory instance = new DAOFactory();
    private final UserDAO passDAO = new PassengerDAO();
    private final UserDAO driverDAO = new DriverDAO();
    private final UserDAO adminDAO = new AdminDAO();
    private final OrderDAO simpleOderDAO = new SimpleOrderDAO();
    private final StreetDAO streetDAO = new FamousStreetDAO();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public StreetDAO getStreetDAO() {
        return streetDAO;
    }

    public UserDAO getPassDAO() {
        return passDAO;
    }

    public UserDAO getDriverDAO() {
        return driverDAO;
    }

    public UserDAO getAdminDAO() {
        return adminDAO;
    }

    public OrderDAO getSimpleOrderDAO() {
        return simpleOderDAO;
    }

    public DriverRepository getDriverRepository() {
        return DriverRepository.getInstance();
    }
}
