package by.epam.halavin.maintask.controller.filter;

import javax.servlet.ServletRequest;

/**
 * Abstract class defines filter urls verification
 */
public interface Chekable {

    boolean check(ServletRequest request);
}
