package by.epam.halavin.maintask.service.login;

import by.epam.halavin.maintask.service.login.impl.AdminGate;
import by.epam.halavin.maintask.service.login.impl.DriverGate;
import by.epam.halavin.maintask.service.login.impl.PassengerGate;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<AccountTypes, UserGate> map = new HashMap<>();

    {
        map.put(AccountTypes.PASSENGER, new PassengerGate());
        map.put(AccountTypes.DRIVER, new DriverGate());
        map.put(AccountTypes.ADMIN, new AdminGate());
    }

    public UserGate getGate(String accountType) {
        return map.get(AccountTypes.valueOf(accountType.toUpperCase()));
    }
}
