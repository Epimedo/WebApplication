package by.epam.halavin.maintask.dao.street;

import by.epam.halavin.maintask.dao.exception.DAOException;
import org.junit.Test;

public class FamousStreetDAOTest {
    private final StreetDAO streetDAO = new FamousStreetDAO();

    @Test
    public void getStreet() throws DAOException {
        String name = "ангарская 4";
        System.out.println(streetDAO.getStreet(name));
    }

    @Test
    public void addStreet() throws DAOException {
        String name = "филимонова 53";
        streetDAO.addStreet(name);
        System.out.println(streetDAO.getStreet(name));
    }

    @Test
    public void findSuitableStreet() throws DAOException{
        String name = "ан";
        System.out.println(streetDAO.findSuitableStreet(name));
    }
}