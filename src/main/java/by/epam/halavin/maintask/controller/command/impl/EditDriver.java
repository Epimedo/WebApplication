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
import java.io.IOException;

public class EditDriver implements Command {
    public static final Logger log = LogManager.getLogger(EditDriver.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = Urls.ADMIN.getName() + "?command=REFRESH_ADMIN_PAGE";
        ServiceFactory factory = ServiceFactory.getInstance();
        UserLibrary driverLab = factory.getDriverLibrary();

        try {
            User user = driverLab.findUser(Integer.parseInt(request.getParameter(Attributes.ID.getName())));
            Driver driver = (Driver) user;
            driver.setStatus(request.getParameter(Attributes.STATUS.getName()));

            driverLab.editUser(driver);
        } catch (ServiceException e) {
            e.printStackTrace();
            log.error(e.getStackTrace());
        }

        response.sendRedirect(page);
    }
}
