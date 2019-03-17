package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.controller.info.Urls;
import by.epam.halavin.maintask.service.bridge.DispatcherBridge;
import by.epam.halavin.maintask.service.order.DefaultOrderDispatcher;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.order.OrderDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Refresh implements Command {
    private final String addUrl = "?command=GO_TO_ORDER";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServiceFactory factory = ServiceFactory.getInstance();
        DispatcherBridge bridge = factory.createDispatcherBrigde();

        OrderDispatcher orderDispatcher = bridge
                .getOrderDispatcher((Passenger) session.getAttribute(Attributes.ACCOUNT.getName()));

        if (!orderDispatcher.getOrderStatus().equals(session.getAttribute(Attributes.ORDER_STATUS.getName()))) {
            session.setAttribute(Attributes.ORDER_STATUS.getName(), orderDispatcher.getOrderStatus());
            session.setAttribute(Attributes.ORDER.getName(), orderDispatcher.getOrder());
        }

        String page = Urls.ORDER.getName() + addUrl;
        response.sendRedirect(page);
    }
}
