package by.epam.halavin.maintask.service.login;

import by.epam.halavin.maintask.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface UserGate {

    void registerIn(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

    void signIn(String email, String password, HttpSession session) throws ServiceException;

    void signOut(HttpSession session) throws ServiceException;
}
