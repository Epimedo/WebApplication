package by.epam.halavin.maintask.controller;

import by.epam.halavin.maintask.controller.filter.CheckDispatcher;
import by.epam.halavin.maintask.controller.filter.Chekable;
import by.epam.halavin.maintask.controller.info.Urls;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String encoding = servletRequest.getCharacterEncoding();
        if (!"UTF-8".equalsIgnoreCase(encoding)) {
            servletRequest.setCharacterEncoding("UTF-8");
        }
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String url = httpServletRequest.getRequestURI();
        CheckDispatcher dispatcher = new CheckDispatcher();
        Chekable chekable = dispatcher.getCheck(url);

        if (!chekable.check(servletRequest)) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.sendRedirect(Urls.MAIN.getName());
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
