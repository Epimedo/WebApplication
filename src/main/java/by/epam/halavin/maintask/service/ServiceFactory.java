package by.epam.halavin.maintask.service;

import by.epam.halavin.maintask.service.bridge.DispatcherBridge;
import by.epam.halavin.maintask.service.bridge.SimpleDispatcherBridge;
import by.epam.halavin.maintask.service.driver.DriverManager;
import by.epam.halavin.maintask.service.library.*;
import by.epam.halavin.maintask.service.order.DefaultOrderDispatcher;
import by.epam.halavin.maintask.service.user.AdminService;
import by.epam.halavin.maintask.service.user.DriverService;
import by.epam.halavin.maintask.service.user.PassengerService;
import by.epam.halavin.maintask.service.user.UserService;
import by.epam.halavin.maintask.service.validate.Validator;
import by.epam.halavin.maintask.service.validate.impl.AdminValidator;
import by.epam.halavin.maintask.service.validate.impl.DriverValidator;
import by.epam.halavin.maintask.service.validate.impl.PassengerValidator;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
    private static ServiceFactory instance = new ServiceFactory();
    private final UserService passService = new PassengerService();
    private final UserService driverService = new DriverService();
    private final UserService adminService = new AdminService();
    private final Validator passValidator = new PassengerValidator();
    private final Validator driverValidator = new DriverValidator();
    private final Validator adminValidator = new AdminValidator();
    private final UserLibrary passLibrary = new PassengerLibrary();
    private final UserLibrary driverLibrary = new DriverLibrary();
    private final OrderLibrary orderLibrary = new SimpleOrderLibrary();

    private Map<AccountTypes, UserService> userMap = new HashMap<>();
    private Map<AccountTypes, Validator> validatorMap = new HashMap<>();

    {
        userMap.put(AccountTypes.PASSENGER, passService);
        userMap.put(AccountTypes.DRIVER, driverService);
        userMap.put(AccountTypes.ADMIN, adminService);
        validatorMap.put(AccountTypes.PASSENGER, passValidator);
        validatorMap.put(AccountTypes.ADMIN, adminValidator);
        validatorMap.put(AccountTypes.DRIVER, driverValidator);
    }

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserLibrary getPassLibrary() {
        return passLibrary;
    }

    public UserLibrary getDriverLibrary() {
        return driverLibrary;
    }

    public OrderLibrary getOrderLibrary() {
        return orderLibrary;
    }

    public UserService getUserService(String accountType) {
        return userMap.get(AccountTypes.valueOf(accountType.toUpperCase()));
    }

    public Validator getUserValidator(String accountType) {
        return validatorMap.get(AccountTypes.valueOf(accountType.toUpperCase()));
    }

    public DefaultOrderDispatcher createOrderDispatcher() {
        return new DefaultOrderDispatcher();
    }

    public DriverManager createDriverManager() {
        return new DriverManager();
    }

    public DispatcherBridge createDispatcherBrigde() {
        return new SimpleDispatcherBridge();
    }


}
