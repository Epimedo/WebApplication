package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.controller.info.Urls;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.user.UserService;
import by.epam.halavin.maintask.service.validate.Validator;
import by.epam.halavin.maintask.util.UtilException;
import by.epam.halavin.maintask.util.UtilFactory;
import by.epam.halavin.maintask.util.creators.UserCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterInDriver implements Command {
    public static final Logger log = LogManager.getLogger(RegisterInDriver.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServiceFactory factory = ServiceFactory.getInstance();
        UtilFactory utilFactory = UtilFactory.getInstance();
        UserCreator userCreator = utilFactory.getDriverCreator();
        UserService service = factory.getUserService(request.getParameter(Attributes.ACCOUNT_TYPE.getName()));
        Validator validator = factory.getUserValidator(request.getParameter(Attributes.ACCOUNT_TYPE.getName()));

        String page = Urls.ADMIN.getName() + "?command=INIT_ADMIN";
        String status = "";


        try {
            User user = userCreator.create(request);
            status = validator.registrationCheck(user, request.getParameter(Attributes.SECOND_PASSWORD.getName()));
            if (status.equals("")) {
                if (service.registration(user)) {
                    session.setAttribute(Attributes.RESPONSE_STATUS.getName(),
                            Attributes.SUCCESS.getName());
                }
            } else {
                session.setAttribute(Attributes.RESPONSE_STATUS.getName(),
                        status);
            }
        } catch (ServiceException | UtilException e) {
            log.error(e.getStackTrace());
        }

        System.out.println(status);
        response.sendRedirect(page);
    }
}
