package by.epam.halavin.maintask.controller.filter;

import by.epam.halavin.maintask.controller.info.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * The class determines the required verification object depending on the incoming address
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public class CheckDispatcher {
    private Map<String, Chekable> map = new HashMap<>();

    {
        map.put(Urls.ORDER.getName(), new OrderCheck());
        map.put(Urls.DRIVER.getName(), new DriverCheck());
        map.put(Urls.ADMIN.getName(), new AdminCheck());
        map.put(Urls.MAIN.getName(), new MainCheck());
    }

    public Chekable getCheck(String url) {
        return map.get(url);
    }
}
