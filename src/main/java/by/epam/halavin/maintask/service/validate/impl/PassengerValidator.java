package by.epam.halavin.maintask.service.validate.impl;

import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.controller.info.StringAttributes;
import by.epam.halavin.maintask.service.DispatcherBridge;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.validate.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PassengerValidator implements Validator {

    @Override
    public boolean check(HttpServletRequest request) throws ServiceException {
        boolean bool = true;
        Passenger passenger = null;
        HttpSession session = request.getSession();
        DispatcherBridge bridge = ServiceFactory.getInstance().createDispatcherBrigde();
        passenger = (Passenger) session.getAttribute(StringAttributes.ACCOUNT.getName());

        if (passenger.getStatus().equals(StringAttributes.BAN.getName())) {
            bool = false;
            bridge.removerOrderDispatcher((Passenger) session.getAttribute(StringAttributes.ACCOUNT.getName()));
            session.setAttribute(StringAttributes.ORDER_STATUS.getName(), StringAttributes.ACCOUNT_BAN.getName());
        }

        return bool;
    }
}
