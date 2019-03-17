package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.controller.info.Urls;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.bridge.DispatcherBridge;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.order.DefaultOrderDispatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewDriverSearch implements Command {
    public static final Logger log = LogManager.getLogger(NewDriverSearch.class);
    private final String addUrl = "?command=GO_TO_ORDER";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServiceFactory factory = ServiceFactory.getInstance();
        DispatcherBridge dispatcherBridge = factory.createDispatcherBrigde();
        DefaultOrderDispatcher orderDispatcher = factory.createOrderDispatcher();

        try {
            orderDispatcher.makeOrder((Passenger) session.getAttribute(Attributes.ACCOUNT.getName()),
                    request.getParameter(Attributes.CURRENT_POSITION.getName()),
                    request.getParameter(Attributes.NEXT_POSITION.getName()));
            session.setAttribute(Attributes.ORDER_STATUS.getName(), orderDispatcher.getOrderStatus());
            session.setAttribute(Attributes.ORDER.getName(), orderDispatcher.getOrder());
            dispatcherBridge.addOrderDispatcher(orderDispatcher);
        } catch (ServiceException e) {
            log.error(e.getMessage());
        }

        String page = Urls.ORDER.getName() + addUrl;

        response.sendRedirect(page);
    }
}
