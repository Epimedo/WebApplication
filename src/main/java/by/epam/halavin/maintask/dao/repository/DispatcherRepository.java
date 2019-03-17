package by.epam.halavin.maintask.dao.repository;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.service.order.DefaultOrderDispatcher;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DispatcherRepository {
    private static DispatcherRepository instance = new DispatcherRepository();
    private final Lock lock = new ReentrantLock();
    private final Lock passLock = new ReentrantLock();
    private Set<DefaultOrderDispatcher> set = new HashSet<>();

    private DispatcherRepository() {
    }

    public static DispatcherRepository getInstance() {
        return instance;
    }

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

    public void addOrderDispatcher(DefaultOrderDispatcher orderDispatcher) {
        lock.lock();

        set.add(orderDispatcher);

        lock.unlock();
    }
}
