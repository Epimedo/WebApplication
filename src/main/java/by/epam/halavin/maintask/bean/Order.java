package by.epam.halavin.maintask.bean;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.Passenger;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Class for order's information
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 12;
    private int orderId;
    private Passenger passenger;
    private Driver driver;
    private Date date;
    private String arrivalPoint;
    private String departurePoint;
    private double cost;

    /**
     * Default constructor
     */
    public Order() {
        super();
    }

    /**
     * Function to get field value{@link Order#orderId}
     *
     * @return - order's id
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Id determination function {@link Order#orderId}
     *
     * @param orderId - Id
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Function to get field value {@link Order#driver}
     *
     * @return - order's driver
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * Driver determination function{@link Order#driver}
     *
     * @param driver
     */
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    /**
     * Function to get field value {@link Order#passenger}
     *
     * @return - order's passenger
     */
    public Passenger getPassenger() {
        return passenger;
    }

    /**
     * Passenger determination function {@link Order#passenger}
     *
     * @param passenger
     */
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    /**
     * Function to get field value {@link Order#date}
     *
     * @return - order's date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Date determination function {@link Order#date}
     *
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Function to get field value{@link Order#arrivalPoint}
     *
     * @return - arrival point
     */
    public String getArrivalPoint() {
        return arrivalPoint;
    }

    /**
     * Arrival point determination function{@link Order#arrivalPoint}
     *
     * @param arrivalPoint
     */
    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }

    /**
     * Function to get field value {@link Order#departurePoint}
     *
     * @return - departure point
     */
    public String getDeparturePoint() {
        return departurePoint;
    }

    /**
     * Departure point determination function{@link Order#departurePoint}
     *
     * @param departurePoint
     */
    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    /**
     * Function to get field value{@link Order#cost}
     *
     * @return - order's cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Cost determination{@link Order#cost}
     *
     * @param cost
     */
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
