package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.command.CreatorFullURL;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.controller.info.Urls;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.user.UserService;
import by.epam.halavin.maintask.service.validate.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn implements Command {
    public static final Logger log = LogManager.getLogger(SignIn.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService(request.getParameter(Attributes.ACCOUNT_TYPE.getName()));

        String page = Urls.MAIN_JSP.getName();
        Validator validator = factory.getUserValidator(request.getParameter(Attributes.ACCOUNT_TYPE.getName()));

        try {
            if (!validator.isCorrectEmail(request.getParameter(Attributes.EMAIL.getName()))) {
                session.setAttribute(Attributes.RESPONSE_STATUS.getName(),
                        Attributes.INCORRECT_EMAIL.getName());
            } else {
                if (!validator.isCorrectPassword(request.getParameter(Attributes.EMAIL.getName()),
                        request.getParameter(Attributes.PASSWORD.getName()))) {
                    session.setAttribute(Attributes.RESPONSE_STATUS.getName(),
                            Attributes.INCORRECT_PASSWORD.getName());
                } else {
                    User user = userService.signIn(request.getParameter(Attributes.EMAIL.getName()),
                            request.getParameter(Attributes.PASSWORD.getName()));
                    session.setAttribute(Attributes.ACCOUNT.getName(), user);
                    session.setAttribute(Attributes.RESPONSE_STATUS.getName(), Attributes.SUCCESS_SIGNED.getName());
                    session.setAttribute(Attributes.ACCOUNT_STATUS.getName(), Attributes.SIGNED.getName() +
                            request.getParameter(Attributes.ACCOUNT_TYPE.getName()));
                }
            }
        } catch (ServiceException e) {
            log.error(e.getStackTrace());
        }

        String url = CreatorFullURL.create(request);
        session.setAttribute(Attributes.PREV_REQUEST.getName(), url);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
