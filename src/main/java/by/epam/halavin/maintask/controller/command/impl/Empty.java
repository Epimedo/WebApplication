package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.controller.info.Urls;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Empty implements Command {
    private final String JSP = "_JSP";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String prevRequest = (String) session.getAttribute(Attributes.PREV_REQUEST.getName());

        if (prevRequest == null) {
            String url = request.getRequestURI();
            url = url.split("/")[url.split("/").length - 1];
            url = Urls.valueOf(url.toUpperCase() + JSP).getName();

            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(prevRequest);
        }
    }
}
