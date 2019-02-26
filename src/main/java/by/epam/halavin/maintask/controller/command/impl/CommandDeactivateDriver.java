package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.driver.DriverManager;
import by.epam.halavin.maintask.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandDeactivateDriver implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ServiceFactory factory = ServiceFactory.getInstance();
        DriverManager manager = factory.createDriverManager();

        manager.deactivate(request);
    }
}
