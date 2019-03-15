package com.Hemlagat.filter;

import com.Hemlagat.model.session.UserBean;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.omnifaces.filter.HttpFilter;

/**
 *
 * @author Daniel Cebe
 */
@WebFilter(urlPatterns = "/*")
public class SessionFilter extends HttpFilter {

    @Inject
    private UserBean userBean;

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, HttpSession session, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req1 = (HttpServletRequest) request;
        HttpServletResponse res1 = (HttpServletResponse) response;

        String HomeURL = req1.getContextPath() + "/Locationpage.xhtml";
        System.out.println(HomeURL);
        System.out.println("Current path " + req1.getRequestURL());

        String currentPath = req1.getRequestURL().toString();

        if (!userBean.isLoggedIn()) {
            if (currentPath.contains("/Hemlagat/profile.xhtml") || currentPath.contains("/Hemlagat/addb/Create.xhtml")) {
                res1.sendRedirect(req1.getContextPath() + "/LoginPage.xhtml");
            } else {
                chain.doFilter(request, response);

            }

        } else {
            chain.doFilter(request, response);

        }

    }
}
