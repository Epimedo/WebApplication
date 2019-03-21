package by.epam.halavin.maintask.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Abstract class defines the act method
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public interface Command {

    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
