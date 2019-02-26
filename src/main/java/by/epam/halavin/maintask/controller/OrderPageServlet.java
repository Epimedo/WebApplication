package by.epam.halavin.maintask.controller;

import by.epam.halavin.maintask.controller.security.Chekable;
import by.epam.halavin.maintask.controller.security.impl.OrderSecurity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Chekable chekable = new OrderSecurity();
        chekable.validate(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Chekable chekable = new OrderSecurity();
        chekable.validate(req, resp);
    }
}
