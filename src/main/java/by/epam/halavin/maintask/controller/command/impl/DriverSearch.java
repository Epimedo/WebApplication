package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.controller.info.Urls;
import by.epam.halavin.maintask.service.AccountTypes;
import by.epam.halavin.maintask.service.bridge.DispatcherBridge;
import by.epam.halavin.maintask.service.order.DefaultOrderDispatcher;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.order.OrderDispatcher;
import by.epam.halavin.maintask.service.validate.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DriverSearch implements Command {
    public static final Logger log = LogManager.getLogger(DriverSearch.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServiceFactory factory = ServiceFactory.getInstance();
        DispatcherBridge dispatcherBridge = factory.createDispatcherBrigde();
        Validator validator = factory.getUserValidator(AccountTypes.PASSENGER.toString());
        OrderDispatcher orderDispatcher = dispatcherBridge.
                getOrderDispatcher((Passenger) session.getAttribute(Attributes.ACCOUNT.getName()));

        try {
            if (!validator.isBan((Passenger) session.getAttribute(Attributes.ACCOUNT.getName()))) {
                if (orderDispatcher == null) {
                    orderDispatcher = ServiceFactory.getInstance().createOrderDispatcher();
                    orderDispatcher.makeOrder((Passenger) session.getAttribute(Attributes.ACCOUNT.getName()),
                            request.getParameter(Attributes.CURRENT_POSITION.getName()),
                            request.getParameter(Attributes.NEXT_POSITION.getName()));
                    dispatcherBridge.addOrderDispatcher(orderDispatcher);
                } else {
                    if (orderDispatcher.getOrderStatus().equals(Attributes.NO_AVAILABLE_DRIVERS.getName())) {
                        orderDispatcher.makeOrder((Passenger) session.getAttribute(Attributes.ACCOUNT.getName()),
                                request.getParameter(Attributes.CURRENT_POSITION.getName()),
                                request.getParameter(Attributes.NEXT_POSITION.getName()));
                    }

                }
                session.setAttribute(Attributes.ORDER_STATUS.getName(), orderDispatcher.getOrderStatus());
                session.setAttribute(Attributes.ORDER.getName(), orderDispatcher.getOrder());
            } else {
                session.setAttribute(Attributes.ORDER_STATUS.getName(), Attributes.ACCOUNT_BAN.getName());
            }
        } catch (ServiceException e) {
            log.error(e.getMessage());
        }

        String page = Urls.ORDER.getName() + "?command=GO_TO_ORDER";
        response.sendRedirect(page);
    }
}
