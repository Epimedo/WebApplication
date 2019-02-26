package by.epam.halavin.maintask.util.builder.user;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.User;

public class DriverBuilder implements UserBuilder {
    private Driver driver;

    {
        driver = new Driver();
    }

    public DriverBuilder setId(int id) {
        driver.setId(id);
        return this;
    }

    public DriverBuilder setName(String name) {
        driver.setName(name);
        return this;
    }

    public DriverBuilder setSurname(String surname) {
        driver.setSurname(surname);
        return this;
    }

    public DriverBuilder setEmail(String email) {
        driver.setEmail(email);
        return this;
    }

    public DriverBuilder setTel(String tel) {
        driver.setTel(tel);
        return this;
    }

    public DriverBuilder setPassword(String password) {
        driver.setPassword(password);
        return this;
    }

    public DriverBuilder setStatus(String status) {
        driver.setStatus(status);
        return this;
    }

    public DriverBuilder setCarName(String carName) {
        driver.setCarName(carName);
        return this;
    }

    public DriverBuilder setCarNumber(String carNumber) {
        driver.setCarNumber(carNumber);
        return this;
    }

    @Override
    public User getObject() {
        return driver;
    }

    @Override
    public void reset() {
        driver = new Driver();
    }
}
