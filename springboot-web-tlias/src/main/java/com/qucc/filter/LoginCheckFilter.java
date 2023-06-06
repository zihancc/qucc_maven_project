package com.qucc.filter;

import com.alibaba.fastjson.JSONObject;
import com.qucc.pojo.Result;
import com.qucc.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURL().toString();
        log.info("请求的url: {}", url);
        if (url.contains("login")){
            log.info("登录操作，放行...");
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("token");
        if(!StringUtils.hasLength(token)){
            log.info("请求头token为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            String errJson = JSONObject.toJSONString(error);
            response.getWriter().write(errJson);
            return;
        }

        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败， 返回未登录的错误信息");
            Result error = Result.error("NOT_LOGIN");
            String errJson = JSONObject.toJSONString(error);
            response.getWriter().write(errJson);
            return;
        }

        log.info("令牌合法， 方行");
        filterChain.doFilter(request, response);
    }
}
