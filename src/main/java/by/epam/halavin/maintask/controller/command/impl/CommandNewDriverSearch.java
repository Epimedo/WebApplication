package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.service.DispatcherBridge;
import by.epam.halavin.maintask.service.OrderDispatcher;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommandNewDriverSearch implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        OrderDispatcher orderDispatcher = ServiceFactory.getInstance().createOrderDispatcher();

        orderDispatcher.makeOrder(request, response);
        DispatcherBridge bridge = ServiceFactory.getInstance().createDispatcherBrigde();
        bridge.addOrderDispatcher(orderDispatcher);
    }
}
