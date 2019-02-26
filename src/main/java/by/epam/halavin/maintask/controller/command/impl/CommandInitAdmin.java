package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.info.StringAttributes;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.admin.AdminManager;
import by.epam.halavin.maintask.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommandInitAdmin implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        AdminManager manager = ServiceFactory.getInstance().createAdminManager();

        manager.initAdminTables(request, response);
        HttpSession session = request.getSession();

        session.setAttribute(StringAttributes.ADMIN_MANAGER.getName(), manager);
    }
}
