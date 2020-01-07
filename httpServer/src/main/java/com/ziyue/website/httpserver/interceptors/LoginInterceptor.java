package com.ziyue.website.httpserver.interceptors;

import java.io.BufferedInputStream;
import java.io.InputStream;

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
        request.setAttribute("requestTime", startTime);
        log.warn(request.getCharacterEncoding());
        BufferedInputStream bufferedInputStream = new BufferedInputStream(request.getInputStream());
        byte b[] = new byte[1024];
        StringBuilder stringBuilder = new StringBuilder();
        int ret = 0;
        while ((ret =  bufferedInputStream.read(b, 0, b.length)) != -1) {
            String s = new String(b, 0, ret);
            stringBuilder.append(s);
        }
        log.warn(stringBuilder.toString() + "wqnmd" + " " + testEncode(stringBuilder.toString()));
        return true;
    }

    public String testEncode(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GB2312
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是ISO-8859-1
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是UTF-8
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GBK
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
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
