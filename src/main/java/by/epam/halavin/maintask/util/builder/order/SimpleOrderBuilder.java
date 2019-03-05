package by.epam.halavin.maintask.util.builder.order;

import by.epam.halavin.maintask.bean.Order;
import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;

import java.util.Date;

public class SimpleOrderBuilder implements OrderBuilder {
    private Order order;

    {
        order = new Order();
    }

    public SimpleOrderBuilder setPassenger(Passenger passenger) {
        order.setPassenger(passenger);
        return this;
    }

    public SimpleOrderBuilder setDriver(Driver driver) {
        order.setDriver(driver);
        return this;
    }

    public SimpleOrderBuilder setDate(Date date) {
        order.setDate(date);
        return this;
    }

    public SimpleOrderBuilder setDepature(String depature) {
        order.setDeparturePoint(depature);
        return this;
    }

    public SimpleOrderBuilder setArrival(String arrival) {
        order.setArrivalPoint(arrival);
        return this;
    }

    public SimpleOrderBuilder setCost(double cost) {
        order.setCost(cost);
        return this;
    }

    public SimpleOrderBuilder setId(int id) {
        order.setOrderId(id);
        return this;
    }

    public void reset() {
        order = new Order();
    }

    @Override
    public Order getObject() {
        return order;
    }


}
