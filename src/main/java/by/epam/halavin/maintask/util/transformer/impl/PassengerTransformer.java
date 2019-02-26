package by.epam.halavin.maintask.util.transformer.impl;

import by.epam.halavin.maintask.util.transformer.ListTransfrmer;

public class PassengerTransformer<User, Passenger> extends ListTransfrmer<User, Passenger> {

    @Override
    public Passenger transform(User o) {
        Passenger passenger = (Passenger) o;
        return passenger;
    }
}
