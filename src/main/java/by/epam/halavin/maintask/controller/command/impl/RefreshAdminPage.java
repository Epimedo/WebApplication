package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.command.CreatorFullURL;
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

public class RefreshAdminPage implements Command {
    public static final Logger log = LogManager.getLogger(SignIn.class);
    private final int START_INDEX = 1;
    private final int COUNT = 4;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = Urls.ADMIN_JSP.getName();
        HttpSession session = request.getSession();
        ServiceFactory factory = ServiceFactory.getInstance();
        UserLibrary driLibrary = factory.getDriverLibrary();
        UserLibrary passLibrary = factory.getPassLibrary();

        try {
            int startPass = (int) session.getAttribute(Attributes.CUR_PASS_POSITION.getName());
            int startDriver = (int) session.getAttribute(Attributes.CUR_DRIVER_POSITION.getName());
            session.setAttribute(Attributes.PASSENGERS.getName(), passLibrary.getUserIndexOf(startPass,
                    startPass + COUNT));
            session.setAttribute(Attributes.DRIVERS.getName(), driLibrary.getUserIndexOf(startDriver,
                    startDriver + COUNT));
        } catch (ServiceException e) {
            log.error(e.getStackTrace());
        }

        String url = CreatorFullURL.create(request);
        session.setAttribute(Attributes.PREV_REQUEST.getName(), url);


        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
