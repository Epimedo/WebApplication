package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.controller.info.Urls;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.library.OrderLibrary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NextByValueOrders implements Command {
    public static final Logger log = LogManager.getLogger(NextByValueOrders.class);
    private final int COUNT = 5;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String page = Urls.ORDER_JSP.getName();

        ServiceFactory factory = ServiceFactory.getInstance();
        OrderLibrary orderLibrary = factory.getOrderLibrary();

        int start = Integer.parseInt(request.getParameter(Attributes.BLOCK_NUMBER.getName())) - 1;
        start *= COUNT;
        start++;


        try {
            User user = (User) session.getAttribute(Attributes.ACCOUNT.getName());
            session.setAttribute(Attributes.ORDERS.getName(),
                    orderLibrary.getUserOrdersIndexOf(user.getId(), start, start + COUNT - 1));
            session.setAttribute(Attributes.FOCUS_TABLE.getName(), Attributes.ORDER_TABLE.getName());
        } catch (ServiceException e) {
            log.error(e.getMessage());
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }
}
