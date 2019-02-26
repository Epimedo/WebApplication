package by.epam.halavin.maintask.service;

import by.epam.halavin.maintask.service.admin.AdminManager;
import by.epam.halavin.maintask.service.driver.DriverManager;
import by.epam.halavin.maintask.service.login.UserManager;

public class ServiceFactory {
    private static ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserManager createUserManager() {
        return new UserManager();
    }

    public AdminManager createAdminManager() {
        return new AdminManager();
    }

    public OrderDispatcher createOrderDispatcher() {
        return new OrderDispatcher();
    }

    public DriverManager createDriverManager() {
        return new DriverManager();
    }

    public DispatcherBridge createDispatcherBrigde() {
        return new DispatcherBridge();
    }


}
