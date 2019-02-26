package by.epam.halavin.maintask.bean;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {
    private int orderId;
    private Passenger passenger;
    private Driver driver;
    private Date date;
    private String arrivalPoint;
    private String departurePoint;
    private double cost;

    public Order() {
        super();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getArrivalPoint() {
        return arrivalPoint;
    }

    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                Double.compare(order.cost, cost) == 0 &&
                Objects.equals(passenger, order.passenger) &&
                Objects.equals(date, order.date) &&
                Objects.equals(driver, order.driver) &&
                Objects.equals(arrivalPoint, order.arrivalPoint) &&
                Objects.equals(departurePoint, order.departurePoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, passenger, driver, date, arrivalPoint, departurePoint, cost);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", passenger=" + passenger +
                ", driver=" + driver +
                ", date=" + date +
                ", arrivalPoint='" + arrivalPoint + '\'' +
                ", departurePoint='" + departurePoint + '\'' +
                ", cost=" + cost +
                '}';
    }
}
