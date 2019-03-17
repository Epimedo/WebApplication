package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.command.CreatorFullURL;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.controller.info.Urls;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToOrder implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = Urls.ORDER_JSP.getName();
        HttpSession session = request.getSession();
        session.setAttribute(Attributes.RESPONSE_STATUS.getName(), null);

        String url = CreatorFullURL.create(request);
        session.setAttribute(Attributes.PREV_REQUEST.getName(), url);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
