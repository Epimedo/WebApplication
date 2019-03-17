package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.exception.ServiceException;
import by.epam.halavin.maintask.service.library.StreetLibrary;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxStreet implements Command {
    private final String contextType = "application/json";
    private final String encoding = "utf-8";
    private final String curStreet = "curStreet";
    private final String answer = "answer";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory factory = ServiceFactory.getInstance();
        StreetLibrary library = factory.getStreetLibrary();

        response.setContentType(contextType);
        response.setCharacterEncoding(encoding);
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();

            String street = library.findStreetLike(request.getParameter(curStreet).toLowerCase().trim());
            jsonEnt.put(answer, " " + street);
            out.print(jsonEnt.toString());
        } catch (JSONException | ServiceException e) {
            e.printStackTrace();
        }
    }
}
