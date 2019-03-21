package by.epam.halavin.maintask.dao.repository;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.service.order.DefaultOrderDispatcher;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Repository keeps order dispatchers
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public class DispatcherRepository {
    private static DispatcherRepository instance = new DispatcherRepository();
    private final Lock lock = new ReentrantLock();
    private final Lock passLock = new ReentrantLock();
    private Set<DefaultOrderDispatcher> set = new HashSet<>();

    private DispatcherRepository() {
    }

    /**
     * Function to get repository reference
     *
     * @return
     */
    public static DispatcherRepository getInstance() {
        return instance;
    }

    /**
     * Function to get driver's current order dispatcher, if it exists.
     *
     * @param driver
     * @return
     */
    public DefaultOrderDispatcher driverCheck(Driver driver) {
        lock.lock();

        Driver drv;
        DefaultOrderDispatcher orderDispatcher = null;
        DefaultOrderDispatcher driverOrderDispatcher = null;
        Iterator<DefaultOrderDispatcher> iterator = set.iterator();

        while (iterator.hasNext()) {
            orderDispatcher = iterator.next();
            drv = orderDispatcher.getOrder().getDriver();

            if (drv != null && drv.getId() == driver.getId()) {
                driverOrderDispatcher = orderDispatcher;
                break;
            }
        }

        lock.unlock();
        return driverOrderDispatcher;
    }

    /**
     * Function to get passenger's order dispatcher, if it exists
     *
     * @param passenger
     * @return
     */
    public DefaultOrderDispatcher getOrderDispatcher(Passenger passenger) {
        passLock.lock();
        Passenger pass;
        DefaultOrderDispatcher orderDispatcher = null;
        Iterator<DefaultOrderDispatcher> iterator = set.iterator();

        while (iterator.hasNext()) {
            orderDispatcher = iterator.next();
            pass = orderDispatcher.getOrder().getPassenger();

            if (pass.getId() == passenger.getId()) {
                break;
            }
        }

        passLock.unlock();
        return orderDispatcher;
    }

    /**
     * Remove from depository method for passenger's order dispatcher
     *
     * @param passenger
     */
    public void removeOrderDispatcher(Passenger passenger) {
        lock.lock();
        Passenger pass;
        DefaultOrderDispatcher orderDispatcher = null;
        Iterator<DefaultOrderDispatcher> iterator = set.iterator();

        while (iterator.hasNext()) {
            orderDispatcher = iterator.next();
            pass = orderDispatcher.getOrder().getPassenger();

            if (pass.getId() == passenger.getId()) {
                set.remove(orderDispatcher);
                break;
            }
        }
        lock.unlock();
    }

    /**
     * Function to add order dispatcher to repository
     *
     * @param orderDispatcher
     */
    public void addOrderDispatcher(DefaultOrderDispatcher orderDispatcher) {
        lock.lock();

        set.add(orderDispatcher);

        lock.unlock();
    }
}
