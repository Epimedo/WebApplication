package by.epam.halavin.maintask.controller.command.impl;

import by.epam.halavin.maintask.bean.user.Passenger;
import by.epam.halavin.maintask.bean.user.User;
import by.epam.halavin.maintask.controller.command.Command;
import by.epam.halavin.maintask.controller.info.Attributes;
import by.epam.halavin.maintask.controller.info.Urls;
import by.epam.halavin.maintask.service.ServiceFactory;
import by.epam.halavin.maintask.service.library.UserLibrary;
import by.epam.halavin.maintask.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditPassenger implements Command {
    public static final Logger log = LogManager.getLogger(EditPassenger.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = Urls.ADMIN.getName() + "?command=REFRESH_ADMIN_PAGE";
        ServiceFactory factory = ServiceFactory.getInstance();
        UserLibrary driverLab = factory.getPassLibrary();

        try {
            User user = driverLab.findUser(Integer.parseInt(request.getParameter(Attributes.ID.getName())));
            Passenger passenger = (Passenger) user;
            passenger.setStatus(request.getParameter(Attributes.STATUS.getName()));
            passenger.setDiscount(Double.parseDouble(request.getParameter(Attributes.DISCOUNT.getName())));
            passenger.setBonus(Double.parseDouble(request.getParameter(Attributes.BONUS.getName())));

            driverLab.editUser(passenger);
        } catch (ServiceException e) {
            e.printStackTrace();
            log.error(e.getStackTrace());
        }

        response.sendRedirect(page);
    }
}
