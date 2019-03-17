package by.epam.halavin.maintask.bean.user;

import by.epam.halavin.maintask.bean.Car;
import by.epam.halavin.maintask.bean.geocoding.Point;

import java.util.Objects;

public class Driver extends User {
    private String status = "";
    private String carName = "";
    private String carNumber = "";
    private Car car;
    private Point currentPoint = new Point();

    public Driver() {
        super();
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Point currentPoint) {
        this.currentPoint = currentPoint;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getStatus() {
        return status;
    }

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
                        currentPoint.equals(driver.currentPoint) && Objects.equals(car, driver.car)) {
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
