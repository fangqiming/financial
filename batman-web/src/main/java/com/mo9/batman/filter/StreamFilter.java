package com.mo9.batman.filter;

import com.mo9.batman.stream.BufferedServletRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by ylzhou on 2017/6/22.
 * 重新包装Post请求输入流
 */
@Component
public class StreamFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String method = request.getMethod();
        if (RequestMethod.GET.name().equalsIgnoreCase(method)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletRequest httpServletRequest = new BufferedServletRequestWrapper(request);
            filterChain.doFilter(httpServletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
