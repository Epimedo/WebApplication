package by.epam.halavin.maintask.util.builder.user;

import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.bean.user.User;

public class PassengerBuilder implements UserBuilder {
    private Passenger passenger;

    {
        passenger = new Passenger();
    }

    public PassengerBuilder setId(int id) {
        passenger.setId(id);
        return this;
    }

    public PassengerBuilder setName(String name) {
        passenger.setName(name);
        return this;
    }

    public PassengerBuilder setSurname(String surName) {
        passenger.setSurname(surName);
        return this;
    }

    public PassengerBuilder setEmail(String email) {
        passenger.setEmail(email);
        return this;
    }

    public PassengerBuilder setPassword(String password) {
        passenger.setPassword(password);
        return this;
    }

    public PassengerBuilder setTel(String phone) {
        passenger.setTel(phone);
        return this;
    }

    public PassengerBuilder setStatus(String status) {
        passenger.setStatus(status);
        return this;
    }

    public PassengerBuilder setBonus(double bonus) {
        passenger.setBonus(bonus);
        return this;
    }

    public PassengerBuilder setDiscount(double discount) {
        passenger.setDiscount(discount);
        return this;
    }

    @Override
    public User getObject() {
        return passenger;
    }

    @Override
    public void reset() {
        passenger = new Passenger();
    }
}
