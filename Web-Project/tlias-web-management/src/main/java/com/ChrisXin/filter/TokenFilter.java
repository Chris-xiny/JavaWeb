package com.ChrisXin.filter;

import com.ChrisXin.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter("/*")  //注释掉过滤器请求路径。暂时不用fliter
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        //获取请求路径
        String path = req.getRequestURI();
        //判断是否为登录请求,放行
        if (path.equals("/login")) {
            log.info("登录请求,放行");
            chain.doFilter(request, response);
            return;
        }
        //获取请求头的token
        String token = req.getHeader("token");
        //判断token是否存在，不存在->响应401
        if (token == null|| token.isEmpty()) {
            log.info("请求token令牌不存在");
            resp.setStatus(401);
            return;
        }
        //解析token，校验失败->响应401
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("令牌非法");
            resp.setStatus(401);
            return;
        }
        //校验通过放行
        log.info("令牌合法");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
