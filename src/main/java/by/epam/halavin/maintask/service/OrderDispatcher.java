package by.epam.halavin.maintask.service;

import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.bean.geocoding.Point;
import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.controller.info.StringAttributes;
import by.epam.halavin.maintask.dao.DAOFactory;
import by.epam.halavin.maintask.dao.OrderManager;
import by.epam.halavin.maintask.dao.edited.impl.PassengerBonusUpdate;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.repository.DriverRepository;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.geocoding.impl.DistanceSample;
import by.epam.halavin.maintask.service.geocoding.impl.GeocodingSample;
import by.epam.halavin.maintask.service.validate.Validator;
import by.epam.halavin.maintask.service.validate.impl.PassengerValidator;
import org.json.JSONException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class OrderDispatcher {
    private static final double START_PRICE = 2;
    private static final double COST_PER_KM = 1.65;
    private Order order;
    private String orderStatus;
    private String driverStatus;

    public Order getOrder() {
        return order;
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
                System.out.println(distance);
                if (minDistance > distance) {
                    nearestDriver = driver;
                    minDistance = distance;
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                throw new ServiceException(e);
            }
        }

        return nearestDriver;
    }

    public void makeOrder(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        GeocodingSample geocodingSample = new GeocodingSample();
        HttpSession session = request.getSession();
        initOrder(request, response);
        Driver driver = null;
        Validator validator = new PassengerValidator();

        if (validator.check(request)) {
            if (DriverRepository.getInstance().getFreeDrivers().values().size() != 0) {
                while (driver == null) {
                    try {
                        if (DriverRepository.getInstance().getFreeDrivers().values().size() == 0) {
                            orderStatus = StringAttributes.NO_AVAILABLE_DRIVERS.getName();
                            session.setAttribute(StringAttributes.ORDER_STATUS.getName(), orderStatus);
                            session.setAttribute(StringAttributes.ORDER.getName(), order);
                            return;
                        }
                        driver = searchDriver(geocodingSample.getPoint(
                                request.getParameter(StringAttributes.CURRENT_POSITION.getName())));
                        driver = DriverRepository.getInstance().takeDriver(driver);

                        order.setDate(new Date());
                        order.setDriver(driver);
                    } catch (IOException | JSONException e) {
                        throw new ServiceException(e);
                    }
                }

                orderStatus = StringAttributes.WAIT.getName();
                session.setAttribute(StringAttributes.ORDER_STATUS.getName(), orderStatus);
                session.setAttribute(StringAttributes.ORDER.getName(), order);
            } else {

                orderStatus = StringAttributes.NO_AVAILABLE_DRIVERS.getName();
                session.setAttribute(StringAttributes.ORDER_STATUS.getName(), orderStatus);
                session.setAttribute(StringAttributes.ORDER.getName(), order);
            }
        }
    }

    public void refresh(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String orderStatus = (String) session.getAttribute(StringAttributes.ORDER_STATUS.getName());

        if (!this.orderStatus.equals(orderStatus)) {
            session.setAttribute(StringAttributes.ORDER_STATUS.getName(), this.orderStatus);
            session.setAttribute(StringAttributes.ORDER.getName(), order);
        }
        if (this.orderStatus.equals(StringAttributes.NO_AVAILABLE_DRIVERS.getName())) {
            try {
                makeOrder(request, response);
            } catch (ServiceException e) {
                throw new ServiceException(e);
            }
        }

    }

    public void acceptOrder(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(StringAttributes.ORDER.getName(), order);
        orderStatus = StringAttributes.ACCEPT.getName();
        driverStatus = StringAttributes.ON_WAY.getName();
        session.setAttribute(StringAttributes.DRIVER_STATUS.getName(), driverStatus);
        session.setAttribute(StringAttributes.ACCOUNT.getName(), order.getDriver());

        if (orderStatus.equals(StringAttributes.RESET.getName())) {
            session.setAttribute(StringAttributes.ORDER.getName(), null);
            driverStatus = StringAttributes.ACTIVATE.getName();
            session.setAttribute(StringAttributes.DRIVER_STATUS.getName(), driverStatus);
        }
    }

    public void declineOrder(HttpServletRequest request, HttpServletResponse response) {
        DriverRepository.getInstance().freeDriver(order.getDriver());
        HttpSession session = request.getSession();

        session.setAttribute(StringAttributes.ACCOUNT.getName(), order.getDriver());
        session.setAttribute(StringAttributes.ORDER.getName(), null);
        orderStatus = StringAttributes.DECLINE.getName();
        driverStatus = StringAttributes.ACTIVATE.getName();
        session.setAttribute(StringAttributes.DRIVER_STATUS.getName(), driverStatus);
        order.setDriver(null);
    }

    public void initOrder(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        DistanceSample sample = new DistanceSample();
        GeocodingSample geocodingSample = new GeocodingSample();

        try {
            order = new Order();
            order.setPassenger((Passenger) session.getAttribute(StringAttributes.ACCOUNT.getName()));
            order.setOrderId(order.getPassenger().getId());
            order.setDeparturePoint(request.getParameter(StringAttributes.CURRENT_POSITION.getName()));
            order.setArrivalPoint(request.getParameter(StringAttributes.NEXT_POSITION.getName()));
            double cost = (double) Math.round((START_PRICE + COST_PER_KM * sample.getDistance(
                    geocodingSample.getPoint(request.getParameter(StringAttributes.CURRENT_POSITION.getName())),
                    geocodingSample.getPoint(request.getParameter(StringAttributes.NEXT_POSITION.getName())))) * 100d)
                    / 100d;

            order.setCost(cost);
        } catch (JSONException | IOException e) {
            throw new ServiceException(e);
        }
    }

    public void resetOrder(HttpServletRequest request) {
        DriverRepository.getInstance().freeDriver(order.getDriver());
        HttpSession session = request.getSession();
        session.setAttribute(StringAttributes.ORDER_STATUS.getName(), null);
        session.setAttribute(StringAttributes.ORDER.getName(), null);
    }

    public void orderPayment(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        orderStatus = null;
        session.setAttribute(StringAttributes.ORDER.getName(), null);
        session.setAttribute(StringAttributes.DRIVER_STATUS.getName(), StringAttributes.ACTIVATE.getName());
        PassengerBonusUpdate update = new PassengerBonusUpdate();
        OrderManager orderManager = new OrderManager();

        try {
            update.edit(order);
            orderManager.saveOrder(order);
            DriverRepository.getInstance().freeDriver(order.getDriver());
            session.setAttribute(StringAttributes.ORDER_STATUS.getName(), null);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        DispatcherBridge bridge = new DispatcherBridge();
        bridge.removerOrderDispatcher(order.getPassenger());
    }


    @Override
    public String toString() {
        return "OrderDispatcher{" +
                "order=" + order +
                ", orderStatus='" + orderStatus + '\'' +
                ", driverStatus='" + driverStatus + '\'' +
                '}';
    }
}
