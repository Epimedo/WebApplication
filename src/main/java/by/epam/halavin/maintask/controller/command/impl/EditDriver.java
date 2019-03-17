package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.controller.info.Urls;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.library.UserLibrary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditDriver implements Command {
    public static final Logger log = LogManager.getLogger(EditDriver.class);
    private final String addUrl = "?command=REFRESH_ADMIN_PAGE";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String page = Urls.ADMIN.getName() + addUrl;
        ServiceFactory factory = ServiceFactory.getInstance();
        UserLibrary driverLab = factory.getDriverLibrary();

        try {
            User user = driverLab.findUser(Integer.parseInt(request.getParameter(Attributes.ID.getName())));
            Driver driver = (Driver) user;
            driver.setStatus(request.getParameter(Attributes.STATUS.getName()));
            session.setAttribute(Attributes.FOCUS_TABLE.getName(), Attributes.DRIVER_TABLE.getName());

            driverLab.editUser(driver);
        } catch (ServiceException e) {
            log.error(e.getStackTrace());
        }

        response.sendRedirect(page);
    }
}
