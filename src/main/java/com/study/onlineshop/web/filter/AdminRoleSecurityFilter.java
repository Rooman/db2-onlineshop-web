package com.study.onlineshop.web.filter;

import com.study.onlineshop.entity.UserRole;
import com.study.onlineshop.security.SecurityService;
import com.study.onlineshop.security.Session;
import com.study.onlineshop.web.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumSet;

public class AdminRoleSecurityFilter implements Filter {

    @Autowired
    private SecurityService securityService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Cookie[] cookies = httpServletRequest.getCookies();
        boolean isAuth = false;

        Session session = null;
        String token = WebUtil.getTokenFromCookies(cookies);
        if (token != null) {
            session = securityService.getSession(token);
            if (session != null) {
                isAuth = securityService.checkTokenPermissions(token, EnumSet.of(UserRole.ADMIN));
            }
        }

        if (isAuth) {
            request.setAttribute("session", session);
            chain.doFilter(request, response);
        } else {
            httpServletResponse.sendRedirect("/login");
        }

    }

    @Override
    public void destroy() {

    }

}
