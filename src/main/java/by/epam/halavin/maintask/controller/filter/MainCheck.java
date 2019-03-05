package by.epam.halavin.maintask.controller.filter;

import javax.servlet.ServletRequest;

public class MainCheck implements Chekable {

    @Override
    public boolean check(ServletRequest request) {
        return true;
    }
}
