package com.chrisxin.interceptor;

import com.chrisxin.utils.CurrentHolder;
import com.chrisxin.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    // 拦截器处理逻辑
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头的token
        String token = request.getHeader("token");
        //判断token是否存在，不存在->响应401
        if (token == null|| token.isEmpty()) {
            log.info("请求token令牌不存在");
            response.setStatus(401);
            return false;
        }
        //解析token，校验失败->响应401
        try {
            Claims claims =JwtUtils.parseToken(token);
            CurrentHolder.setCurrentId(Integer.valueOf(claims.get("id").toString()));
        } catch (Exception e) {
            log.info("令牌非法");
            response.setStatus(401);
            return false;
        }
        //校验通过放行
        log.info("令牌合法");
        return true;
    }

    // 处理业务逻辑后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    // 响应返回给前端后，清理当前线程的变量
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        CurrentHolder.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
