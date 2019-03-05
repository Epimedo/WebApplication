package by.epam.halavin.maintask.controller.filter;

import javax.servlet.ServletRequest;

public interface Chekable {

    boolean check(ServletRequest request);
}
