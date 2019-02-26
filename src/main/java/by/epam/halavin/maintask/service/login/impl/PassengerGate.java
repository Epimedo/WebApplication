package by.epam.halavin.maintask.service.login.impl;

import by.epam.halavin.maintask.controller.info.StringAttributes;
import by.epam.halavin.maintask.dao.exception.DAOException;
import by.epam.halavin.maintask.dao.registration.UserRegister;
import by.epam.halavin.maintask.dao.registration.impl.PassengerRegister;
import by.epam.halavin.maintask.dao.signin.impl.PassengerSignIn;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.login.UserGate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;

public class PassengerGate implements UserGate {

    @Override
    public void registerIn(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserRegister register = new PassengerRegister();
        HttpSession session = request.getSession();

        try {
            session.setAttribute(StringAttributes.RESPONSE_STATUS.getName(), register.registerIn(request, response));
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void signIn(String email, String password, HttpSession session) throws ServiceException {
        PassengerSignIn passengerSignIn = new PassengerSignIn();

        try {
            session.setAttribute(StringAttributes.RESPONSE_STATUS.getName(), passengerSignIn.sign(email, password, session));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void signOut(HttpSession session) throws ServiceException {
        session.setAttribute(StringAttributes.RESPONSE_STATUS.getName(), "");
        Iterator<String> iterator = session.getAttributeNames().asIterator();

        while (iterator.hasNext()) {
            session.setAttribute(iterator.next(), null);
        }
    }
}
