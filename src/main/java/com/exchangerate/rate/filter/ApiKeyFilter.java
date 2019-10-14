package com.exchangerate.rate.filter;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApiKeyFilter implements Filter {

    public static final Logger LOG = LoggerFactory.getLogger(ApiKeyFilter.class);

    private final String API_KEY = "api-key";

    @Value("${server.key}")
    private String serverKey;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String apiKey = req.getHeader(API_KEY);
        String md5Hex = DigestUtils.md5Hex(serverKey);
        if (apiKey == null
                || apiKey.trim().isEmpty()
                || !md5Hex.equals(apiKey)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            String errorMsg = String.format("ApiKey %s is not valid ", apiKey);
            httpResponse.sendError(HttpStatus.NOT_ACCEPTABLE.value(), errorMsg);
            LOG.error( errorMsg+ apiKey);
            return;
        }
        filter.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void destroy() {

    }
}
