package org.biletado.logging;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestLoggingFilter extends AbstractRequestLoggingFilter {

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return logger.isDebugEnabled() || logger.isInfoEnabled();
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        if (logger.isInfoEnabled()) {
            logger.info("Request: " + request.getMethod() + " " + request.getRequestURI());
        }
        if (logger.isDebugEnabled()) {
            logger.debug(message);
        }
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        if (logger.isDebugEnabled()) {
            logger.debug(message);
        }
    }
}