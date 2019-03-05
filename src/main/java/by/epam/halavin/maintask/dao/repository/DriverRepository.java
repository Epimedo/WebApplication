package by.epam.halavin.maintask.dao.repository;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.util.GeoGenerator;

import java.util.HashMap;
import java.util.Map;

public class DriverRepository {
    private static DriverRepository instance = new DriverRepository();
    private Map<Integer, Driver> freeDrivers = new HashMap<>();
    private Map<Integer, Driver> busyDrivers = new HashMap<>();
    private GeoGenerator geoGenerator = new GeoGenerator();

    private DriverRepository() {
        super();
    }

    public static DriverRepository getInstance() {
        return instance;
    }

    public synchronized void addDriver(Driver driver) {
        if (!freeDrivers.containsKey(driver.getId())) {
            driver.setCurrentPoint(geoGenerator.generatePoint());
            freeDrivers.put(driver.getId(), driver);
        }
    }

    public synchronized void removeDriver(Driver driver) {
        freeDrivers.remove(driver.getId());
    }

    public synchronized void freeDriver(Driver driver) {
        busyDrivers.remove(driver.getId());
        driver.setCurrentPoint(geoGenerator.generatePoint());
        freeDrivers.put(driver.getId(), driver);
    }

    public synchronized Driver takeDriver(Driver driver) {
        if (freeDrivers.remove(driver.getId()) == null) {
            driver = null;
        } else {
            busyDrivers.put(driver.getId(), driver);
        }
        return driver;
    }

    public Map<Integer, Driver> getFreeDrivers() {
        return freeDrivers;
    }
}
