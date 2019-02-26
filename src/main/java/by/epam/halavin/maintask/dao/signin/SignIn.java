package by.epam.halavin.maintask.dao.signin;

import by.epam.halavin.maintask.dao.exception.DAOException;

import javax.servlet.http.HttpSession;

public interface SignIn {

    String sign(String email, String password, HttpSession session) throws DAOException;
}
