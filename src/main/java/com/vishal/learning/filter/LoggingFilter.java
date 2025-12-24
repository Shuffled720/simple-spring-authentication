package com.vishal.learning.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        long startTime = System.currentTimeMillis();
        String method = request.getMethod();
        String uri = request.getRequestURI();

        // 1. Log Incoming Request
        logger.info("Incoming Request: {} {}", method, uri);

        try {
            // Proceed with the next filter in the chain
            filterChain.doFilter(request, response);
        } finally {
            // 2. Log Outgoing Response
            long duration = System.currentTimeMillis() - startTime;
            int status = response.getStatus();

            logger.info("Outgoing Response: {} {} | Status: {} | Time: {}ms",
                    method, uri, status, duration);
        }
    }



}