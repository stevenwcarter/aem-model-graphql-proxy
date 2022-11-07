package com.bounteous.api.filter;

import com.google.inject.Singleton;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Adds the cross domain allowance headers which we use to allow the calls
 * to be made from web apps.
 *
 * @author steve.carter
 */
@Singleton
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
            IOException,
            ServletException {

        if (request instanceof HttpServletRequest) {
            HttpServletResponse res = (HttpServletResponse) response;
            res.setHeader("Access-Control-Allow-Origin", "*");
            res.setHeader("Access-Control-Allow-Methods", "HEAD,GET,POST,PUT,DELETE,OPTIONS");
            res.setHeader("Access-Control-Expose-Headers", "Content-Length");
            res.setHeader("Access-Control-Allow-Headers",
                          "Content-Type,X-Login-Session-ID,X-Session-ID,X-Transaction-ID,X-Member-ID,X-File-Type,X-File-Name,X-File-Size");
            res.setHeader("Access-Control-Max-Age", "600");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
