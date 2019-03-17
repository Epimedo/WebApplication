package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.controller.info.Urls;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.bridge.DispatcherBridge;
import by.epam.halavin.maintask.service.order.DefaultOrderDispatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckTrips implements Command {
    public static final Logger log = LogManager.getLogger(CheckTrips.class);
    private final String addUrl = "?command=GO_TO_DRIVER";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServiceFactory factory = ServiceFactory.getInstance();
        DispatcherBridge bridge = factory.createDispatcherBrigde();
        DefaultOrderDispatcher orderDispatcher = bridge.
                driverCheck((Driver) session.getAttribute(Attributes.ACCOUNT.getName()));

        if (orderDispatcher == null) {
            session.setAttribute(Attributes.ORDER.getName(), null);
        } else {
            session.setAttribute(Attributes.ORDER.getName(), orderDispatcher.getOrder());
        }

        String page = Urls.DRIVER.getName() + addUrl;
        response.sendRedirect(page);
    }
}
