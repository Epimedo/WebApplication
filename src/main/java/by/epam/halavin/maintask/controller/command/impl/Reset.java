package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.controller.info.Urls;
import by.epam.halavin.maintask.service.bridge.DispatcherBridge;
import by.epam.halavin.maintask.service.order.DefaultOrderDispatcher;
import by.epam.halavin.maintask.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Reset implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServiceFactory factory = ServiceFactory.getInstance();
        DispatcherBridge bridge = factory.createDispatcherBrigde();
        DefaultOrderDispatcher orderDispatcher = bridge
                .getOrderDispatcher((Passenger) session.getAttribute(Attributes.ACCOUNT.getName()));

        orderDispatcher.resetOrder();
        session.setAttribute(Attributes.ORDER_STATUS.getName(), null);
        session.setAttribute(Attributes.ORDER.getName(), null);
        bridge.removeOrderDispatcher((Passenger) session.getAttribute(Attributes.ACCOUNT.getName()));

        String page = Urls.ORDER.getName() + "?command=GO_TO_ORDER";
        response.sendRedirect(page);

    }
}
