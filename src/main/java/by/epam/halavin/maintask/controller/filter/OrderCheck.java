package by.epam.halavin.maintask.controller.filter;

import by.epam.halavin.maintask.controller.info.Attributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OrderCheck implements Chekable {

    @Override
    public boolean check(ServletRequest request) {
        boolean bool = true;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        HttpSession session = httpServletRequest.getSession();

        if (session.getAttribute((Attributes.ACCOUNT_STATUS.getName())) == null ||
                !session.getAttribute(Attributes.ACCOUNT_STATUS.getName()).
                        equals(Attributes.SIGNED_PASSENGER.getName())) {
            bool = false;
        }

        return bool;
    }
}
