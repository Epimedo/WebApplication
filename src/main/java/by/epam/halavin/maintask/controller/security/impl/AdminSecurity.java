package by.epam.halavin.maintask.controller.security.impl;

import by.epam.halavin.maintask.controller.info.StringAttributes;
import by.epam.halavin.maintask.controller.info.StringUrls;
import by.epam.halavin.maintask.controller.command.CommandProvider;
import by.epam.halavin.maintask.controller.security.Chekable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminSecurity implements Chekable {

    @Override
    public void validate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute(StringAttributes.RESPONSE_STATUS.getName()) == null ||
                !session.getAttribute(StringAttributes.RESPONSE_STATUS.getName())
                        .equals(StringAttributes.SIGNED_ADMIN.getName())) {
            resp.sendRedirect(StringUrls.MAIN.getName());
        } else {
            CommandProvider provider = new CommandProvider();
            provider.execute(req, resp);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher(StringUrls.ADMIN_JSP.getName());
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    public void resend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute(StringAttributes.RESPONSE_STATUS.getName()) == null ||
                !session.getAttribute(StringAttributes.RESPONSE_STATUS.getName())
                        .equals(StringAttributes.SIGNED_ADMIN.getName())) {
            response.sendRedirect(StringUrls.MAIN.getName());
        } else {
            CommandProvider provider = new CommandProvider();
            provider.execute(request, response);

            request.setAttribute(StringAttributes.COMMAND.getName(), null);
            response.sendRedirect(StringUrls.ADMIN.getName());
        }
    }
}
