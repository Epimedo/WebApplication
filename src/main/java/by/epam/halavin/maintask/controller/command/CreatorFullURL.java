package by.epam.halavin.maintask.controller.command;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Controller class that has static method for creation request's url
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public class CreatorFullURL {

    /**
     * Function to get full url of income request
     *
     * @param request
     * @return - url string
     */
    public static String create(HttpServletRequest request) {
        String url = "";

        Enumeration<String> paramNames2 = request.getParameterNames();


        String paramName;
        String paramValue;
        while (paramNames2.hasMoreElements()) {
            paramName = paramNames2.nextElement();

            paramValue = request.getParameter(paramName);
            url = url + paramName + "=" + paramValue + "&";
        }

        url = request.getRequestURL() + "?" + url;

        return url;

    }

}
