package com.ziyue.website.httpserver.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ziyue.website.common.Commons;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("get request {}, '{}'", request.getMethod(), request.getRequestURI());
        long startTime = System.currentTimeMillis();
        log.warn("wcnm" + request.getParts().toString());
        log.warn(request.getCharacterEncoding());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable
            ModelAndView modelAndView) throws Exception {
        long timeTaken = System.currentTimeMillis() - (long) request.getAttribute("requestTime");
        log.info("finish is request {}, '{}', status: {}, time taken: {}ms", request.getMethod(), request.getRequestURI(),
                response.getStatus(), timeTaken);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable
            Exception ex) throws Exception {
    }
}
