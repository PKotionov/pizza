package ru.mail.pashok.shop.controller.Filter;

import ru.mail.pashok.shop.repository.model.Role;
import ru.mail.pashok.shop.repository.model.User;
import ru.mail.pashok.shop.service.UserServiceImpl;
import ru.mail.pashok.shop.service.model.RoleDTO;
import ru.mail.pashok.shop.service.model.UserDTO;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RoleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            httpServletResponse.sendRedirect("/");
        } else {
            String requestURI = String.valueOf(httpServletRequest.getRequestURL());
            System.out.println(requestURI);
            RoleDTO role = UserServiceImpl.getInstance().getUserRole(username);
            switch (role){
                case ADMIN:
                    if (requestURI.contains("/admin")) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        httpServletResponse.sendRedirect("/admin");
                    }
                    break;
                case USER:
                    if (requestURI.contains("/user")) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        httpServletResponse.sendRedirect("/user");
                    }
            }

        }

    }

    @Override
    public void destroy() {

    }
}
