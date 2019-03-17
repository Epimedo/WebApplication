package by.epam.halavin.maintask.service.library;

import by.epam.halavin.maintask.dao.DAOFactory;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.street.StreetDAO;
import by.epam.halavin.maintask.service.exception.ServiceException;

public class DefaultStreetLibrary extends StreetLibrary {

    @Override
    public String findStreetLike(String namePart) throws ServiceException {
        String result = "";
        DAOFactory factory = DAOFactory.getInstance();
        StreetDAO streetDAO = factory.getStreetDAO();

        try {
            result = streetDAO.findSuitableStreet(namePart);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }
}
