package by.epam.halavin.maintask.util.builder;

import by.epam.halavin.maintask.bean.Car;

import java.util.Date;

public class CarBuilder {
    private Car car;

    {
        car = new Car();
    }

    public CarBuilder setId(int id) {
        car.setId(id);
        return this;
    }

    public CarBuilder setCarName(String name) {
        car.setName(name);
        return this;
    }

    public CarBuilder setCarNumber(String number) {
        car.setNumber(number);
        return this;
    }

    public CarBuilder setCarCheckupEnd(Date date) {
        car.setDate(date);
        return this;
    }

    public Car getObject() {
        return car;
    }

    public void reset() {
        car = new Car();
    }
}
