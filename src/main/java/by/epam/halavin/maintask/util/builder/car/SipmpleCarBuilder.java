package by.epam.halavin.maintask.util.builder.car;

import by.epam.halavin.maintask.bean.Car;

import java.util.Date;

public class SipmpleCarBuilder implements CarBuilder {
    private Car car;

    {
        car = new Car();
    }

    public SipmpleCarBuilder setId(int id) {
        car.setId(id);
        return this;
    }

    public SipmpleCarBuilder setCarName(String name) {
        car.setName(name);
        return this;
    }

    public SipmpleCarBuilder setCarNumber(String number) {
        car.setNumber(number);
        return this;
    }

    public SipmpleCarBuilder setCarCheckupEnd(Date date) {
        car.setDate(date);
        return this;
    }

    @Override
    public Car getObject() {
        return car;
    }

    public void reset() {
        car = new Car();
    }
}
