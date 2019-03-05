package by.epam.halavin.maintask.service;

import by.epam.halavin.maintask.service.order.DefaultOrderDispatcher;
import org.junit.Test;

public class ServiceFactoryTest {

    @Test
    public void createOrderDispatcher() {
        DefaultOrderDispatcher dispatcher = ServiceFactory.getInstance().createOrderDispatcher();

    }
}