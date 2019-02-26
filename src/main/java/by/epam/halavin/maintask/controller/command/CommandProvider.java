package by.epam.halavin.maintask.controller.command;

import by.epam.halavin.maintask.controller.command.impl.*;
import by.epam.halavin.maintask.controller.info.StringAttributes;
import by.epam.halavin.maintask.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


public class CommandProvider {
    public static final Logger log = LogManager.getLogger(CommandProvider.class);
    private Map<CommandNames, Command> map = new HashMap<CommandNames, Command>();

    {
        map.put(CommandNames.SIGN_IN, new CommandSignIn());
        map.put(CommandNames.SIGN_OUT, new CommandSignOut());
        map.put(CommandNames.REGISTER_IN, new CommandRegisterIn());
        map.put(CommandNames.INIT_ADMIN, new CommandInitAdmin());
        map.put(CommandNames.NEXT_DRIVERS, new CommandNextDrivers());
        map.put(CommandNames.NEXT_PASSENGERS, new CommandNextPassengers());
        map.put(CommandNames.EDIT_PASSENGER, new CommandEditPassenger());
        map.put(CommandNames.EDIT_DRIVER, new CommandEditDriver());
        map.put(CommandNames.EMPTY_COMMAND, new CommandEmpty());
        map.put(CommandNames.EN, new CommandChangeLocale());
        map.put(CommandNames.RU, new CommandChangeLocale());
        map.put(CommandNames.DRIVERSEARCH, new CommandDriverSearch());
        map.put(CommandNames.ACTIVATEDRIVER, new CommandActivateDriver());
        map.put(CommandNames.DEACTIVATEDRIVER, new CommandDeactivateDriver());
        map.put(CommandNames.REFRESH, new CommandRefresh());
        map.put(CommandNames.CHECKTRIPS, new CommandCheckTrips());
        map.put(CommandNames.ACCEPT, new CommandAccept());
        map.put(CommandNames.DECLINE, new CommandDecline());
        map.put(CommandNames.NEWDRIVERSEARCH, new CommandNewDriverSearch());
        map.put(CommandNames.RESET, new CommandReset());
        map.put(CommandNames.PAID, new CommandPaid());
        map.put(CommandNames.NEXT_BY_VALUE_DRIVERS, new CommandNextByValueDrivers());
        map.put(CommandNames.NEXT_BY_VALUE_PASSENGERS, new CommandNextByValuePassengers());
    }

    public CommandProvider() {
    }

    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Command command;

        if (request.getParameter(StringAttributes.COMMAND.getName()) == null) {
            command = map.get(CommandNames.EMPTY_COMMAND);
        } else {
            command = map.get(CommandNames.valueOf(request.getParameter(StringAttributes.COMMAND.getName())
                    .toUpperCase()));
        }

        try {
            command.execute(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
            log.error(e);
        }
    }

}
