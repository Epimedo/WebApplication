package by.epam.halavin.maintask.util.creators.impl;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.util.UtilException;
import by.epam.halavin.maintask.util.builder.car.SipmpleCarBuilder;
import by.epam.halavin.maintask.util.builder.user.DriverBuilder;
import by.epam.halavin.maintask.util.creators.UserCreator;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Driver creator
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public class DriverCreator extends UserCreator {
    private final String DEFAULT_STATUS = "ban";
    private final String parsePattern = "yyyy-mm-dd";

    {
        userBuilder = new DriverBuilder();
        user = new Driver();
    }

    /**
     * Function gets http request and return new initialized driver object.
     *
     * @param request
     * @return - driver
     * @throws UtilException
     */
    @Override
    public User create(HttpServletRequest request) throws UtilException {
        String name = request.getParameter(Attributes.NAME.getName());
        String surname = request.getParameter(Attributes.SURNAME.getName());
        String email = request.getParameter(Attributes.EMAIL.getName());
        String tel = request.getParameter(Attributes.TEL.getName());
        String password = request.getParameter(Attributes.PASSWORD.getName());
        String car = request.getParameter(Attributes.CAR.getName());
        String carNumber = request.getParameter(Attributes.CAR_NUMBER.getName());

        SipmpleCarBuilder sipmpleCarBuilder = new SipmpleCarBuilder();
        DateFormat format = new SimpleDateFormat(parsePattern, Locale.ENGLISH);
        try {
            Date date = format.parse(request.getParameter(Attributes.CHECKUP_END.getName()));
            sipmpleCarBuilder.setCarNumber(carNumber).setCarName(car).setCarCheckupEnd(date);
        } catch (ParseException e) {
            throw new UtilException(e);
        }

        DriverBuilder driverBuilder = (DriverBuilder) userBuilder;
        driverBuilder.setName(name).setSurname(surname).setTel(tel).setEmail(email).setPassword(password).
                setCarName(car).setStatus(DEFAULT_STATUS).setCarNumber(carNumber).setCar(sipmpleCarBuilder.getObject());

        return driverBuilder.getObject();
    }
}
