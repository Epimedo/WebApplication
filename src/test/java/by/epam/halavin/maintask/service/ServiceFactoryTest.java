package by.epam.halavin.maintask.service;

import org.junit.Test;

public class ServiceFactoryTest {

    @Test
    public void createOrderDispatcher() {
        OrderDispatcher dispatcher = ServiceFactory.getInstance().createOrderDispatcher();

    }
}