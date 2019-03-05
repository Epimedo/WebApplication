package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.controller.info.Urls;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.library.UserLibrary;
import by.epam.halavin.maintask.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NextDrivers implements Command {
    public static final Logger log = LogManager.getLogger(NextDrivers.class);
    private final int START_INDEX = 1;
    private final int COUNT = 5;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String page = Urls.ADMIN_JSP.getName();
        ServiceFactory factory = ServiceFactory.getInstance();
        UserLibrary driLibrary = factory.getDriverLibrary();

        try {
            int start = (int) session.getAttribute(Attributes.CUR_DRIVER_POSITION.getName()) + COUNT;
            int count = start + COUNT - 1;
            int max = driLibrary.userCount();

            if (start > max) {
                start = START_INDEX;
                count = COUNT;
            }

            session.setAttribute(Attributes.DRIVERS.getName(), driLibrary.getUserIndexOf(start, count));
            session.setAttribute(Attributes.CUR_DRIVER_POSITION.getName(), start);
        } catch (ServiceException e) {
            log.error(e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
