package com.bounteous.api.filter;

import com.google.inject.Singleton;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.*;

/**
 * When .json is used on the URL, add the Accept: applicaton/json header.
 * This is a shortcut to specify the response content type that is desired
 * if we ever support other response types on the API (e.g. XML)
 *
 * @author steve.carter
 */
@Singleton
public class JsonFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
            IOException,
            ServletException {

        if (request instanceof HttpServletRequest) {
            final HttpServletRequest httpReq = (HttpServletRequest) request;

            //String requestUri = httpReq.getRequestURI();
            HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpReq) {
                @Override
                public String getRequestURI() {
                    return super.getRequestURI()
                            .substring(0,
                                       super.getRequestURI()
                                               .length() - 5);
                }

                @Override
                public String getHeader(String name) {
                    if ("Accept".equals(name)) {
                        return MediaType.APPLICATION_JSON;
                    }
                    return super.getHeader(name);
                }

                @Override
                public Enumeration<String> getHeaderNames() {
                    Enumeration<String> e = super.getHeaderNames();
                    Set<String> headerNames = new HashSet<>();
                    headerNames.add("Accept");
                    while (e.hasMoreElements()) {
                        headerNames.add(e.nextElement());
                    }
                    return Collections.enumeration(headerNames);
                }

                @Override
                public Enumeration<String> getHeaders(String name) {
                    if ("accept".equalsIgnoreCase(name)) {
                        List<String> s = new ArrayList<>();
                        s.add(MediaType.APPLICATION_JSON);
                        return Collections.enumeration(s);
                    }
                    return super.getHeaders(name);
                }
            };
            chain.doFilter(wrapper, response);
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }

}
