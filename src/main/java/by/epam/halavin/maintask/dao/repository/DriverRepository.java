package by.epam.halavin.maintask.dao.repository;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.util.GeoGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Repository keeps active drivers and controls which of them are free/busy
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public class DriverRepository {
    private static DriverRepository instance = new DriverRepository();
    private final Lock lock = new ReentrantLock();
    private final Lock passengerLock = new ReentrantLock();
    private Map<Integer, Driver> freeDrivers = new HashMap<>();
    private Map<Integer, Driver> busyDrivers = new HashMap<>();
    private GeoGenerator geoGenerator = new GeoGenerator();

    private DriverRepository() {
        super();
    }

    /**
     * Function to get repository reference
     *
     * @return
     */
    public static DriverRepository getInstance() {
        return instance;
    }

    /**
     * Function to add driver object into free drivers list
     *
     * @param driver
     */
    public void addDriver(Driver driver) {
        lock.lock();
        if (!freeDrivers.containsKey(driver.getId())) {
            driver.setCurrentPoint(geoGenerator.generatePoint());
            freeDrivers.put(driver.getId(), driver);
        }
        lock.unlock();
    }

    /**
     * Function to remover driver from free drivers list
     *
     * @param driver
     */
    public void removeDriver(Driver driver) {
        lock.lock();
        freeDrivers.remove(driver.getId());
        lock.unlock();
    }

    /**
     * Function to return driver, which was took, to free drivers list
     *
     * @param driver
     */
    public void freeDriver(Driver driver) {
        passengerLock.lock();
        busyDrivers.remove(driver.getId());
        driver.setCurrentPoint(geoGenerator.generatePoint());
        freeDrivers.put(driver.getId(), driver);
        passengerLock.unlock();
    }

    /**
     * Function to take free driver form free drivers list
     *
     * @param driver
     * @return
     */
    public Driver takeDriver(Driver driver) {
        lock.lock();
        if (freeDrivers.remove(driver.getId()) == null) {
            driver = null;
        } else {
            busyDrivers.put(driver.getId(), driver);
        }
        lock.unlock();
        return driver;
    }

    /**
     * Funtciton to get list of free drivers
     *
     * @return free drivers
     */
    public Map<Integer, Driver> getFreeDrivers() {
        return freeDrivers;
    }
}
