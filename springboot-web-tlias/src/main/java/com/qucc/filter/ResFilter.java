package com.qucc.filter;


import javax.servlet.*;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class ResFilter implements Filter {
    @Override //初始化方法只会被调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init 初始化方法执行了");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到了请求");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override //销毁方法也只会执行一次
    public void destroy() {
        System.out.println("destroy 销毁方法执行了");
    }
}
