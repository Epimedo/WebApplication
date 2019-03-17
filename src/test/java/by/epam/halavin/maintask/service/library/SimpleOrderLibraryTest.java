package by.epam.halavin.maintask.service.library;

import by.epam.halavin.maintask.service.exception.ServiceException;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleOrderLibraryTest {

    @Test
    public void getUserOrdersIndexOf() {
    }

    @Test
    public void orderCount() throws ServiceException {
        SimpleOrderLibrary simpleOrderLibrary = new SimpleOrderLibrary();
        System.out.println(simpleOrderLibrary.orderCount(1));
        System.out.println(simpleOrderLibrary.getUserBlocks(1));
    }

    @Test
    public void findOrder() {
    }
}