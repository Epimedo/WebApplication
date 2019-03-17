package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.command.CreatorFullURL;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.controller.info.Urls;
import by.epam.halavin.maintask.service.AccountTypes;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.driver.DriverManager;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.validate.Validator;
import by.epam.halavin.maintask.service.validate.impl.DriverValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ActivateDriver implements Command {
    public static final Logger log = LogManager.getLogger(ActivateDriver.class);
    public final String addUrl = "?command=GO_TO_DRIVER";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = Urls.DRIVER.getName() + addUrl;
        ServiceFactory factory = ServiceFactory.getInstance();
        DriverManager manager = factory.createDriverManager();
        HttpSession session = request.getSession();
        Validator validator = factory.getUserValidator(AccountTypes.DRIVER.toString());

        try {

            if (!validator.isBan((Driver) session.getAttribute(Attributes.ACCOUNT.getName()))) {
                if (!((DriverValidator) validator).isEndCheckaupEnd(
                        (Driver) session.getAttribute(Attributes.ACCOUNT.getName()))) {
                    manager.activate((Driver) session.getAttribute(Attributes.ACCOUNT.getName()));
                    session.setAttribute(Attributes.DRIVER_STATUS.getName(), Attributes.ACTIVATE.getName());
                } else {
                    session.setAttribute(Attributes.DRIVER_STATUS.getName(),
                            Attributes.CAR_CHECKUP_END.getName());
                }
            } else {
                session.setAttribute(Attributes.DRIVER_STATUS.getName(), Attributes.ACCOUNT_BAN.getName());
            }
        } catch (ServiceException e) {
            log.error(e.getMessage());
        }

        String url = CreatorFullURL.create(request);
        session.setAttribute(Attributes.PREV_REQUEST.getName(), url);

        response.sendRedirect(page);
    }
}
