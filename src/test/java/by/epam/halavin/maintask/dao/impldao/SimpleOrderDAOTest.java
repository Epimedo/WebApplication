package by.epam.halavin.maintask.dao.impldao;

import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.dao.order.OrderDAO;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.order.SimpleOrderDAO;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SimpleOrderDAOTest {
    private static OrderDAO orderDAO;

    @BeforeClass
    public static void init() {
        orderDAO = new SimpleOrderDAO();
    }

    @Test
    public void saveOrder() {
    }

    @Test
    public void getUserOrder() throws DAOException, ParseException {
        String string = "2019-01-21";
        DateFormat format = new SimpleDateFormat("yyyy-m-dd", Locale.ENGLISH);
        Date date = format.parse(string);

        System.out.println(date);
        System.out.println(new java.sql.Date(date.getTime()));
        int id = 3;

        Order order = orderDAO.getUserOrder(id, date);

        System.out.println(order);
    }

    @Test
    public void getUserOrderCount() throws DAOException{
        System.out.println(orderDAO.getUserOrderCount(1));
    }


    @Test
    public void getUserOrdersIndexOf() throws DAOException{
        System.out.println(orderDAO.getUserOrdersIndexOf(1,1,4));
    }
}