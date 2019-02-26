package by.epam.halavin.maintask.controller.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Chekable {

    void validate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    void resend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
