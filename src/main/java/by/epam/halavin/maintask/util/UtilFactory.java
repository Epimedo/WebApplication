package by.epam.halavin.maintask.util;

import by.epam.halavin.maintask.util.creators.UserCreator;
import by.epam.halavin.maintask.util.creators.impl.DriverCreator;
import by.epam.halavin.maintask.util.creators.impl.PassengerCreator;

public class UtilFactory {
    private static UtilFactory instance = new UtilFactory();
    private final UserCreator passCreator = new PassengerCreator();
    private final UserCreator driverCreator = new DriverCreator();

    private UtilFactory() {
        super();
    }

    public static UtilFactory getInstance() {
        return instance;
    }

    public UserCreator getPassCreator() {
        return passCreator;
    }

    public UserCreator getDriverCreator() {
        return driverCreator;
    }
}
