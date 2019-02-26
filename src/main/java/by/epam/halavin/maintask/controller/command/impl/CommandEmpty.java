package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandEmpty implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

    }
}
