package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.controller.info.Urls;
import by.epam.halavin.maintask.service.bridge.DispatcherBridge;
import by.epam.halavin.maintask.service.order.DefaultOrderDispatcher;
import by.epam.halavin.maintask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Accept implements Command {
    public static final Logger log = LogManager.getLogger(Accept.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DispatcherBridge dispatcherBridge = serviceFactory.createDispatcherBrigde();

        DefaultOrderDispatcher orderDispatcher = dispatcherBridge.
                driverCheck((Driver) session.getAttribute(Attributes.ACCOUNT.getName()));
        orderDispatcher.acceptOrder();
        session.setAttribute(Attributes.DRIVER_STATUS.getName(), orderDispatcher.getDriverStatus());
        session.setAttribute(Attributes.ACCOUNT.getName(), orderDispatcher.getOrder().getDriver());

        if (orderDispatcher.getOrderStatus().equals(Attributes.RESET.getName())) {
            session.setAttribute(Attributes.ORDER.getName(), null);
        } else {
            session.setAttribute(Attributes.ORDER.getName(), orderDispatcher.getOrder());
        }

        String page = Urls.DRIVER.getName() + "?command=GO_TO_DRIVER";
        response.sendRedirect(page);
    }
}
