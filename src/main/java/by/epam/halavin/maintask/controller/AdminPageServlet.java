package by.epam.halavin.maintask.controller;

import by.epam.halavin.maintask.controller.security.Chekable;
import by.epam.halavin.maintask.controller.security.impl.AdminSecurity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Chekable chekable = new AdminSecurity();
        chekable.validate(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Chekable chekable = new AdminSecurity();
        chekable.resend(req, resp);
    }
}
