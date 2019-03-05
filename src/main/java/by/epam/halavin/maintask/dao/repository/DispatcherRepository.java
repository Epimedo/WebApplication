package by.epam.halavin.maintask.dao.repository;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.service.order.DefaultOrderDispatcher;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DispatcherRepository {
    private static DispatcherRepository instance = new DispatcherRepository();
    private Set<DefaultOrderDispatcher> set = new HashSet<>();

    private DispatcherRepository() {
    }

    public static DispatcherRepository getInstance() {
        return instance;
    }

    public DefaultOrderDispatcher driverCheck(Driver driver) {
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

        return driverOrderDispatcher;
    }

    public DefaultOrderDispatcher getOrderDispatcher(Passenger passenger) {
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

        return orderDispatcher;
    }

    public void removeOrderDispatcher(Passenger passenger) {
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

    }

    public void addOrderDispatcher(DefaultOrderDispatcher orderDispatcher) {
        set.add(orderDispatcher);
    }
}
