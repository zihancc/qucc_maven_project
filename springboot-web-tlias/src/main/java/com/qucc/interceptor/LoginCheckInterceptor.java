package com.qucc.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.qucc.pojo.Result;
import com.qucc.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override //目标资源方法方行前运行，返回true：放行，返回false，不放心代表拦截
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURL().toString();
        log.info("请求的url: {}", url);
        if (url.contains("login")){
            log.info("登录操作，放行...");
            return true;
        }

        String token = request.getHeader("token");
        if(!StringUtils.hasLength(token)){
            log.info("请求头token为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            String errJson = JSONObject.toJSONString(error);
            response.getWriter().write(errJson);
            return false;
        }

        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败， 返回未登录的错误信息");
            Result error = Result.error("NOT_LOGIN");
            String errJson = JSONObject.toJSONString(error);
            response.getWriter().write(errJson);
            return false;
        }

        log.info("令牌合法， 放行");
        return true;
    }

    @Override //目标方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle 运行了...");

    }

    @Override //视图渲染完毕后运行，最后才会运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
