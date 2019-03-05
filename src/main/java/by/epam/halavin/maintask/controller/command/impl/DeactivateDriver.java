package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.command.CreatorFullURL;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.controller.info.Urls;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeactivateDriver implements Command {
    public static final Logger log = LogManager.getLogger(ActivateDriver.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = Urls.DRIVER.getName() + "?command=GO_TO_DRIVER";
        ServiceFactory factory = ServiceFactory.getInstance();
        DriverManager manager = factory.createDriverManager();
        HttpSession session = request.getSession();

        manager.deactivate((Driver) session.getAttribute(Attributes.ACCOUNT.getName()));
        session.setAttribute(Attributes.DRIVER_STATUS.getName(), null);


        String url = CreatorFullURL.create(request);
        session.setAttribute(Attributes.PREV_REQUEST.getName(), url);

        response.sendRedirect(page);

    }
}
