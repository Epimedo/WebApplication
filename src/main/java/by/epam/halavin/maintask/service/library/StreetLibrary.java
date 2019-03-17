package by.epam.halavin.maintask.service.library;

import by.epam.halavin.maintask.service.exception.ServiceException;

public abstract class StreetLibrary {

    public abstract String findStreetLike(String namePart) throws ServiceException;
}
