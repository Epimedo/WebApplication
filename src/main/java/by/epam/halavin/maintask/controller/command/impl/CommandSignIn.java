package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.info.StringAttributes;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.login.UserGate;
import by.epam.halavin.maintask.service.login.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommandSignIn implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        UserManager userManager = ServiceFactory.getInstance().createUserManager();
        UserGate gate = userManager.getGate(request.getParameter(StringAttributes.ACCOUNT_TYPE.getName()));

        try {

            gate.signIn(request.getParameter(StringAttributes.EMAIL.getName()),
                    request.getParameter(StringAttributes.PASSWORD.getName()), session);
        } catch (ServiceException e) {
            throw e;
        }
    }
}
