package by.epam.halavin.maintask.bean.user;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.bean.geocoding.Point;

import java.util.Objects;

/**
 * Driver account object
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public class Driver extends User {
    private String status = "";
    private String carName = "";
    private String carNumber = "";
    private Car car;
    private Point currentPoint = new Point();

    public Driver() {
        super();
    }

    /**
     * Function to get field value {@link Driver#currentPoint}
     *
     * @return - current point
     */
    public Point getCurrentPoint() {
        return currentPoint;
    }

    /**
     * Current point determination
     *
     * @param currentPoint
     */
    public void setCurrentPoint(Point currentPoint) {
        this.currentPoint = currentPoint;
    }

    /**
     * Function to get field value {@link Driver#car}
     *
     * @return - driver's car
     */
    public Car getCar() {
        return car;
    }

    /**
     * Car determination {@link Driver#car}
     *
     * @param car
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Function to get field value {@link Driver#carName}
     *
     * @return - car name
     */
    public String getCarName() {
        return carName;
    }

    /**
     * Car name determination {@link Driver#carName}
     *
     * @param carName
     */
    public void setCarName(String carName) {
        this.carName = carName;
    }

    /**
     * Function to get field value {@link Driver#carNumber}
     *
     * @return - car number
     */
    public String getCarNumber() {
        return carNumber;
    }

    /**
     * Car number determination {@link Driver#carNumber}
     *
     * @param carNumber
     */
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    /**
     * Function to get field value {@link Driver#status}
     *
     * @return - status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Status determination {@link Driver#status}
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        boolean bool = false;

        if (this == o) {
            bool = true;
        } else {
            if (o == null || getClass() != o.getClass()) {
                bool = false;
            } else {
                Driver driver = (Driver) o;

                if (super.equals(o) && carName.equals(driver.carName) &&
                        carNumber.equals(driver.carNumber) && status.equals(driver.status) &&
                        currentPoint.equals(driver.currentPoint) && car.equals(driver.car)) {
                    bool = true;
                }
            }
        }

        return bool;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), status, carName, carNumber, currentPoint, car);
    }


    @Override
    public String toString() {
        return "{" +
                "status='" + status + '\'' +
                "car='" + car + '\'' +
                "carName='" + carName + '\'' +
                ", carNumber='" + carNumber + '\'' +
                "currentPoint='" + currentPoint + '\'' +
                "}\n" + super.toString();
    }
}
