package by.epam.halavin.maintask.dao.edited;

import by.epam.halavin.maintask.dao.exception.DAOException;

import javax.servlet.http.HttpServletRequest;

public interface Edited {

    void edit(HttpServletRequest request) throws DAOException;
}
