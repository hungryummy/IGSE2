package com.igse2.auth;

import com.alibaba.fastjson.JSON;
import com.igse2.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 检查用户是否已经完成登录（方式二：拦截器）
 * 需要在实现WebMvcConfigurer接口的配置类中重写addInterceptors方法，将拦截器注册到容器，并指定拦截规则
 */
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取请求
        log.info("拦截的请求：{}", request.getRequestURI());
        //2.判断用户是否登录
        HttpSession session = request.getSession();
        // 若存在，则放行
        if (Objects.nonNull(session.getAttribute("username"))) return true;
        //拦截住，并给前端页面返回未登录信息，以输出流的方式，json格式返回
        response.setContentType("application/json; charset=utf-8");
        // 1、使用Fastjson（默认过滤null值）
        // 发送重定向响应:
        response.sendRedirect("/login.html");
        response.getWriter().write(JSON.toJSONString(new Result(false,500,"未登录！")));
        // 2、使用默认的Jackson，在配置文件中关于Jackson配置的相关属性会失效
        //response.getWriter().write(new ObjectMapper().writeValueAsString(R.error("未登录")));
        return false;
    }
}