package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.info.StringAttributes;
import by.epam.halavin.maintask.service.OrderDispatcher;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommandDecline implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        OrderDispatcher orderDispatcher = ServiceFactory.getInstance().createDispatcherBrigde()
                .driverCheck((Driver) session.getAttribute(StringAttributes.ACCOUNT.getName()));

        orderDispatcher.declineOrder(request, response);
    }
}
