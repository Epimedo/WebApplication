package by.epam.halavin.maintask.service.order;

import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.bean.geocoding.Point;
import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.dao.DAOFactory;
import by.epam.halavin.maintask.dao.order.OrderDAO;
import by.epam.halavin.maintask.dao.user.UserDAO;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.repository.DriverRepository;
import by.epam.halavin.maintask.service.bridge.DispatcherBridge;
import by.epam.halavin.maintask.service.bridge.SimpleDispatcherBridge;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.geocoding.impl.DistanceSample;
import by.epam.halavin.maintask.service.geocoding.impl.GeocodingSample;
import org.json.JSONException;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DefaultOrderDispatcher implements OrderDispatcher {
    private static final double START_PRICE = 2;
    private static final double COST_PER_KM = 1.65;
    private final double PROCENT = .05;
    private Order order;
    private String orderStatus;
    private String driverStatus;

    @Override
    public Order getOrder() {
        return order;
    }

    @Override
    public String getOrderStatus() {
        return orderStatus;
    }

    @Override
    public String getDriverStatus() {
        return driverStatus;
    }

    public Driver searchDriver(Point passengerPosition) throws ServiceException {
        Set<Map.Entry<Integer, Driver>> set = DAOFactory.getInstance().getDriverRepository().getFreeDrivers().entrySet();
        Driver nearestDriver = null;
        double minDistance = Double.MAX_VALUE;
        DistanceSample sample = new DistanceSample();

        Iterator<Map.Entry<Integer, Driver>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next().getValue();

            try {
                double distance = sample.getDistance(passengerPosition, driver.getCurrentPoint());

                if (minDistance > distance) {
                    nearestDriver = driver;
                    minDistance = distance;
                }
            } catch (IOException | JSONException e) {
                throw new ServiceException(e);
            }
        }

        return nearestDriver;
    }

    @Override
    public void makeOrder(Passenger passenger, String currentPos, String nextPos) throws ServiceException {
        GeocodingSample geocodingSample = new GeocodingSample();
        initOrder(passenger, currentPos, nextPos);
        Driver driver = null;

        if (DriverRepository.getInstance().getFreeDrivers().values().size() != 0) {
            while (driver == null) {
                try {
                    if (DriverRepository.getInstance().getFreeDrivers().values().size() == 0) {
                        orderStatus = Attributes.NO_AVAILABLE_DRIVERS.getName();
                        return;
                    }
                    driver = searchDriver(geocodingSample.getPoint(currentPos));
                    driver = DriverRepository.getInstance().takeDriver(driver);

                    order.setDate(new Date());
                    order.setDriver(driver);
                } catch (IOException | JSONException e) {
                    throw new ServiceException(e);
                }
            }
            orderStatus = Attributes.WAIT.getName();
        } else {
            orderStatus = Attributes.NO_AVAILABLE_DRIVERS.getName();
        }
    }

    @Override
    public void acceptOrder() {
        orderStatus = Attributes.ACCEPT.getName();
        driverStatus = Attributes.ON_WAY.getName();

        if (orderStatus.equals(Attributes.RESET.getName())) {
            driverStatus = Attributes.ACTIVATE.getName();
        }
    }

    @Override
    public Driver declineOrder() {
        DriverRepository.getInstance().freeDriver(order.getDriver());
        Driver driver = order.getDriver();

        orderStatus = Attributes.DECLINE.getName();
        driverStatus = Attributes.ACTIVATE.getName();
        order.setDriver(null);

        return driver;
    }

    @Override
    public void initOrder(Passenger passenger, String currentPos, String nextPos) throws ServiceException {
        DistanceSample distanceSample = new DistanceSample();
        GeocodingSample geocodingSample = new GeocodingSample();

        try {
            order = new Order();
            order.setPassenger(passenger);
            order.setOrderId(order.getPassenger().getId());
            order.setDeparturePoint(currentPos);
            order.setArrivalPoint(nextPos);
            double cost = (double) Math.round((START_PRICE + COST_PER_KM * distanceSample.getDistance(
                    geocodingSample.getPoint(currentPos),
                    geocodingSample.getPoint(nextPos))) * 100d)
                    / 100d;

            order.setCost(cost);
        } catch (JSONException | IOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void resetOrder() {
        DriverRepository.getInstance().freeDriver(order.getDriver());
    }

    @Override
    public void orderPayment() throws ServiceException {
        orderStatus = null;
        DAOFactory factory = DAOFactory.getInstance();
        OrderDAO orderDAO = factory.getSimpleOrderDAO();
        UserDAO userDAO = factory.getPassDAO();
        DriverRepository driverRepository = factory.getDriverRepository();
        DispatcherBridge bridge = new SimpleDispatcherBridge();

        try {
            orderDAO.saveOrder(order);
            Passenger passenger = order.getPassenger();
            double cost = (double) Math.round((passenger.getBonus() + (order.getCost() * PROCENT)) * 100d)
                    / 100d;

            passenger.setBonus(passenger.getBonus() + cost);
            userDAO.userUpdate(passenger);
            driverRepository.freeDriver(order.getDriver());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        bridge.removeOrderDispatcher(order.getPassenger());
    }


    @Override
    public String toString() {
        return "DefaultOrderDispatcher{" +
                "order=" + order +
                ", orderStatus='" + orderStatus + '\'' +
                ", driverStatus='" + driverStatus + '\'' +
                '}';
    }
}
