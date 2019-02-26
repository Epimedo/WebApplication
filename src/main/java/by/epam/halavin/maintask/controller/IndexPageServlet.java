package by.epam.halavin.maintask.controller;

import by.epam.halavin.maintask.controller.command.CommandProvider;
import by.epam.halavin.maintask.controller.info.StringAttributes;
import by.epam.halavin.maintask.controller.info.StringUrls;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandProvider provider = new CommandProvider();
        provider.execute(req, resp);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(StringUrls.INDEX_JSP.getName());
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandProvider provider = new CommandProvider();
        provider.execute(req, resp);

        req.setAttribute(StringAttributes.COMMAND.getName(), null);
        resp.sendRedirect(StringUrls.MAIN.getName());
    }
}
