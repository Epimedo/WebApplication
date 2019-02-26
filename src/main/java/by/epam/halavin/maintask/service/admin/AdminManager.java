package by.epam.halavin.maintask.service.admin;

import by.epam.halavin.maintask.controller.info.StringAttributes;
import by.epam.halavin.maintask.dao.CarManager;
import by.epam.halavin.maintask.dao.DAOFactory;
import by.epam.halavin.maintask.dao.edited.Edited;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.repository.SimpleRepository;
import by.epam.halavin.maintask.service.ServiceListDispatcher;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.impl.ServiceSimpleListDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminManager {
    private ServiceListDispatcher passengerDis;
    private ServiceListDispatcher driverDis;
    private String driverBlocks = "driverBlocks";
    private String passengerBlocks = "passengerBlocks";

    public void initAdminTables(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        passengerDis = new ServiceSimpleListDispatcher(SimpleRepository.PASSENGER_REPOSITORY);
        driverDis = new ServiceSimpleListDispatcher(SimpleRepository.DRIVER_REPOSITORY);
        CarManager manager = DAOFactory.getInstance().createCarManager();

        try {
            session.setAttribute(driverBlocks, driverDis.getUserCount());
            session.setAttribute(passengerBlocks, passengerDis.getUserCount());
            session.setAttribute(StringAttributes.CARS.getName(), manager.getCars());
            session.setAttribute(StringAttributes.PASSENGERS.getName(), passengerDis.getNextUserListItems());
            session.setAttribute(StringAttributes.DRIVERS.getName(), driverDis.getNextUserListItems());
        } catch (ServiceException | DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void getPassengersIndexOf(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();

        try {
            session.setAttribute(StringAttributes.PASSENGERS.getName(),
                    passengerDis.getNumberOfUsers(
                            Integer.parseInt(request.getParameter(StringAttributes.BLOCK_NUMBER.getName()))));
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
    }

    public void getDriversIndexOf(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();

        try {
            session.setAttribute(StringAttributes.DRIVERS.getName(),
                    driverDis.getNumberOfUsers(
                            Integer.parseInt(request.getParameter(StringAttributes.BLOCK_NUMBER.getName()))));
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
    }

    public void nextPassengers(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();

        try {
            session.setAttribute(StringAttributes.PASSENGERS.getName(), passengerDis.getNextUserListItems());
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
    }

    public void nextDrivers(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();

        try {
            session.setAttribute(StringAttributes.DRIVERS.getName(), driverDis.getNextUserListItems());
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
    }

    public void editPassenger(HttpServletRequest request) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        Edited edited = factory.createPassengerEdited();
        HttpSession session = request.getSession();

        try {
            edited.edit(request);
            session.setAttribute(StringAttributes.PASSENGERS.getName(), passengerDis.getCurrentUserListItems());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void editDriver(HttpServletRequest request) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        Edited edited = factory.createDriverEdited();
        HttpSession session = request.getSession();

        try {
            edited.edit(request);
            session.setAttribute(StringAttributes.DRIVERS.getName(), driverDis.getCurrentUserListItems());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
