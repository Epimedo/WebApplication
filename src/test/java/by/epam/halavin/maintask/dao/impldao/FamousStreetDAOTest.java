package by.epam.halavin.maintask.dao.impldao;

import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.street.FamousStreetDAO;
import by.epam.halavin.maintask.dao.street.StreetDAO;
import org.junit.Assert;
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
    public void findSuitableStreet() throws DAOException {
        String expected = "ангарская 4";
        String name = "ан";
        Assert.assertEquals(streetDAO.findSuitableStreet(name), expected);
    }
}