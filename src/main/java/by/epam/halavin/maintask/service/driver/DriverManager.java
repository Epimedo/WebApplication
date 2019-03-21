package by.epam.halavin.maintask.service.driver;

import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.dao.repository.DriverRepository;
import by.epam.halavin.maintask.service.exception.ServiceException;

/**
 * Manager controls driver's status
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public class DriverManager {

    /**
     * Function activate driver account
     *
     * @param driver
     * @throws ServiceException
     */
    public void activate(Driver driver) throws ServiceException {
        DriverRepository driverRepository = DriverRepository.getInstance();
        driverRepository.addDriver(driver);
    }

    /**
     * Function deactivate driver status
     *
     * @param driver
     */
    public void deactivate(Driver driver) {
        DriverRepository driverRepository = DriverRepository.getInstance();
        driverRepository.removeDriver(driver);
    }
}
