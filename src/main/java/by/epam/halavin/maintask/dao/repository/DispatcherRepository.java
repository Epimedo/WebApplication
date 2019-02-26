package by.epam.halavin.maintask.dao.repository;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.service.OrderDispatcher;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DispatcherRepository {
    private static DispatcherRepository instance = new DispatcherRepository();
    private Set<OrderDispatcher> set = new HashSet<>();

    private DispatcherRepository() {
    }

    public static DispatcherRepository getInstance() {
        return instance;
    }

    public OrderDispatcher driverCheck(Driver driver) {
        Driver drv;
        OrderDispatcher orderDispatcher = null;
        OrderDispatcher driverOrderDispatcher = null;
        Iterator<OrderDispatcher> iterator = set.iterator();

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

    public OrderDispatcher getOrderDispatcher(Passenger passenger) {
        Passenger pass;
        OrderDispatcher orderDispatcher = null;
        Iterator<OrderDispatcher> iterator = set.iterator();

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
        OrderDispatcher orderDispatcher = null;
        Iterator<OrderDispatcher> iterator = set.iterator();

        while (iterator.hasNext()) {
            orderDispatcher = iterator.next();
            pass = orderDispatcher.getOrder().getPassenger();

            if (pass.getId() == passenger.getId()) {
                set.remove(orderDispatcher);
                break;
            }
        }

    }

    public void addOrderDispatcher(OrderDispatcher orderDispatcher) {
        set.add(orderDispatcher);
    }
}
