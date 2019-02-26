package by.epam.halavin.maintask.controller.filter;

import javax.servlet.*;
import java.io.IOException;

public class AdminPageFilter implements Filter {

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

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
