package by.epam.halavin.maintask.service.driver;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.dao.repository.DriverRepository;
import by.epam.halavin.maintask.service.exception.ServiceException;

public class DriverManager {

    public void activate(Driver driver) throws ServiceException {
        DriverRepository driverRepository = DriverRepository.getInstance();
        driverRepository.addDriver(driver);
    }

    public void deactivate(Driver driver) {
        DriverRepository driverRepository = DriverRepository.getInstance();
        driverRepository.removeDriver(driver);
    }
}
