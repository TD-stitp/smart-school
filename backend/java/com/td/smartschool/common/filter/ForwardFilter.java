package com.td.smartschool.common.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/4/24 11:56
 */
@Component
@WebFilter(filterName = "forwardFilter", urlPatterns = "/admin/*")
public class ForwardFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        if (uri.startsWith("/js") || uri.startsWith("/css") || uri.startsWith("/img") || uri.startsWith("/api") || uri.startsWith("/smart-school")) {
            request.getRequestDispatcher(uri).forward(request, response);

        } else {
            request.getRequestDispatcher("/").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
