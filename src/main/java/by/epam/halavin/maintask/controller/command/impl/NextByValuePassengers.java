package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.controller.info.Urls;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.library.UserLibrary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NextByValuePassengers implements Command {

    public static final Logger log = LogManager.getLogger(NextByValuePassengers.class);
    private final int COUNT = 5;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String page = Urls.ADMIN_JSP.getName();
        ServiceFactory factory = ServiceFactory.getInstance();
        UserLibrary passLibrary = factory.getPassLibrary();

        int start = Integer.parseInt(request.getParameter(Attributes.BLOCK_NUMBER.getName())) - 1;
        start *= COUNT;
        start++;

        try {
            session.setAttribute(Attributes.PASSENGERS.getName(), passLibrary.getUserIndexOf(start,
                    start + COUNT - 1));
            session.setAttribute(Attributes.CUR_PASS_POSITION.getName(), start);
            session.setAttribute(Attributes.FOCUS_TABLE.getName(), Attributes.USER_TABLE.getName());
        } catch (ServiceException e) {
            log.error(e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
