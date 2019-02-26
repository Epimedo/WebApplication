package by.epam.halavin.maintask.dao.registration;

import by.epam.halavin.maintask.dao.exception.DAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserRegister {

    String registerIn(HttpServletRequest request, HttpServletResponse response) throws DAOException;
}
