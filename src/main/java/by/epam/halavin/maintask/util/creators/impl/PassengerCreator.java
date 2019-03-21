package by.epam.halavin.maintask.util.creators.impl;

import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.util.builder.user.PassengerBuilder;
import by.epam.halavin.maintask.util.creators.UserCreator;

import javax.servlet.http.HttpServletRequest;

/**
 * Passenger creator
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public class PassengerCreator extends UserCreator {

    {
        userBuilder = new PassengerBuilder();
        user = new Passenger();
    }

    /**
     * Function gets http request nad return new initialized passenger object
     *
     * @param request
     * @return
     */
    @Override
    public User create(HttpServletRequest request) {
        String name = request.getParameter(Attributes.NAME.getName());
        String surname = request.getParameter(Attributes.SURNAME.getName());
        String email = request.getParameter(Attributes.EMAIL.getName());
        String tel = request.getParameter(Attributes.TEL.getName());
        String password = request.getParameter(Attributes.PASSWORD.getName());

        PassengerBuilder passengerBuilder = (PassengerBuilder) userBuilder;
        user = passengerBuilder.setName(name).setSurname(surname).setEmail(email).setTel(tel).
                setPassword(password).getObject();

        return user;
    }
}
