package by.epam.halavin.maintask.controller.command;

import by.epam.halavin.maintask.controller.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


/**
 * Provider defines which command to represent
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public class CommandProvider {
    public static final Logger log = LogManager.getLogger(CommandProvider.class);
    private Map<CommandNames, Command> map = new HashMap<CommandNames, Command>();

    {
        map.put(CommandNames.SIGN_IN, new SignIn());
        map.put(CommandNames.SIGN_OUT, new SignOut());
        map.put(CommandNames.REGISTER_IN_PASS, new RegisterInPassenger());
        map.put(CommandNames.REGISTER_IN_DRIVER, new RegisterInDriver());
        map.put(CommandNames.INIT_ADMIN, new InitAdmin());
        map.put(CommandNames.NEXT_DRIVERS, new NextDrivers());
        map.put(CommandNames.NEXT_PASSENGERS, new NextPassengers());
        map.put(CommandNames.EDIT_PASSENGER, new EditPassenger());
        map.put(CommandNames.EDIT_DRIVER, new EditDriver());
        map.put(CommandNames.EMPTY_COMMAND, new Empty());
        map.put(CommandNames.EN, new ChangeLocale());
        map.put(CommandNames.RU, new ChangeLocale());
        map.put(CommandNames.DRIVERSEARCH, new DriverSearch());
        map.put(CommandNames.ACTIVATEDRIVER, new ActivateDriver());
        map.put(CommandNames.DEACTIVATEDRIVER, new DeactivateDriver());
        map.put(CommandNames.REFRESH, new Refresh());
        map.put(CommandNames.CHECKTRIPS, new CheckTrips());
        map.put(CommandNames.ACCEPT, new Accept());
        map.put(CommandNames.DECLINE, new Decline());
        map.put(CommandNames.NEWDRIVERSEARCH, new NewDriverSearch());
        map.put(CommandNames.RESET, new Reset());
        map.put(CommandNames.PAID, new Paid());
        map.put(CommandNames.NEXT_BY_VALUE_DRIVERS, new NextByValueDrivers());
        map.put(CommandNames.NEXT_BY_VALUE_PASSENGERS, new NextByValuePassengers());
        map.put(CommandNames.GO_TO_ORDER, new GoToOrder());
        map.put(CommandNames.GO_TO_DRIVER, new GoToDriver());
        map.put(CommandNames.GO_TO_MAIN, new GoToMain());
        map.put(CommandNames.REFRESH_ADMIN_PAGE, new RefreshAdminPage());
        map.put(CommandNames.GET_USER_ORDERS, new GetUserOrders());
        map.put(CommandNames.CLOSE_USER_ORDERS, new CloseUserOrders());
        map.put(CommandNames.NEXT_BY_VALUE_ORDERS, new NextByValueOrders());
        map.put(CommandNames.AJAX_CUR_STREET, new AjaxStreet());
    }

    public CommandProvider() {
    }

    /**
     * Function to get command object depending on command name
     *
     * @param commandName
     * @return
     */
    public Command getCommand(String commandName) {
        Command command;
        if (commandName != null) {
            command = map.get(CommandNames.valueOf(commandName.toUpperCase()));
        } else {
            command = map.get(CommandNames.EMPTY_COMMAND);
        }

        return command;
    }

}
